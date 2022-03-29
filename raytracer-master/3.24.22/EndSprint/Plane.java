public class Plane implements Geometry {
  public Vector3 ABC;
  public float D;

  public Plane(Vector3 ABC, float D) {
    this.ABC = ABC;
    this.D = D;
  }

  @Override
  public TAndNormal intersect(Ray ray) {

    // TODO Auto-generated method stub
    return null;
  }
}
