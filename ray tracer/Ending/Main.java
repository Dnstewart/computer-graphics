import java.awt.image.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import javax.imageio.ImageIO;

class Main {
  public static void main(String[] args) {
    //tests();
    try {
      long startTime = System.currentTimeMillis();
      System.out.println("Up and running");

      var width = 512;
      var height = 512;

      BufferedImage outImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

      // Camera points
      var cameraOrigin = new Vector3(.0f, 0f, -1f);
      var cameraLookAt = new Vector3(0, 0, 0);
      var cameraLookUp = new Vector3(0, 1, 0);
      var halfWidth = Math.PI / 4;

      var camera = new Camera(cameraOrigin, cameraLookAt, cameraLookUp, halfWidth);

      // Light points

      // Load an obj
      List<Triangle> objTriangles = new ArrayList<>();
      List<Vector3> vertices = new ArrayList<>();
      try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/dyl_4/Desktop/intro to game design/ray tracer/Ending/raytrace.obj"))) {
        String line;
        while ((line = br.readLine()) != null) {
          if (line.startsWith("v ")) {
            String[] parts = line.split(" ");
            var x = Float.parseFloat(parts[1]);
            var y = Float.parseFloat(parts[2]);
            var z = Float.parseFloat(parts[3]);
            Vector3 vertex = new Vector3(x, y, z);
            vertices.add(vertex);
          } else if (line.startsWith("f ")) {
            String[] firstParts = line.split(" ");

            var indexOne = Integer.parseInt(firstParts[1].split("/")[0]);
            var indexTwo = Integer.parseInt(firstParts[2].split("/")[0]);
            var indexThree = Integer.parseInt(firstParts[3].split("/")[0]);
            var one = vertices.get(indexOne - 1);
            var two = vertices.get(indexTwo - 1);
            var three = vertices.get(indexThree - 1);
            var objTriangle = new Triangle(one, two, three);
            objTriangles.add(objTriangle);
          }
        }
      }
      System.out.println("Read the obj file");
      // We are done reading the obj file
      Triangle[] allObjTriangles = objTriangles.toArray(new Triangle[0]);
      Mesh[] allObjMeshes = new Mesh[allObjTriangles.length];
      for (var i = 0; i < allObjTriangles.length; i++) {
        var mesh = new Mesh(allObjTriangles[i], Materials.phongBlue);
        allObjMeshes[i] = mesh;
      }

      List<Mesh> meshes = new ArrayList<>();
      meshes.add(Meshes.planeMesh1);
      
      // meshes.add(Meshes.sphereMesh1);
      // meshes.add(Meshes.sphereMesh2);
      // meshes.add(Meshes.sphereMesh3);
      /// meshes.add(Meshes.sphereMesh4);
      // meshes.add(Meshes.sphereMesh5);
      //meshes.addAll(allObjMeshes);
      Collections.addAll(meshes, allObjMeshes);
      Mesh[] allMeshes = meshes.toArray(new Mesh[0]);

      var scene = new Scene(new DirectionalLight[] { Lights.fromAbove }, camera, allMeshes);

      scene.render(outImage);

      ImageIO.write(outImage, "png", new File("C:/Users/dyl_4/Desktop/intro to game design/ray tracer/Ending/out/out.png"));

      long endTime = System.currentTimeMillis();
      long runtime = endTime - startTime;
      System.out.println("Done in " + runtime + " millisecond(s)");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // public static void tests() {
  //   var v0 = new Vector3(0, 0, 0);
  //   var v1 = new Vector3(1, 1, 1);
  //   var v2 = new Vector3(2, 2, 2);
  //   var v3 = new Vector3(-3, 2, -1);

  //   // Test the dot products
  //   var dot0 = v0.dot(v1);
  //   if (dot0 != 0)
  //     System.out.println("Error");

  //   var dot1 = v1.dot(v2);
  //   if (dot1 != 6)
  //     System.out.println("Error");

  //   // Test the normalization
  //   var n1 = v1.normalize();
  //   if (!n1.nearlyEquals(new Vector3(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3))))
  //     System.out.println("Error");

  //   var n2 = v2.normalize();
  //   if (!n2.nearlyEquals(new Vector3(1 / Math.sqrt(3), 1 / Math.sqrt(3), 1 / Math.sqrt(3))))
  //     System.out.println("Error");

  //   // Test the length
  //   if (v0.length() != 0)
  //     System.out.println("Error");

  //   if (v1.length() != (float) Math.sqrt(3))
  //     System.out.println("Error");

  //   if (v2.length() != (float) Math.sqrt(12))
  //     System.out.println("Error");

  //   // Test the squared length

  //   if (v0.lengthSquared() != 0)
  //     System.out.println("Error");

  //   if (v1.lengthSquared() != 3)
  //     System.out.println("Error");

  //   if (v2.lengthSquared() != 12)
  //     System.out.println("Error");

  //   // Test the cross product
  //   if (!v0.cross(v1).nearlyEquals(new Vector3(0, 0, 0)))
  //     System.out.println("Error");

  //   if (!v1.cross(v2).nearlyEquals(new Vector3(0, 0, 0)))
  //     System.out.println("Error");

  //   if (!v1.cross(v3).nearlyEquals(new Vector3(-3, -2, 5)))
  //     System.out.println("Error");

  //   if (!v2.cross(v3).nearlyEquals(new Vector3(-6, -4, 10)))
  //     System.out.println("Error");

  //   // Test addition
  //   if (!v0.plus(v1).nearlyEquals(v1))
  //     System.out.println("Error");

  //   if (!v1.plus(v2).nearlyEquals(new Vector3(3, 3, 3)))
  //     System.out.println("Error");

  //   if (!v1.plus(v3).nearlyEquals(new Vector3(-2, 3, 0)))
  //     System.out.println("Error");

  //   // Test substraction
  //   if (!v0.minus(v1).nearlyEquals(v1.negate()))
  //     System.out.println("Error");

  //   if (!v1.minus(v0).nearlyEquals(v1))
  //     System.out.println("Error");

  //   if (!v3.minus(v2).nearlyEquals(new Vector3(-5, 0, -3)))
  //     System.out.println("Error");

  //   // Test negation
  //   if (!v0.negate().equals(v0))
  //     System.out.println("Error");

  //   if (!v1.negate().equals(new Vector3(-1, -1, -1)))
  //     System.out.println("Error");

  //   // Test scaling
  //   if (!v1.scale(2).equals(new Vector3(2, 2, 2)))
  //     System.out.println("Error");

  //   // Test reflections
  //   var normal = new Vector3(0, 0, 1);
  //   var toLight = new Vector3(1 / Math.sqrt(2), 0, 1 / Math.sqrt(2));
  //   var reflection = toLight.reflect(normal);
  //   if (!reflection.nearlyEquals(
  //       new Vector3(-1 / Math.sqrt(2), 0, 1 / Math.sqrt(2))))
  //     System.err.println("Bad Reflection");

  //   normal = new Vector3(1, 0, 0);
  //   toLight = new Vector3(0, 0, 1);
  //   reflection = toLight.reflect(normal);
  //   if (!reflection.nearlyEquals(
  //       new Vector3(0, 0, -1)))
  //     System.err.println("Bad Reflection");

  //   // Test planes from points
  //   Vector3[] points = new Vector3[3];
  //   points[0] = new Vector3(0, 0, 0);
  //   points[1] = new Vector3(1, 0, 0);
  //   points[2] = new Vector3(0, 1, 0);

  //   Plane pointsPlane = new Plane(points);
  //   if (!pointsPlane.ABC.equals(new Vector3(0, 0, -1)) || pointsPlane.D != 0) {
  //     System.out.println("Bad plane points");
  //   }

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
  //}
}