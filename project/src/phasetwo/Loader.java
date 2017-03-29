package phasetwo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Loader implements Worker {
  private String name;
  private Integer pickid;

  public Loader(String name) {
    this.name = name;
  }


  public void ready(int pickid) {
    this.pickid = pickid;
  }
  
	/**
	 * This loader will scan the pallets to see if all the sku numbers are in
	 * order. if it is not, the loader will send the pickid to OrderManager for
	 * repick.
	 * 
	 * @param pallets
	 *            the pallets that send to compare
	 * @param loadinglist
	 *            the loadinglist that sequencer sequenced
	 * @param loaded
	 *            the list of fascia that loader checked and going to loaded
	 * @param om
	 *            the OrderManager system
	 * @return boolean that tells if these pallets are correct
	 */
	public boolean scan(ArrayList<ArrayList<String>> pallets,
			HashMap<Integer, ArrayList<ArrayList<String>>> loadinglist,
			HashMap<Integer, ArrayList<ArrayList<String>>> loaded, OrderManager om) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if (!(loadinglist.get(pickid).get(i).get(j).equals(pallets.get(i).get(j)))) {
					repick(om);
					return false;
				}
			}
		}
		loaded.put(pickid, pallets);
		return true;
	}

	/**
	 * Call OrderManager to repick these pickid.
	 * 
	 * @param om
	 *            the OrderManager
	 */
  public void repick(OrderManager om) {
    om.repick(this.pickid);
  }

  /**
   * Load every sku number of the fascia in the loadinglist in to a file.
   * @param loaded the loaded list of fascia
   * @param filePath file to write in
   * @throws IOException when file is not found.
   */
  public void load(HashMap<Integer, ArrayList<ArrayList<String>>> loaded, String filePath)
      throws IOException {

    String csvFile = filePath;
    FileWriter writer = new FileWriter(csvFile);
    String frontSku = "";
    String backSku = "";

    if (loaded.keySet().size() == 0) {
      writer.close();
      return;
    }
    for (Integer key : loaded.keySet()) {
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 4; j++) {
          if (i == 0) {
            frontSku = frontSku + loaded.get(key).get(i).get(j) + ",";
          } else {
            backSku = backSku + loaded.get(key).get(i).get(j) + ",";
          }
        }
      }

      writer.append("pick ID: " + Integer.toString(key) + "," + frontSku + backSku);

    }

    writer.close();
    System.out.println("Loading");


  }

  @Override
  public String getName() {
    return name;
  }


  @Override
  public int getid() {
    return this.pickid;
  }



}
