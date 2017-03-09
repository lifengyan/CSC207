package phase1;

import java.util.ArrayList;

public class Warehouse {
	//this two ArrayList use to represent two Zone inside the warehouse,
	//the order followed by aisles,racks,level and in side the level there
	//are 30 space to put down the Fascia.
	static ArrayList<Fascia>[][][][] ZoneA = new ArrayList [2][3][4][30];
	static ArrayList<Fascia>[][][][] ZoneB = new ArrayList [2][3][4][30];
	
	private  String WarehouseName;
	
	/***
	 * Constrick the warehouse
	 */
	public Warehouse(){};
	
	public Warehouse(String WarehouseName){
		this.WarehouseName = WarehouseName;
	};

}
