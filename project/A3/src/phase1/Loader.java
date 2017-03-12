package phase1;

public class Loader extends Worker{

  public Loader(String name) {
    super(name);
  }
  
  public void load() {
    System.out.println("Loading");
  }
}
