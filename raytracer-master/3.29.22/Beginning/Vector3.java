public class Vector3 {

  public float x, y, z;

  public Vector3(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Vector3(double x, double y, double z){
    this((float)x, (float)y, (float)z);
  }

  public Vector3 normalize() {
    var length = this.length();
    var toReturn = new Vector3(x/length, y/length, z/length);
    return toReturn;

  }

  public float length() {
    return (float)Math.sqrt(this.lengthSquared());
  }

  public float lengthSquared() {
    return x * x + y * y + z * z;
  }

  public float dot(Vector3 other){
    return x*other.x + y*other.y+z*other.z;
  }

  //(a_y\cdot b_z-a_z\cdot b_y, a_z\cdot b_x-a_x\cdot b_z, a_x\cdot b_y - a_y\cdot b_x)
  public Vector3 cross(Vector3 other){
    var x1  = y*other.z-z*other.y;
    var y1 = z*other.x-x*other.z;
    var z1 = x*other.y-y*other.x;
    return new Vector3(x1,y1,z1);
  }

  public boolean equals(Vector3 other){
    return x == other.x && y == other.y && z == other.z;
  }

  public float absoluteDifference(Vector3 other){
    return Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z);
  }

  public boolean nearlyEquals(Vector3 other){
    return absoluteDifference(other) < .0001;
  }

  public Vector3 plus(Vector3 other){
    return new Vector3(x+other.x, y+other.y, z+other.z);
  }

  public Vector3 minus(Vector3 other){
    return new Vector3(x - other.x, y - other.y, z - other.z);
  }

  public Vector3 negate(){
    return new Vector3(-x, -y, -z);
  }

  public Vector3 scale(float scale){
    return new Vector3(x*scale, y*scale, z*scale);
  }

}
