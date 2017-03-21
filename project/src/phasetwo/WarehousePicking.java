package phasetwo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Based on the Integer SKUs in List 'skus', return a List of locations, where each location is a
 * String containing 5 pieces of information: the zone character (in the range ['A'..'B']), the
 * aisle number (an integer in the range [0..1]), the rack number (an integer in the range ([0..2]),
 * and the level on the rack (an integer in the range [0..3]), and the SKU number.
 * 
 * @param skus the list of SKUs to retrieve.
 * @return the List of locations.
 */
public class WarehousePicking {
  // file location
  // an array to store all locations from the file
  private static ArrayList<Location> locationList = new ArrayList<Location>();
  private ArrayList<Integer> fasciaList = new ArrayList<Integer>();

  // constructor
  
  /**
   * A constructor that read the file and add to location list.
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
   * Add the sku number of order need to be picked to fascia list. 
   * @param newRequest the new picking request
   * @return a arraylist of sku number
   */

  public ArrayList<Integer> pickRequest(Map<Integer, Order> newRequest) {
    for (Integer key : newRequest.keySet()) {
      Order order = newRequest.get(key);
      this.fasciaList.add(order.getFront()); 
      this.fasciaList.add(order.getBack());
    }
    return fasciaList;
  }

  /**
   * Generate optimize path of location of fascia to be picked.
   * @param skus SKU number of picked fascia
   * @return an ArrayList of location
   */

  public ArrayList<Location> optimize(ArrayList<Integer> skus) {

    ArrayList<Location> locationS = new ArrayList<Location>();
    // get 8 random locations and return them as an array of Locations
    for (int i = 0; i < 8; i++) {
      int randomNum = ThreadLocalRandom.current().nextInt(1, 47);
      Location curr = new Location(null, null, null, null, null);
      curr = locationList.get(randomNum);
      locationS.add(curr);
    }
    return locationS;

  }
}
