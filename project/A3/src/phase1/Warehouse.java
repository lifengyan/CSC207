package phase1;

import java.util.ArrayList;
import java.util.Observable;

public class Warehouse extends Observable {
	//this two ArrayList use to represent two Zone inside the warehouse,
	//the order followed by aisles,racks,level and in side the level there
	//are 30 space to put down the Fascia.
	static ArrayList<Fascia>[][][][] ZoneA = new ArrayList [2][3][4][30];
	static ArrayList<Fascia>[][][][] ZoneB = new ArrayList [2][3][4][30];
	
	private  String WarehouseName;
	
	/***
	 * construct the warehouse
	 */
	public Warehouse(){}
	
	public Warehouse(String WarehouseName){
		this.WarehouseName = WarehouseName;
	}
	
	/***
	 * this function is use to initial ware house state in the beginning.
	 * it will read the csv file and input all the data into the system.
	 */
	public void StorageInital(){
		return; // do something here
	} 
	
	
	/***
	 * When a pick scean the barcode, it will tell the system it take 
	 * the Fascia from the warehouse. The warehouse will make a note, 
	 * and check if that rack need resupply.
	 * @param zone
	 * @param aisles
	 * @param racks
	 * @param level
	 * @return
	 */
	
	public Fascia getFascia (String zone,int aisles,int racks,int level){ 
		// do something here
//		if (zone =="zonea"){
//			ZoneA[aisles][racks][level].remove;
//			return ZoneA[aisles][racks][level].pop;//
	}
	
	/***
	 * when there are exactly 5 of fascia left in the rack, ware will 
	 * replenishing 25 fascia from the reserve room
	 */
	public void replenishing (){
		//do something here
	}
	
	
	
	
	}
	
	

}
