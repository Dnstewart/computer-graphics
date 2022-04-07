import java.awt.image.*;
import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;

class Main {
  public static void main(String[] args) {
    try {
      long startTime = System.currentTimeMillis();
      System.out.println("Up and running");

      var width = 200;//128
      var height = 200;//128

      BufferedImage outImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

      // Create our geometry

      var pointOne = new Vector3(0, 1, 0);
      var pointTwo = new Vector3(-.5, .5, 0);
      var pointThree = new Vector3(.5, .5, 0);
      var triangle1 = new Triangle(pointOne, pointTwo, pointThree);
      var point4 = new Vector3(0, 0, 0);
      var point5 = new Vector3(-.5, 0, 0);
      var point6 = new Vector3(0, .5, 0);
      var triangle2 = new Triangle(pointOne, pointTwo, pointThree);

      var plane1 = new Plane(new Vector3(0, 0, .8), 1);
      var plane2 = new Plane(new Vector3(0, -1 / Math.sqrt(2), -1 / Math.sqrt(2)), 1);

      var sphere1 = new Sphere(new Vector3(0, 0, 0), .2f);
      var sphere2 = new Sphere(new Vector3(-.5, 0, 0), .2f);
      var sphere3 = new Sphere(new Vector3(.5, 0, 0), .2f);

      var material1 = new SolidMaterial(new Vector3(0, 1, 0));//green
      var material2 = new SolidMaterial(new Vector3(1, 0, 0));//red
      var material3 = new SolidMaterial(new Vector3(0, 0, 1));//blue
      var material4 = new SolidMaterial(new Vector3(0, 0, 0));//black
      var material5 = new SolidMaterial(new Vector3(1, 1, 1));//white
      var phong1 = new PhongMaterial(new Vector3(0, 1, 1));
      var phong2 = new PhongMaterial(new Vector3(0, 0, 0));
      var phong3 = new PhongMaterial(new Vector3(0, 0, 0));

      var planeMesh1 = new Mesh(plane1, material5);
      var planeMesh2 = new Mesh(plane2, material5);

      var sphereMesh1 = new Mesh(sphere1, phong1);
      var sphereMesh2 = new Mesh(sphere2, material2);
      var sphereMesh3 = new Mesh(sphere3, material3);

      var triangleMesh1 = new Mesh(triangle1, material1);
      var triangleMesh2 = new Mesh(triangle2, material4);

      // Camera points
      var cameraOrigin = new Vector3(0, 0,-1);
      var cameraLookAt = new Vector3(0, 0, 0);
      var cameraLookUp = new Vector3(0, 1, 0);
      var halfWidth = Math.PI / 4;

      var camera = new Camera(cameraOrigin, cameraLookAt, cameraLookUp, halfWidth);

      // Light points
      var light = new DirectionalLight(new Vector3(1, 1, 1).normalize(), 1);

      var scene = new Scene(new DirectionalLight[] { light }, camera, new Mesh[] {
        triangleMesh1,
        triangleMesh2,  
        planeMesh1,
        // planeMesh2,
        sphereMesh1,
        sphereMesh2,
        sphereMesh3,
      });

      scene.render(outImage);

      ImageIO.write(outImage, "png", new File("./out.png"));

      long endTime = System.currentTimeMillis();
      long runtime = endTime - startTime;
      System.out.println("Done in " + runtime + " millisecond(s)");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

    // Test ray/plane intersections
    // try{
    // Ray ray = new Ray(new Vector3(0,0,-1), new Vector3(0,0,1));
    // Plane plane = new Plane(new Vector3(0,0,-1), 1);
    // float t = plane.intersect(ray).t;

    // if(t != 2)
    // {
    // System.out.println("Error");
    // }
    // }catch (Exception e){
    // System.out.println("Error");
    // }
  }