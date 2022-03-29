public class Camera{
  public Vector3 origin;
  public Vector3 lookAt;
  public Vector3 lookUp;
  double halfWidth;

  public Camera(Vector3 origin, Vector3 lookAt, Vector3 lookUp, double halfWidth){
    this.origin = origin;
    this.lookAt = lookAt;
    this.lookUp = lookUp;
    this.halfWidth = halfWidth;
  }
  
}
