package phasetwo;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Warehouse {
  // this two ArrayList use to represent two Zone inside the warehouse,
  // the order followed by aisles,racks,level and in side the WarehouseLevel there
  // are 30 space to put down the Fascia.
  static ArrayList<ArrayList<ArrayList<ArrayList<Level>>>> warehouseinv = new ArrayList<>();
  private String warehouseName;

  /**
   * Construct the warehouse, entire warehouse are build in a nest arraylist
   */
  public Warehouse() {
	  //build the zone level
    for (int i = 0; i < 2; ++i) {
      ArrayList<ArrayList<ArrayList<Level>>> zone = new ArrayList<>();
      warehouseinv.add(zone);
      //build the aisles level
      for (int j = 0; j < 2; ++j) {
        ArrayList<ArrayList<Level>> aisles = new ArrayList<>();
        zone.add(aisles);
        //build the rack level
        for (int k = 0; k < 3; ++k) {
          ArrayList<Level> racks = new ArrayList<>();
          aisles.add(racks);
          //build the level level
          for (int l = 0; l < 4; ++l) {
            Level level = new Level();
            level.add(30);
            racks.add(level);
          }
        }
      }
    }
    //
  }

  
  

  /**
   * This function is use to initial ware house state in the beginning. It will read the csv file
   * and input all the data into the system.
   */
  public void storageInital(String filePath) throws FileNotFoundException {
    Scanner scanner = new Scanner(new FileInputStream(filePath));
    String[] record;
    while (scanner.hasNextLine()) {
        record = scanner.nextLine().split(",");
      ArrayList<Integer> locationlist = new ArrayList<>();
      if (record[0].equals("A")){
    	  record[0] = "0";
      }else{record[0] = "1";}
    	  
      for (int i = 0; i < record.length; i++) {
    	 
        locationlist.add(Integer.parseInt(record[i]));
      }

      warehouseinv.get(locationlist.get(0)).get(locationlist.get(1)).get(locationlist.get(2))
          .get(locationlist.get(3)).set(locationlist.get(4));

    }
    scanner.close();
  }



  /**
   * When a pick scan the barcode, it will tell the system it take the Fascia from the warehouse.
   * The warehouse will make a note, and check if that rack need Replenishing.
   * 
   * @param zone zone number of warehouse
   * @param aisles aisles of warehouse
   * @param racks rack of warehouse
   * @param level level of warehouse
   * @return a Fascia 
   */
  public Fascia getFascia(int zone, int aisles, int racks, int level) {
    return warehouseinv.get(zone).get(aisles).get(racks).get(level).removeOne();
  }

  /**
   * Write down the warehouse system into a csv file.
   * only record the level that does not has 30 Fascia
   * @param filePath a file to write 
   * @throws IOException throw an exception
   */
  public void writeDown(String filePath) throws IOException {
    String csvFile = filePath;
    FileWriter writer = new FileWriter(csvFile);
    for (int i = 0; i < 2; ++i) {
      // ZONE

      for (int j = 0; j < 2; ++j) {
        // AILES

        for (int k = 0; k < 3; ++k) {
          // RACK

          for (int l = 0; l < 4; ++l) {
            // LEVEL
        	  if ( warehouseinv.get(i).get(j).get(k).get(l).report()!=30 ){
        	String zone = "A";
        	if (i!=0){
        		zone = "B";
        	}
            writer.append(String.valueOf(zone));
            writer.append(",");
            writer.append(String.valueOf(j));
            writer.append(",");
            writer.append(String.valueOf(k));
            writer.append(",");
            writer.append(String.valueOf(l));
            writer.append(",");
            writer.append(String.valueOf(warehouseinv.get(i).get(j).get(k).get(l).report()));
            writer.append("\n");}
          }

        }
      }
    }
    writer.close();
  }



}


