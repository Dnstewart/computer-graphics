public class CheckeredPhong implements Material{
  private Vector3 color;

  public CheckeredPhong(Vector3 color){
    this.color = color;
  }
  public Vector3 Shade(Vector3 fromDirection, Vector3 position, Vector3 normal, DirectionalLight directionalLight, Scene scene, int remainingBounces){
    return this.color;
  }

  
  
}
