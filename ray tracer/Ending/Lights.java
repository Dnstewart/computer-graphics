public class Lights {
  static DirectionalLight shoulderLight = new DirectionalLight(new Vector3(1, 1, -1).normalize(), 1);
  static DirectionalLight fromCamera = new DirectionalLight(new Vector3(0, 0, -1).normalize(), 1);
  static DirectionalLight fromAbove = new DirectionalLight(new Vector3(0, 1, -.0f).normalize(), 1);

}
