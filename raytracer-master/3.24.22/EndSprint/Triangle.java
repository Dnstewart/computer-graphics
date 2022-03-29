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
    // TODO Auto-generated method stub
    return null;
  }

  
}
