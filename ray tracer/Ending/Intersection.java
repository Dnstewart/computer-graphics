public class Intersection {
  public float t;
  public Vector3 normal;
  public Mesh mesh;

  public Intersection(float t, Vector3 normal, Mesh mesh) {
    this.t = t;
    this.normal = normal;
    this.mesh = mesh;
  }

}
