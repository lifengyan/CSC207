package phasetwo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


public class Hrsystem {
  private ArrayList<Sequencer> freeSequencer;
  private ArrayList<Replenisher> hrSystemOther;
  private ArrayList<Loader> hrSystemLoader;
  private ArrayList<Sequencer> hrSystemSequencer;
  private HashMap<Integer, ArrayList<String>> unsequencingList;
  private HashMap<Integer, ArrayList<ArrayList<String>>> unloadingList;
  public HashMap<Integer, ArrayList<ArrayList<String>>> loadingList;
  private ArrayList<Integer> unScanedloadedid;
  public int trucknumber =0;

  /**
   * Construct Hrsystem.
   */
  public Hrsystem() {
    this.hrSystemOther = new ArrayList<Replenisher>();
    this.freeSequencer = new ArrayList<Sequencer>();
    this.unsequencingList = new HashMap<Integer, ArrayList<String>>();
    this.unloadingList = new HashMap<Integer, ArrayList<ArrayList<String>>>();
    this.hrSystemLoader = new ArrayList<Loader>();
    this.loadingList = new HashMap<Integer, ArrayList<ArrayList<String>>>();
    this.unScanedloadedid = new ArrayList<Integer>();
    this.hrSystemSequencer = new ArrayList<Sequencer>();

  }
  
 

  /**
   * Get a sequencer from hrSystemSequencer by name.
   * 
   * @param name of the sequencer
   * @return Sequencer
   */
  public Sequencer getSequencer(String name) {

    for (int i = 0; i < freeSequencer.size(); i++) {
      if (freeSequencer.get(i).getName().equals(name)) {
        Sequencer nw = freeSequencer.get(i);
        freeSequencer.remove(i);
        return nw;
      }
    }
    for (int i = 0; i < hrSystemSequencer.size(); i++) {
      if (hrSystemSequencer.get(i).getName().equals(name)) {
        Sequencer nw = hrSystemSequencer.get(i);
        return nw;
      }
    }
    Sequencer nw = new Sequencer(name);
    // if there is no item for sequencer, then the sequencer will wait and become a free Sequencer

    if (unsequencingList.isEmpty()) {
      freeSequencer.add(nw);
    } else {
      hrSystemSequencer.add(nw);
    }

    return nw;
  }

  /**
   * add get the loader from free loader list.
   * 
   * @param name of the loader
   * @return this loader
   */
  public Loader getLoader(String name) {
    for (int i = 0; i < hrSystemLoader.size(); i++) {
      if (hrSystemLoader.get(i).getName().equals(name)) {
        return hrSystemLoader.get(i);
      }
    }
    Loader nw = new Loader(name);
    hrSystemLoader.add(nw);
    return nw;
  }

  /**
   * return a Replenisher, if ther is no Replenisher in the system create a new one.
   * 
   * @param name of the replenisher
   * @return the replenisher
   */
  public Replenisher getReplenisher(String name) {
    for (int i = 0; i < hrSystemOther.size(); i++) {
      if (hrSystemOther.get(i).getName().equals(name)) {
        return hrSystemOther.get(i);
      }
    }
    Replenisher nw = new Replenisher(name);
    hrSystemOther.add(nw);
    return nw;
  }

  /**
   * Read list of workers from a file input.
   * 
   * @param filePath of the input file
   * @throws FileNotFoundException while file not found
   */
  public void readFromcsvfile(String filePath) throws FileNotFoundException {

    Scanner scanner = new Scanner(new FileInputStream(filePath));
    String[] record;
    while (scanner.hasNextLine()) {
      record = scanner.nextLine().split(",");
      this.createEmployee(record[0], record[1]);
    }
    scanner.close();
  }

  /**
   * Add a new unscreened picking list to Sequencing List.
   * 
   * @param sequencingId is the id of Sequencer
   * @param sequencinglist the list of Sequencer
   */
  public String addtoSequencing(Integer sequencingId, ArrayList<String> sequencinglist) {
    unsequencingList.put(sequencingId, sequencinglist);
    // if there are
    if (!freeSequencer.isEmpty()) {
      Sequencer localSequencer = freeSequencer.get(0);
      freeSequencer.remove(0);
      localSequencer.ready(this.getSequencingid());
      return (localSequencer.getName() + " recieved the items with pick ID of "
          + localSequencer.getid());
    }
    return "we do not have free sequencer yet";
  }


  /**
   * Return a sequencer ID. if there is a waiting sequencing a waits, return the id and remove it
   * from the unsequencing list
   * 
   * @return a id
   */
  public Integer getSequencingid() {
    if (unsequencingList.isEmpty()) {
      return 0;
    } else {
      Set<Integer> xn = unsequencingList.keySet();
      int locoalint = xn.iterator().next();
      unsequencingList.remove(locoalint);
      return locoalint;
    }
  }

  // return current sequencing item
  public ArrayList<String> getSequencingItem(Integer id) {
    return unsequencingList.get(id);
  }

  /**
   * give all the item to.
   * 
   * @param id of pick request that needed to be loaded
   * @param loadingitem the sku of fascia that need to be loaded
   */
  public void addToloader(Integer id, ArrayList<ArrayList<String>> loadingitem) {
    unloadingList.put(id, loadingitem);
    unScanedloadedid.add(id);
  }

  /**
   * Create a new employee and add to the hr system.
   * 
   * @param name of the employee
   * @param job of the employee
   */
  public void createEmployee(String name, String job) {
    if (job.equals("Sequencer")) {
      Sequencer sq = new Sequencer(name);
      this.freeSequencer.add(sq);
    } else if (job.equals("Loader")) {

      Loader ld = new Loader(name);
      this.hrSystemLoader.add(ld);

    } else if (job.equals("Replenisher")) {
      Replenisher rp = new Replenisher(name);
      this.hrSystemOther.add(rp);
    }
  }

  /**
   * return one of the pickid that is sequenced but loader have not scan yet.
   */
  public int getLoaderId() {
    if (!unScanedloadedid.isEmpty()) {
      int reint = unScanedloadedid.get(0);
      unScanedloadedid.remove(0);
      return reint;
    } else {
      return 0;
    }

  }

  /**
   * return the loader scaned but not loadinged list.
   */
  public HashMap<Integer, ArrayList<ArrayList<String>>> getunloadingList() {
    return unloadingList;
  }


  /**
   * add the loader scaned pallets to the loading list, waits for loader loads.
   * @param pickid
   * @param pallets
   */
  public void addTOtruck(Integer pickid, ArrayList<ArrayList<String>> pallets) {
    loadingList.put(pickid, pallets);
  }


}
