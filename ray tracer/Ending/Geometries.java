public class Geometries {
  static Vector3 pointOne = new Vector3(0, 0, 0);
  static Vector3 pointTwo = new Vector3(1, 0, 0);
  static Vector3 pointThree = new Vector3(0, 1, 0);
  static Triangle triangle = new Triangle(pointOne, pointTwo, pointThree);

  static Plane plane1 = new Plane(new Vector3(0, 1, 0), 1);
  static Plane plane2 = new Plane(new Vector3(0, -1 / Math.sqrt(2), -1 / Math.sqrt(2)), 1);

  static Sphere sphere1 = new Sphere(new Vector3(-.5, 0, 1), .25f);
  static Sphere sphere2 = new Sphere(new Vector3(0, 0, 1), .25f);
  static Sphere sphere3 = new Sphere(new Vector3(.5, 0, 1), .25f);
  static Sphere sphere4 = new Sphere(new Vector3(1.0, 0, 1), .25f);
  static Sphere sphere5 = new Sphere(new Vector3(-1.0, 0, 1), .25f);

}
