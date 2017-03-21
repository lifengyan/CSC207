package phasetwo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import phaseone.Replenisher;
import phaseone.Sequencer;
import phaseone.Worker;

public class Hrsystem {
  private ArrayList<Sequencer> hrSystemSequencer;
  private ArrayList<Worker> hrSystemOther;
  private ArrayList<Loader> hrSystemLoader;

  private HashMap<Integer, ArrayList<Integer>> sequencingList;
  public HashMap<Integer, ArrayList<ArrayList<Integer>>> loadingList;

  /**
   * Construct Hrsystem.
   */
  public Hrsystem() {
    this.hrSystemOther = new ArrayList<Worker>();
    this.hrSystemSequencer = new ArrayList<Sequencer>();
    this.sequencingList = new HashMap<Integer, ArrayList<Integer>>();
    this.loadingList = new HashMap<Integer, ArrayList<ArrayList<Integer>>>();
    this.hrSystemLoader = new ArrayList<Loader> ();
  }

  
  /**
   * Get a sequencer from hrSystemSequencer by name.
   * @param name of the sequencer
   * @return Sequencer
   */
  public Sequencer getSequencer(String name) {
    for (int i = 0; i < hrSystemSequencer.size(); i++) {
      if (hrSystemSequencer.get(i).getName().equals(name)) {
        return hrSystemSequencer.get(i);
      }
    }
    Sequencer nw = new Sequencer(name);
    return nw;
  }


  public Loader getLoader(String name) {
	    for (int i = 0; i < hrSystemLoader.size(); i++) {
	      if (hrSystemLoader.get(i).getName().equals(name)) {
	        return hrSystemLoader.get(i);
	      }
	    }
	    Loader nw = new Loader(name);
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
   * Add a new Sequencer to Sequencing List.
   * @param sequencingId is the id of Sequencer
   * @param sequencinglist the list of Sequencer
   */
  public void addtoSequencing(Integer sequencingId, ArrayList<Integer> sequencinglist) {
    sequencingList.put(sequencingId, sequencinglist);
  }

  
  /**
   * Return a sequencer ID.
   * @return a id
   */
  public Integer getSequencingid() {
    if (sequencingList.isEmpty()) {
      return 0;
    } else {
      Set<Integer> xn = sequencingList.keySet();
      return xn.iterator().next();
    }
  }

  //
  // return current sequencing item
  public ArrayList<Integer> getSequencingItem(Integer id) {
    return sequencingList.get(id);
  }

  public void addToloader(Integer id, ArrayList<ArrayList<Integer>> loadingitem) {
    loadingList.put(id, loadingitem);
    sequencingList.remove(id);
  }

  /**
   * Create a new employee and add to the hr system.
   * @param name of the employee
   * @param job of the employee
   */
  public void createEmployee(String name, String job) {
    if (job.equals("Sequencer")) {
      Sequencer sq = new Sequencer(name);
      this.hrSystemSequencer.add(sq);
    } else if (job.equals("Loader")) {
      
    	Loader ld = new Loader(name);
      this.hrSystemLoader.add(ld);
      
    } else if (job.equals("Replenisher")) {
      Replenisher rp = new Replenisher(name);
      this.hrSystemOther.add(rp);
    }

  }

}
