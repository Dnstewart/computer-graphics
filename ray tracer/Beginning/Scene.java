import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.awt.*;

public class Scene {

  public DirectionalLight[] lights;
  public Camera camera;
  public Mesh[] meshes;
  public Vector3[][] colors;

  public Scene(DirectionalLight[] light, Camera camera, Mesh[] mesh) {
    this.lights = light;
    this.camera = camera;
    this.meshes = mesh;

  }

  public void render(BufferedImage outImage) {
    this.colors = new Vector3[outImage.getWidth()][outImage.getHeight()];

    for (var y = 0; y < outImage.getHeight(); y++) {
      for (var x = 0; x < outImage.getWidth(); x++) {
        // if(y == 64 && x == 64){
        //   System.out.println("Stop");
        // }
        

        this.colors[x][y] = new Vector3(0,0,0);

        var origin = camera.origin;
        var lookAt = camera.lookAt;
        var lookUp = camera.lookUp;
        var lookDirection = lookAt.minus(origin).normalize();
        var lookRight = lookUp.cross(lookDirection).normalize();
        
        var percentX = x/(float)outImage.getWidth();
        var percentY = y/(float)outImage.getHeight();
        var scaleX = percentX*2 - 1;
        var scaleY = percentY*2-1;

        //Invert because math and screens are opposite
        scaleY *= -1;
        var samples =100;

        for(var j = 0; j < samples; j++){
        var viewingPlanePoint = lookAt.plus(lookRight.scale(scaleX)).plus(lookUp.scale(scaleY));
        var direction = viewingPlanePoint.minus(origin).normalize();
        
        var worldSpacePixelSize = 2 * 1 / (float)outImage.getWidth();

        var jx = Math.random() * 2-1;
        var jy = Math.random() * 2-1;
        var jz = Math.random() * 2-1;

        var jitter = new Vector3(jx,jy,jz).scale(worldSpacePixelSize/2);
        direction = direction.plus(jitter).normalize();


        

        Ray ray = new Ray(origin, direction);

        float closestDistance = Float.MAX_VALUE;
        Vector3 closestColor = null;
        Vector3 closestNormal = null;

        for (var i = 0; i < meshes.length; i++) {
          var mesh = meshes[i];
          var material = mesh.material;
          var geometry = mesh.geometry;
          // float r = material.color.x;
          // float g = material.color.y;
          // float b = material.color.z;

          // Now do the ray cast.

          TAndNormal tn = geometry.intersect(ray);
          if (tn.t <= 0)
            continue;
          if (tn.t < closestDistance) {
            closestDistance = tn.t;
            closestNormal = tn.normal;
            //Now write a shader for this collision
            var closestPoint = ray.origin.plus(ray.direction.scale(tn.t));
            var fromDirection = ray.origin.minus(closestPoint).normalize();
            closestColor = material.Shade(fromDirection, closestPoint, closestNormal, lights[0]);
          }
        }

        if (closestColor == null)
          colors[x][y] = colors[x][y].plus(new Vector3(0, 0, 0));
        else {
          
          colors[x][y] = colors[x][y].plus(closestColor);
        }
      }

      colors[x][y] = colors[x][y].scale(1/(float)samples);
      }
    }

    save(outImage);
  }

  private void save(BufferedImage outImage) {
    for (var y = 0; y < outImage.getHeight(); y++) {
      for (var x = 0; x < outImage.getWidth(); x++) {
        Vector3 color = colors[x][y].clamp(0,1);
        float r = color.x;
        float g = color.y;
        float b = color.z;

        outImage.setRGB(x, y, new Color(r, g, b).getRGB());

      }
    }
  }

}
