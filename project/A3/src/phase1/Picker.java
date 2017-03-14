package phase1;

import java.util.ArrayList;

public class Picker {
	
		
		// a array contains all the SKU that are already picked by current picker
		private ArrayList<Integer> forkLift = new ArrayList<Integer>();
		private ArrayList<Location> locationList = new ArrayList<Location>();
		private Integer requestID; // set to zero when picker is not currently picking
		private String name;
		private static Integer curr= 0; 
		private Integer currpick =0;
		//Constructor, get picker name as a string input
		public Picker(String pickername){
			this.name = pickername;
		}

		// store currently pick request's 8 locations
		public void addLocation(ArrayList<Location> locations){
			this.locationList = locations;
		}
		
		// method to add picked SKU into forkLift
		public void addtoFolkLift(Integer sku, Warehouse currentWarehouse){
			
			this.forkLift.add(sku);
			Location currLocation = locationList.get(currpick++);
			int zone = 0;
				if(currLocation.zone.equals("B")){
					zone = 1;
				}	
			currentWarehouse.getFascia(zone, Integer.valueOf( currLocation.aisle),
					Integer.valueOf( currLocation.rack), Integer.valueOf( currLocation.level));
			
		}
		
	
		// getter method to get forkLift
		public ArrayList<Integer> getForkLift(){
			return this.forkLift;
		}
		//getter method to get the requestID that his picker in currently working on.
		public Integer getRequestID(){
			return this.requestID;
		}
		// getter function to get picker's name
		public String getName(){
			return this.name;
		}
		//getter method to return one location at a time
		public String getLoc(){
			return locationList.get(curr++).toString();
		}
		
		public void setRequestid(int id) {
		  this.requestID = id;
		}
		
}
