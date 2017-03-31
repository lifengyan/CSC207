package phasetwo;

public class Replenisher implements Worker {

  private String name;

  public Replenisher(String name) {
    this.name = name; 
  }

  public String replenish(Warehouse wh, String[] userinput) {
    Integer zone = 0;
    if (userinput[3].equals("B")) {
      zone = 1;
    }
    Integer aile = Integer.valueOf(userinput[4]);
    Integer rack = Integer.valueOf(userinput[5]);
    Integer level = Integer.valueOf(userinput[6]);
    wh.getLevel(zone, aile, rack, level).set(30);
    return userinput[3] + userinput[4] + userinput[5] + userinput[6]
        + " has replenised, it has 30 Fasica now";
  }

  /**
   * return Replenisher`s name
   */
  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return name;
  }

  /***
   * reutrn worker`s id
   */
  @Override
  public int getid() {
    // TODO Auto-generated method stub
    return 0;
  }


}
