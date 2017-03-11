package phase1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Warehouse {
	//this two ArrayList use to represent two Zone inside the warehouse,
	//the order followed by aisles,racks,level and in side the WarehouseLevel there
	//are 30 space to put down the Fascia.
	static ArrayList<ArrayList<ArrayList<ArrayList<Level>>>> warehouseinv = new ArrayList();
		
	private  String WarehouseName;
	
	/***
	 * construct the warehouse
	 */
	public Warehouse(){
		//build the initial warehouse storage room
		for( int i = 0; i < 2; ++i ) {
			ArrayList<ArrayList<ArrayList<Level>>> zone = new ArrayList();
			warehouseinv.add( zone );
			  
			  for( int j = 0; j < 2; ++j ) {
				  ArrayList<ArrayList<Level>> aisles = new ArrayList();
				  zone.add( aisles );
				  
			    for( int k = 0; k < 3; ++k ) {
			    	ArrayList<Level> racks = new ArrayList();
			    	aisles.add( racks);
			    	
			    	 for( int l = 0; l <4 ; ++l ) {
			    	   Level level = new Level();
						  racks.add( level);
				  }
			  }
			}
		}
		//
	}
	
	public Warehouse(String WarehouseName){
		this.WarehouseName = WarehouseName;
		//build the initial warehouse storage room
				for( int i = 0; i < 2; ++i ) {
					ArrayList<ArrayList<ArrayList<Level>>> zone = new ArrayList();
					  warehouseinv.add( zone );
					  
					  for( int j = 0; j < 2; ++j ) {
						  ArrayList<ArrayList<Level>> aisles = new ArrayList();
						  zone.add( aisles );
						  
					    for( int k = 0; k < 3; ++k ) {
					    	ArrayList<Level> racks = new ArrayList();
					    	aisles.add( racks);
					    	
					    	 for( int l = 0; l <4 ; ++l ) {
					    	   Level level = new Level();
								  racks.add( level);
						  }
					  }
					}
				}
				//
	}
	
	/***
	 * this function is use to initial ware house state in the beginning.
	 * it will read the csv file and input all the data into the system.
	 */
	public void storageInital (String filePath) throws FileNotFoundException{
	  Scanner scanner = new Scanner(new FileInputStream(filePath));
      String[] record;
      Order order;
      ArrayList<Order> temOrder  = new ArrayList();
      while(scanner.hasNextLine()) {
        
        Fascia temFas = new Fascia();
        record = scanner.nextLine().split(",");//read the input csv file
        
        int[] locationlist = Arrays.asList(record).stream().mapToInt(Integer::parseInt).toArray();//convert string array into int array
        
        warehouseinv.get(locationlist[0]).get(locationlist[1]).get(locationlist[2])
        .get(locationlist[3]).add(new Fascia(locationlist[4]));// level may get to many Fascias shold be a Exception 
       }
      scanner.close();
	} 
	
	
	
	/***
	 * When a pick scan the barcode, it will tell the system it take 
	 * the Fascia from the warehouse. The warehouse will make a note, 
	 * and check if that rack need Replenishing.
	 * @param zone
	 * @param aisles
	 * @param racks
	 * @param level
	 * @return
	 */
	public Fascia getFascia (int zone,int aisles,int racks,int level){ 
		return warehouseinv.get(zone).get(aisles).get(racks).get(level).removeOne();
	}
	
	
	}
	
	

