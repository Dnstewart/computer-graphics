public class Plane implements Geometry {
  public Vector3 ABC;
  public float D;

  public Plane(Vector3 ABC, float D) {
    this.ABC = ABC;
    this.D = D;
  }

  @Override
  public TAndNormal intersect(Ray ray) {
    var num = -D-ABC.dot(ray.origin);
    var den = ABC.dot(ray.direction);
    var T = num/den;
    return new TAndNormal(T,ABC);
  }
}
