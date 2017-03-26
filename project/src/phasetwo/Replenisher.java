package phasetwo;

public class Replenisher implements Worker {

  private  String name;

  public Replenisher(String name) {
    this.name = name;    // TODO Auto-generated constructor stub
  }

  public void replenish() {
    System.out.println("Replenishing");
  }

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return name;
  }

  @Override
  public int getid() {
    // TODO Auto-generated method stub
    return 0;
  }


}
