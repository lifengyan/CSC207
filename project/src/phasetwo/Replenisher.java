package phasetwo;

public class Replenisher extends Worker {

  public Replenisher(String name) {
    super(name);
  }

  public void replenish() {
    System.out.println("Replenishing");
  }


}
