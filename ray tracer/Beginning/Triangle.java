public class Triangle implements Geometry{

  public Vector3[] points = new Vector3[3];

  public Triangle(Vector3[] points){
    for(var i = 0; i < 3; i++){
      this.points[i] = points[i];
    }
  }

  public Triangle(Vector3 one, Vector3 two, Vector3 three){
    this.points[0] = one;
    this.points[1] = two;
    this.points[2] = three;
  }

  public Vector3 get(int index){
    return this.points[index];
  }

  public Vector3 getOne(){
    return this.points[0];
  }
  
  public Vector3 getTwo(){
    return this.points[1];
  }
  public Vector3 getThree(){
    return this.points[2];
  }

  public void setOne(Vector3 one){
    this.points[0] = one;
  }
  
  public void setTwo(Vector3 two){
    this.points[1] = two;
  }
  public void setThree(Vector3 three){
    this.points[2] = three;
  }

  @Override
  public TAndNormal intersect(Ray ray) {

    // https://www.scratchapixel.com/lessons/3d-basic-rendering/ray-tracing-rendering-a-triangle/ray-triangle-intersection-geometric-solution
    //triangles normal
    Vector3 v1v2 = this.getTwo().minus(this.getOne());
    Vector3 v1v3 = this.getThree().minus(this.getOne());
    Vector3 N = v1v2.cross(v1v3);
    var area2 = N.length();

    // find p

    var NdotRayDirection = N.dot(ray.direction);
    //if zero or lower return beacuse they will not intersect infront of the camera
    if (NdotRayDirection < 0.00001){
      return new TAndNormal(1, null);
    }
    //get d and t
    var d = -N.dot(this.getOne());
    // (N * O + d) /(N * R)
    var t = -(N.dot(ray.origin) + d) / NdotRayDirection;

    //see if triangle is behind the ray
    if (t < 0){
      return new TAndNormal(1, null);
    }

    //get intersection point
    Vector3 P = ray.origin.plus(ray.direction.scale(t));

    //in out test
    Vector3 C;

    //edge 1
    Vector3 edge1 = this.getTwo().minus(this.getOne());
    Vector3 vecP1 = P.minus(this.getOne());
    C = edge1.cross(vecP1);
    //sees if inside
    if (N.dot(C) < 0){
      return new TAndNormal(1, null);
    }

    //edge 2
    Vector3 edge2 = this.getThree().minus(this.getTwo());
    Vector3 vecP2 = P.minus(this.getTwo());
    C = edge2.cross(vecP2);
    //sees if inside
    if (N.dot(C) < 0){
      return new TAndNormal(1, null);
    }

    //edge 3
    Vector3 edge3 = this.getOne().minus(this.getThree());
    Vector3 vecP3 = P.minus(this.getThree());
    C = edge3.cross(vecP3);
    //sees if inside
    if (N.dot(C) < 0){
      return new TAndNormal(1, null);
    }

    return new TAndNormal(t,N);
  }

  
}
