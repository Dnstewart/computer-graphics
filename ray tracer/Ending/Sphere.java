public class Sphere implements Geometry {

  public Vector3 center;
  public float radius;

  public Sphere(Vector3 center, float radius) {
    this.center = center;
    this.radius = radius;
  }

  @Override
  public Intersection intersect(Ray ray) {
    float A = ray.direction.dot(ray.direction);
    float B = 2 * ray.direction.dot(ray.origin.minus(center));
    Vector3 oc = ray.origin.minus(center);
    float C = oc.dot(oc) - radius * radius;

    float inSqrt = B * B - 4 * A * C;
    if (inSqrt < 0)
      return new Intersection(-1, null, null);

    float num1 = -1 * B - (float) Math.sqrt(B * B - 4 * A * C);
    float num2 = -1 * B + (float) Math.sqrt(B * B - 4 * A * C);
    float den = 2 * A;
    float t1 = num1 / den;
    float t2 = num2 / den;
    float t;

    
    if (t1 > 0 && t1 < t2)
      t = t1;
    else if (t1 < 0 && t2 > 0)
      t = t2;
    else
      return new Intersection(-1, null, null);

    var collisionPoint = ray.origin.plus(ray.direction.scale(t));
    var normal = (collisionPoint.minus(center)).normalize();
    return new Intersection(t, normal, null);
  }
}
