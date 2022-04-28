public class ReflectiveMaterial implements Material {
  private Vector3 color;

  public ReflectiveMaterial(Vector3 color) {
    this.color = color;
  }

  public Vector3 Shade(Vector3 fromDirection, Vector3 position, Vector3 normal, DirectionalLight directionalLight,
      Scene scene, int remainingBounces) {

    Vector3 shadowRayDirection = directionalLight.directionToLight;
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

    Vector3 finalColorPhong;

    if (!inShadow)
      finalColorPhong = ambient.plus(diffuse).plus(specular);
    else
      finalColorPhong = ambient;

    finalColorPhong = finalColorPhong.clamp(0, 1);

    Vector3 finalColorReflective;

    if (remainingBounces <= 0) {
      finalColorReflective = Vector3.Zero;
    } else {

      Vector3 reflectionDirection = fromDirection.reflect(normal);
      Vector3 reflectionOrigin = position.plus(reflectionDirection.scale(.1f));
      var ray = new Ray(reflectionOrigin, reflectionDirection);
      Intersection intersection2 = scene.shootRay(ray);

      if (intersection2 == null) {
        finalColorReflective = new Vector3(0, 0, 0);
      }

      else {
        var collisionPosition = ray.origin.plus(ray.direction.scale(intersection2.t));
        var fromDirection2 = ray.origin.minus(collisionPosition).normalize();
        var closestColor = intersection2.mesh.material.Shade(
            fromDirection2,
            collisionPosition,
            intersection2.normal,
            directionalLight,
            scene,
            --remainingBounces);
        finalColorReflective = closestColor.scale(.9f);
      }
    }

    return finalColorPhong.scale(.5f).plus(finalColorReflective.scale(.5f));
  }

}
