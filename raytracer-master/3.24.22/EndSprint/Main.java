import java.awt.image.*;
import java.awt.*;
import java.io.File;

import javax.imageio.ImageIO;

class Main {
  public static void main(String[] args) {
    tests();
    try {
      long startTime = System.currentTimeMillis();
      System.out.println("Up and running");

      var width = 128;
      var height = 128;

      BufferedImage outImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

      // Create our geometry

      var pointOne = new Vector3 (0, 0, 0);
      var pointTwo = new Vector3(1, 0, 0);
      var pointThree = new Vector3(0, 1, 0);
      var triangle = new Triangle(pointOne, pointTwo, pointThree);
      
      var plane1 = new Plane(new Vector3(0,0,-1), 1);
      var plane2 = new Plane(new Vector3(0,-1/Math.sqrt(2),-1/Math.sqrt(2)), 1);

      var sphere1 = new Sphere(new Vector3(0,0,0), .5f);

      var material1 = new Material(new Vector3(0,1,0));
      var material2 = new Material(new Vector3(1,0,0));
      var material3 = new Material(new Vector3(0,0,1));

      var planeMesh1 = new Mesh(plane1, material1);
      var planeMesh2 = new Mesh(plane2, material2);

      var sphereMesh1 = new Mesh(sphere1, material3);

      // Camera points
      var cameraOrigin = new Vector3(0, 0, -1);
      var cameraLookAt = new Vector3(0, 0, 0);
      var cameraLookUp = new Vector3(0, 1, 0);
      var halfWidth = Math.PI / 4;

      var camera = new Camera(cameraOrigin, cameraLookAt, cameraLookUp, halfWidth);

      // Light points
      var light = new DirectionalLight(new Vector3(1, 1, 1).normalize(), 1);

      var scene = new Scene(new DirectionalLight[] { light }, camera, new Mesh[] {
         planeMesh1, 
         
        });

      scene.render(outImage);

      ImageIO.write(outImage, "png", new File("./out/out.png"));

      long endTime = System.currentTimeMillis();
      long runtime = endTime - startTime;
      System.out.println("Done in " + runtime + " millisecond(s)");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void tests() {
    var v0 = new Vector3(0, 0, 0);
    var v1 = new Vector3(1, 1, 1);
    var v2 = new Vector3(2, 2, 2);
    var v3 = new Vector3(-3, 2, -1);

    // Test the dot products
    var dot0 = v0.dot(v1);
    if (dot0 != 0)
      System.out.println("Error");

    var dot1 = v1.dot(v2);
    if (dot1 != 6)
      System.out.println("Error");

    // Test the normalization
    var n1 = v1.normalize();
    if (!n1.nearlyEquals(new Vector3(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3))))
      System.out.println("Error");

    var n2 = v2.normalize();
    if (!n2.nearlyEquals(new Vector3(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3))))
      System.out.println("Error");

    // Test the length
    if (v0.length() != 0)
      System.out.println("Error");

    if (v1.length() != (float)Math.sqrt(3))
      System.out.println("Error");

    if (v2.length() != (float)Math.sqrt(12))
      System.out.println("Error");

    // Test the squared length

    if (v0.lengthSquared() != 0)
      System.out.println("Error");

    if (v1.lengthSquared() != 3)
      System.out.println("Error");

    if (v2.lengthSquared() != 12)
      System.out.println("Error");

    // Test the cross product
    if (!v0.cross(v1).nearlyEquals(new Vector3(0, 0, 0)))
      System.out.println("Error");

    if (!v1.cross(v2).nearlyEquals(new Vector3(0, 0, 0)))
      System.out.println("Error");

    if (!v1.cross(v3).nearlyEquals(new Vector3(-3, -2, 5)))
      System.out.println("Error");

    if (!v2.cross(v3).nearlyEquals(new Vector3(-6, -4, 10)))
      System.out.println("Error");

    // Test addition
    if (!v0.plus(v1).nearlyEquals(v1))
      System.out.println("Error");

    if (!v1.plus(v2).nearlyEquals(new Vector3(3, 3, 3)))
      System.out.println("Error");

    if (!v1.plus(v3).nearlyEquals(new Vector3(-2, 3, 0)))
      System.out.println("Error");

    // Test substraction
    if (!v0.minus(v1).nearlyEquals(v1.negate()))
      System.out.println("Error");

    if (!v1.minus(v0).nearlyEquals(v1))
      System.out.println("Error");

    if (!v3.minus(v2).nearlyEquals(new Vector3(-5, 0, -3)))
      System.out.println("Error");

    // Test negation
    if (!v0.negate().equals(v0))
      System.out.println("Error");

    if (!v1.negate().equals(new Vector3(-1, -1, -1)))
      System.out.println("Error");

      //Test scaling
      if(!v1.scale(2).equals(new Vector3(2,2,2)))
        System.out.println("Error");


      //Test ray/plane intersections
      Ray ray = new Ray(new Vector3(0,0,-1), new Vector3(0,0,1));
      Plane plane = new Plane(new Vector3(0,0,-1), 1);
      float t = plane.intersect(ray).t;

      if(t != 2)
      {
        System.out.println("Error");
      }
  }
}