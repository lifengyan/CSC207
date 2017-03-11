package phase1;
//this class will be store Fasscia.
import java.util.ArrayList;


public class Level  {
	private ArrayList <Fascia> fasciaList = new ArrayList();
	
	public Level(){
	}
	
	/***
	 * add fascia in to the level
	 * @param newFascia
	 */
	public void add (Fascia newFascia){
		fasciaList.add(newFascia);
	}
	
	/***
	 * when picker remove the fasica, current warehouselevel
	 * will reduce one unite of fasica, and check if the  level need 
	 * to replenishing.
	 * 
	 * @return Fascia 
	 * 
	 */
	public Fascia removeOne(){
		Fascia retFascia = fasciaList.get(-1);
		fasciaList.remove(-1);
		this.Replenishing();
		return retFascia;
	}
	
	/***
	 * when there are exactly 5 of fascia left in the rack, ware will 
	 * replenishing 25 fascia from the reserve room
	 */
	public void Replenishing() {
		if (fasciaList.size()<=5){
			System.out.println("Replenishing");
			//need to add 25 more Fascia from the reserve room
			//in to the warehouse level.
		}
	}

}
