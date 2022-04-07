public class SolidMaterial implements Material {

  public Vector3 color;
  public SolidMaterial(Vector3 color){
    this.color = color;
  }
  public Vector3 Shade(Vector3 fromDirection, Vector3 position, Vector3 normal, DirectionalLight directionalLight){
    return this.color;
  }
  
}
