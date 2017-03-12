package phase1;

import java.util.ArrayList;

public class Picker {
	
		
		// a array contains all the SKU that are already picked by current picker
		ArrayList<Integer> forkLift = new ArrayList<Integer>();
		boolean status;
		ArrayList<Location> locationList = new ArrayList<Location>();
		Integer requestID; // set to zero when picker is not currently picking
		String name;
		
		//Constructor, get picker name as a string input
		public Picker(String pickername){
			this.name = pickername;
		}

		// store currently pick request's 8 locations
		public void addLocation(ArrayList<Location> locations){
			this.locationList = locations;
		}
		
		// method to add picked SKU into forkLift
		public void addtoFolkLift(Integer sku){
			this.forkLift.add(sku);
		}
		// method to change picker status
		public void changePickerStatus(boolean stA){
			this.status = stA;// true when ready, false when this picker is currently picking
		}
		
		// after picker complete this request, empty this request's info
		public void resetPicker(){
			this.status = true; // indicate the picker is free
			this.forkLift = null;// empty this Array list 
			this.locationList = null;
			requestID = null;
			
			//after reset this picker, add it to freePicker list in pickerManager
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
		
		
}
