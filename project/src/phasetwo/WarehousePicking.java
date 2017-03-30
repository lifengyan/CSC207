package phasetwo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;


/**
 * Simulates the generic software, which gives optimized location list for picker to go in order 
 * to spend minimum time to get all 8 fascias in one picking request.
 * <p>
 * Based on the Integer SKUs in List 'skus', return a List of locations, where each location is a
 * String containing 5 pieces of information: the zone character (in the range ['A'..'B']), the
 * aisle number (an integer in the range [0..1]), the rack number (an integer in the range ([0..2]),
 * and the level on the rack (an integer in the range [0..3]), and the SKU number.
 * 
 */
public class WarehousePicking {
  private static ArrayList<Location> locationList = new ArrayList<Location>(); 
  private ArrayList<String> fasciaList = new ArrayList<String>();

  
  /**
   * constructor reads the input file and read each line of the file as a Location instance 
   * then stores to location list.
   * @param piclocFile file to read 
   * @throws FileNotFoundException while file is not found
   */
  public void warehousePickingreader(String piclocFile) throws FileNotFoundException {
    Scanner scanner = new Scanner(new FileInputStream(piclocFile));
    String[] record;
    while (scanner.hasNextLine()) {
      record = scanner.nextLine().split(",");
      Location newLoc = new Location(record[0], record[1], record[2], record[3], record[4]);
      locationList.add(newLoc);

    }
    scanner.close();
  }

  /**
   * Adds all SKU numbers in one picking request and add to fasciaList. FasciaList is 
   * then used to generate corresponding locations in optimize method
   * @param newRequest the new picking request which contains 4 orders and 8 fascias
   * @return a String type arrayList of 8 SKU number
   */
  public ArrayList<String> pickRequest(Map<Integer, Order> newRequest) {
    for (Integer key : newRequest.keySet()) {
      Order order = newRequest.get(key);
      this.fasciaList.add(order.getFront()); 
      this.fasciaList.add(order.getBack());
    }
    return fasciaList;
  }

  /**
   * Generate optimize path for pickers to get all 8 fascias in one picking request with minimum time
   * @param skus Integer type arrayList with 8 SKU numbers in one picking request
   * @return an Location type ArrayList of location for 8 fascias in one picking request
   */
  public ArrayList<Location> optimize(ArrayList<String> skus) {
    ArrayList<Location> locationS = new ArrayList<Location>();
    // get 8 random locations and return them as an array of Locations
   
    for (String sku : skus) {
      int i =0;
      for (Location checkLocation: locationList){
        if (checkLocation.sku.equals(sku)){
          locationS.add(checkLocation);
        }
      }
  /*  
      Location curr = new Location(null, null, null, null, null);
        curr = locationList.get(Integer.valueOf(sku)-1);
      locationS.add(curr);*/
    }
    return locationS;
  }
}
