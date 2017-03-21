package phasetwo;

// this class will be store Fascia.
import java.util.ArrayList;


public class Level {
	// each Level will use fasciaList to store all the Fascia 
  private ArrayList<Fascia> fasciaList = new ArrayList<>();
  
  //Constructor
  public Level() {}

  /**
   * Add new any number of Fascia in to level.
   * 
   * @param numberOfFascia a integer
   */
  public void add(int numberOfFascia) {
    for (int i = 0; i < numberOfFascia; i++) {
      fasciaList.add(new Fascia());
    }
  }
  
  /***
   * this method allow warehouse build the 
   * @param numberOfFascia
   */
  public void set(int numberOfFascia) {
	  int differnt = fasciaList.size()-numberOfFascia;
		  for(int i = 0; i< differnt; i++){
			  fasciaList.remove(0);
		  }

	 
	  }

  /**
   * when picker remove the fascia, current level will reduce one unite of fascia, and
   * check if the level need to replenishing.
   * 
   * @return Fascia
   * 
   */
  public Fascia removeOne() {
    Fascia retFascia = fasciaList.get(fasciaList.size() - 1);
    fasciaList.remove(fasciaList.size() - 1);
    this.replenishing();
    return retFascia;
  }

  /***
   * report how many Fascia left 
   * @return
   */
  public int report() {
    return fasciaList.size();
  }

  /**
   * when there are exactly 5 of fascia left in the rack, ware will replenishing 25 fascia from the
   * reserve room.
   */
  public void replenishing() {
    if (fasciaList.size() <= 5) {
      System.out.println("Replenishing");
      // need to add 25 more Fascia from the reserve room
      // in to the warehouse level.
    }
  }

}
