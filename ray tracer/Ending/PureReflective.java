public class PureReflective implements Material {

  public PureReflective() {

  }

  public Vector3 Shade(Vector3 fromDirection, Vector3 position, Vector3 normal, DirectionalLight directionalLight,
      Scene scene, int remainingBounces) {

    if (remainingBounces <= 0) {
      return Vector3.Zero;
    }

    Vector3 reflectionDirection = fromDirection.reflect(normal);
    var jx = Math.random() * 2 - 1;
    var jy = Math.random() * 2 - 1;
    var jz = Math.random() * 2 - 1;

    var jitter = new Vector3(jx, jy, jz).scale(.5f);

    reflectionDirection = reflectionDirection.plus(jitter).normalize();

    Vector3 reflectionOrigin = position.plus(reflectionDirection.scale(.1f));
    var ray = new Ray(reflectionOrigin, reflectionDirection);
    Intersection intersection = scene.shootRay(ray);

    if (intersection == null) {
      return new Vector3(0, 0, 0);
    }

    else {
      var collisionPosition = ray.origin.plus(ray.direction.scale(intersection.t));
      var fromDirection2 = ray.origin.minus(collisionPosition).normalize();
      var closestColor = intersection.mesh.material.Shade(
          fromDirection2,
          collisionPosition,
          intersection.normal,
          directionalLight,
          scene,
          --remainingBounces);
      return closestColor.scale(.9f);
    }

  }

}
