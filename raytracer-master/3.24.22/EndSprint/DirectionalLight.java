public class DirectionalLight {

  public Vector3 directionToLight;
  public double intensity;

  public DirectionalLight(Vector3 directionToLight, double intensity){
    this.directionToLight = directionToLight;
    this.intensity = intensity;
  }

  public DirectionalLight normalize(){
    directionToLight = directionToLight.normalize();
    return this;
  }
  
}
