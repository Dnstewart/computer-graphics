public class Materials {
  static Material solidRed = new SolidMaterial(new Vector3(1, 0, 0));
  static Material solidGreen = new SolidMaterial(new Vector3(0, 1, 0));
  static Material solidBlue = new SolidMaterial(new Vector3(0, 0, 1));

  static Material phongRed = new PhongMaterial(new Vector3(1, 0, 0));
  static Material phongGreen = new PhongMaterial(new Vector3(0, 1, 0));
  static Material phongBlue = new PhongMaterial(new Vector3(0, 0, 1));
  static Material phongWhite = new PhongMaterial(new Vector3(1, 1, 1));
  static Material checkeredPhong = new CheckeredPhong(new Vector3(1, 1, 1));

  static Material pureReflective = new PureReflective();
  static Material reflectiveRed = new ReflectiveMaterial(new Vector3(1, 0, 0));
  static Material reflectiveGreen = new ReflectiveMaterial(new Vector3(0, 1, 0));
  static Material reflectiveBlue = new ReflectiveMaterial(new Vector3(0, 0, 1));

}
