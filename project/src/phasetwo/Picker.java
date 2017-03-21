package phasetwo;

import java.util.ArrayList;

public class Picker {


  // a array contains all the SKU that are already picked by current picker
  private ArrayList<String> forkLift = new ArrayList<String>();
  private ArrayList<Location> locationList = new ArrayList<Location>();
  private Integer requestId; // set to zero when picker is not currently picking
  private String name;
// keep track of how many skus the picker have picked 
  private Integer currentpick = 0;

  // Constructor, get picker name as a string input
  public Picker(String pickername) {
    this.name = pickername;
  }

  // store currently pick request's 8 locations
  public void addLocation(ArrayList<Location> locations) {
    this.locationList = locations;
  }
  // check if the input sku is the corrct one, if it is currentpick++ if not put it back, system output:put it back
  // no change should be made in the warehouse system until the correct one is picked.
  public boolean checkPickerScanedCorrectSKU(String sku){
	if(sku.equals(this.locationList.get(currentpick).getSKU())){
		currentpick++;
		return true;
	}else{return false;}
  }
// check if the picker has got all the 8 skus needed 
  public boolean checkgotAllSKU(){
	  if (this.forkLift.size() == 8){
		  return true;
	  }
	  else{return false;}
  }

  // method to add picked SKU into forkLift, need to change this according to if the picker picked the correct sku
  /**
   * Add a fascia with sku number to forklift.
   */
  public void addtoFolkLift(String sku, Warehouse currentWarehouse) {

    this.forkLift.add(sku);
    int previouspick = currentpick -1;
    Location currLocation = locationList.get(previouspick); //here we want the previous location not the next one.
    int zone = 0;											// currentpick added one when it is the correct one
    if (currLocation.zone.equals("B")) {
      zone = 1;
    }
    currentWarehouse.getFascia(zone, Integer.valueOf(currLocation.aisle),
        Integer.valueOf(currLocation.rack), Integer.valueOf(currLocation.level));

  }


  // getter method to get forkLift
  public ArrayList<String> getForkLift() {
    return this.forkLift;
  }

  // getter method to get the requestID that his picker in currently working on.
  public Integer getRequestid() {
    return this.requestId;
  }

  // getter function to get picker's name
  public String getName() {
    return this.name;
  }

  // getter method to return one location at a time
  // need to change this function name to getNextLocation
  public String getLoc() {
    return locationList.get(currentpick).toString();
  }

  public void setRequestid(int id) {
    this.requestId = id;
  }

}
