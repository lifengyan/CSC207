package phasetwo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


public class Hrsystem {
  private ArrayList<Sequencer> FreeSequencer;
  private ArrayList<Replenisher> hrSystemOther;
  private ArrayList<Loader> hrSystemLoader;

  private HashMap<Integer, ArrayList<String>> unsequencingList;
  private HashMap<Integer, ArrayList<ArrayList<String>>> unloadingList;
  public HashMap<Integer, ArrayList<ArrayList<String>>> loadingList;
  private ArrayList<Integer> unScanedloadedID;

  /**
   * Construct Hrsystem.
   */
  public Hrsystem() {
    this.hrSystemOther = new ArrayList<Replenisher>();
    this.FreeSequencer = new ArrayList<Sequencer>();
    this.unsequencingList = new HashMap<Integer, ArrayList<String>>();
    this.unloadingList = new HashMap<Integer, ArrayList<ArrayList<String>>>();
    this.hrSystemLoader = new ArrayList<Loader> ();
    this.loadingList = new  HashMap<Integer, ArrayList<ArrayList<String>>>();
    this.unScanedloadedID = new ArrayList<Integer> ();
    
  }

  /**
   * Get a sequencer from hrSystemSequencer by name.
   * @param name of the sequencer
   * @return Sequencer
   */
  public Sequencer getSequencer(String name) {
    for (int i = 0; i < FreeSequencer.size(); i++) {
      if (FreeSequencer.get(i).getName().equals(name)) {
        return FreeSequencer.get(i);
      }
    }
    Sequencer nw = new Sequencer(name);
    FreeSequencer.add(nw);
    return nw;
  }
  
  /***
   * add  get the loader from free loader list
   * @param name
   * @return
   */
  public Loader getLoader(String name) {
	    for (int i = 0; i < hrSystemLoader.size(); i++) {
	      if (hrSystemLoader.get(i).getName().equals(name)) {
	        return hrSystemLoader.get(i);
	      }
	    }
	    Loader nw = new Loader(name);
	    hrSystemLoader.add( nw);
	    return nw;
	  }
  /***
   * return a Replenisher, if ther is no Replenisher in the system create a new one
   * @param name
   * @return
   */
  public Replenisher getReplenisher(String name) {
    for (int i = 0; i < hrSystemOther.size(); i++) {
      if (hrSystemOther.get(i).getName().equals(name)) {
        return  hrSystemOther.get(i);
      }
    }
    Replenisher nw = new Replenisher(name);
    hrSystemOther.add( nw);
    return nw;
  }

  /**
   * Read list of workers from a file input.
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
   * @param sequencingId is the id of Sequencer
   * @param sequencinglist the list of Sequencer
   */
  public String addtoSequencing(Integer sequencingId, ArrayList<String> sequencinglist) {
    unsequencingList.put(sequencingId, sequencinglist);
    // if there are 
    if (!FreeSequencer.isEmpty()) {
      Sequencer localSequencer = FreeSequencer.get(0);
      FreeSequencer.remove(0);
      localSequencer.ready(this.getSequencingid());
      return (localSequencer.getName() + " recieved the items with pick ID of "+ localSequencer.getid());
    }
    return "we do not have free sequencer yet";
  }

  
  /**
   * Return a sequencer ID.
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
  
  /***
   * give all the item to 
   * @param id
   * @param loadingitem
   */
  public void addToloader(Integer id, ArrayList<ArrayList<String>> loadingitem) {
    unloadingList.put(id, loadingitem);
    unScanedloadedID.add(id);
    unsequencingList.remove(id);
    
  }

  /**
   * Create a new employee and add to the hr system.
   * @param name of the employee
   * @param job of the employee
   */
  public void createEmployee(String name, String job) {
    if (job.equals("Sequencer")) {
      Sequencer sq = new Sequencer(name);
      this.FreeSequencer.add(sq);
    } else if (job.equals("Loader")) {
      
    	Loader ld = new Loader(name);
      this.hrSystemLoader.add(ld);
      
    } else if (job.equals("Replenisher")) {
      Replenisher rp = new Replenisher(name);
      this.hrSystemOther.add(rp);
    }
  }
  /***
   * return one of the pickid that is sequenced but loader have not scan yet
   */
  public int getLoaderId() {
    if (!unScanedloadedID.isEmpty()){
      int reint = unScanedloadedID.get(0);
      unScanedloadedID.remove(0);
      return reint;
    }else{
      return 0;
    }
    
  }
/***
 * return the loader scaned but not loadinged list
 */
  public HashMap<Integer, ArrayList<ArrayList<String>>> getunloadingList() {
    return  unloadingList;
  }
 
  
}
