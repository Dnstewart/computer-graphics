public class PhongMaterial implements Material {
  private Vector3 color;

  public PhongMaterial(Vector3 color) {
    this.color = color;
  }

  public Vector3 Shade(Vector3 fromDirection, Vector3 position, Vector3 normal, DirectionalLight directionalLight,
      Scene scene, int remainingBounces) {

    Vector3 shadowRayDirection = directionalLight.directionToLight;
    var jx = Math.random() * 2 - 1;
    var jy = Math.random() * 2 - 1;
    var jz = Math.random() * 2 - 1;

    var jitter = new Vector3(jx, jy, jz).scale(.01f);

        shadowRayDirection = shadowRayDirection.plus(jitter).normalize();

    Vector3 shadowRayOrigin = position.plus(shadowRayDirection.scale(.1f));
    Intersection intersection = scene.shootRay(new Ray(shadowRayOrigin, shadowRayDirection));

    var inShadow = false;
    if (intersection != null) {
      inShadow = true;
    }

    var ambient = new Vector3(.1f, .1f, .1f);

    var diffuseStrength = normal.normalize().dot(directionalLight.directionToLight.normalize());
    diffuseStrength = Math.max(0, diffuseStrength);

    var diffuse = this.color.scale(diffuseStrength);

    // Find the reflection of the light with regards to the normal
    // compare that to the from direction

    var reflection = directionalLight.directionToLight.reflect(normal);
    var specularStrength = Math.max(0, reflection.dot(fromDirection));
    specularStrength = (float) Math.pow(specularStrength, 4);

    var specular = new Vector3(1f, 1f, 1f).scale(specularStrength);

    Vector3 finalColor;

    if (!inShadow)
      finalColor = ambient.plus(diffuse).plus(specular);
    else
      finalColor = ambient;

    finalColor = finalColor.clamp(0, 1);

    return finalColor;
    // return this.color;
  }

}
