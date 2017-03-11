package phase1;

import java.util.ArrayList;
import java.util.Observable;

public class Warehouse {
	//this two ArrayList use to represent two Zone inside the warehouse,
	//the order followed by aisles,racks,level and in side the WarehouseLevel there
	//are 30 space to put down the Fascia.
	static ArrayList<ArrayList> warehouseinv = new ArrayList();
	
	
	private  String WarehouseName;
	
	/***
	 * construct the warehouse
	 */
	public Warehouse(){
		//build the initial warehouse storage room
		for( int i = 0; i < 2; ++i ) {
			  ArrayList<ArrayList> zone = new ArrayList();
			  warehouseinv.add( zone );
			  
			  for( int j = 0; j < 2; ++j ) {
				  ArrayList<ArrayList> aisles = new ArrayList();
				  zone.add( aisles );
				  
			    for( int k = 0; k < 3; ++k ) {
			    	ArrayList<ArrayList> racks = new ArrayList();
			    	aisles.add( racks);
			    	
			    	 for( int l = 0; l <4 ; ++l ) {
						  ArrayList<WarehouseLevel> level = new ArrayList();
						  racks.add( level);
				  }
			  }
			}
		}
		//
	}
	
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
		
		if (zone =="zonea"){
			return ZoneA[aisles][racks][level;
	}
	
	
	}
	
	

}
