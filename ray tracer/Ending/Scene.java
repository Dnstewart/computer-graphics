import java.awt.image.BufferedImage;
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

        if(x == 256 && y == 256){
          System.out.println("Break");
        }
        
        this.colors[x][y] = new Vector3(0, 0, 0);

        var samples = 1024;

        var origin = camera.origin;
        var lookAt = camera.lookAt;
        var lookUp = camera.lookUp;
        var lookDirection = lookAt.minus(origin).normalize();
        var lookRight = lookUp.cross(lookDirection).normalize();

        var percentX = x / (float) outImage.getWidth();
        var percentY = y / (float) outImage.getHeight();
        var scaleX = percentX * 2 - 1;
        var scaleY = percentY * 2 - 1;

        scaleX *=1f;
        scaleY *= 1f;

        // Invert because math and screens are opposite
        scaleY *= -1;

        var viewingPlanePoint = lookAt.plus(lookRight.scale(scaleX)).plus(lookUp.scale(scaleY));

        var pixelWidth = 2 * lookRight.length() / outImage.getWidth();

        for (var s = 0; s < samples; s++) {

          var direction = viewingPlanePoint.minus(origin).normalize();
          var jx = Math.random() * 2 - 1;
          var jy = Math.random() * 2 - 1;
          var jz = Math.random() * 2 - 1;

          var jitter = new Vector3(jx, jy, jz).scale(.1f).scale(pixelWidth);
          direction = direction.plus(jitter).normalize();

          Ray ray = new Ray(origin, direction);

          Intersection intersection = this.shootRay(ray);

          
          if (intersection == null || intersection.mesh == null || intersection.normal == null)
            colors[x][y] = colors[x][y].plus(new Vector3(0, 0, 0));
          else {
            var collisionPosition = ray.origin.plus(ray.direction.scale(intersection.t));
            var fromDirection = ray.origin.minus(collisionPosition).normalize();
            var closestColor = intersection.mesh.material.Shade(
                fromDirection,
                collisionPosition,
                intersection.normal, 
                lights[0],
                this,
                10);

            colors[x][y] = colors[x][y].plus(closestColor);
          }
        }
        colors[x][y] = colors[x][y].scale(1 / (float) samples);

      }
    }

    save(outImage);
  }

  public Intersection shootRay(Ray ray) {
    float closestDistance = Float.MAX_VALUE;
    Vector3 closestColor = null;
    Vector3 closestNormal = null;
    int closestMesh = -1;
    for (var i = 0; i < meshes.length; i++) {
      var mesh = meshes[i];
      var material = mesh.material;
      var geometry = mesh.geometry;

      // Now do the ray cast.

      Intersection tn = geometry.intersect(ray);
      if (tn.t <= 0)
        continue;
      if (tn.t < closestDistance) {
        closestMesh = i;
        closestDistance = tn.t;
        closestNormal = tn.normal;
        tn.mesh = mesh;
        // Do the shading
        var closestPoint = ray.origin.plus(ray.direction.scale(tn.t));
        var fromDirection = ray.origin.minus(closestPoint).normalize();
      }
    }
    if (closestMesh == -1)
      return null;
    return new Intersection(
        closestDistance,
        closestNormal,
        closestMesh == -1 ? null : meshes[closestMesh]);
  }

  private void save(BufferedImage outImage) {
    for (var y = 0; y < outImage.getHeight(); y++) {
      for (var x = 0; x < outImage.getWidth(); x++) {
        Vector3 color = colors[x][y];
        float r = color.x;
        float g = color.y;
        float b = color.z;

        outImage.setRGB(x, y, new Color(r, g, b).getRGB());

      }
    }
  }

}
