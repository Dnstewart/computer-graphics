import java.util.ArrayList;
import java.util.List;

public class Mesh {

  public Geometry geometry;
  public Material material;

  public Mesh(Geometry geometry, Material material){
    this.geometry = geometry;
    this.material = material;
  }
}
