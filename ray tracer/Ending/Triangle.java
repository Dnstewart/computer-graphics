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
  public Intersection intersect(Ray ray) {
    Plane plane = new Plane(points);
    Intersection planeTAndNormal = plane.intersect(ray);

    Vector3 collision = ray.origin.plus(ray.direction.scale(planeTAndNormal.t));
    var abc = planeTAndNormal.normal;

    Plane plane0 = new Plane(points[1], points[0], points[1].plus(abc));
    Plane plane1 = new Plane(points[2], points[1], points[2].plus(abc));
    Plane plane2 = new Plane(points[0], points[2], points[0].plus(abc));

    float offset1 = plane0.offset(collision);
    float offset2 = plane1.offset(collision);
    float offset3 = plane2.offset(collision);

    if(offset1 >= 0 && offset2 >= 0 && offset3 >= 0){
      return planeTAndNormal;
    }

    return new Intersection(-1, null, null);
  }

  
}
