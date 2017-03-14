package phase1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class hrSystem {
  private ArrayList<Worker> hrSystem;
  private HashMap<Integer,ArrayList<Integer> > sequencingList; 
  private HashMap<Integer,ArrayList<ArrayList<Integer>> > loadingList; 
 
  
  public hrSystem() {
    this.hrSystem = new ArrayList<Worker>();
  }
  
  public Worker getWorker(String name) {
    for (int i=0; i < hrSystem.size(); i++) {
      if (hrSystem.get(i).getName() == name) {
        return hrSystem.get(i);
      }
    } 
    Worker nw = new Worker(name);
    return nw;
  }
  
  public void readFromCSVFile(String filePath) throws FileNotFoundException {

    Scanner scanner = new Scanner(new FileInputStream(filePath));
    String[] record;
    while(scanner.hasNextLine()) {
      record = scanner.nextLine().split(",");
      this.createEmployee(record[0], record[1]);
    }      
    scanner.close();
  }
  
  public void addTOSequencing (Integer sequencingID , ArrayList<Integer> Sequencinglist){
    sequencingList.put(sequencingID, Sequencinglist);
  }
  
  //return sequencing ID
  public Integer getSequencingID(){
    if (sequencingList.isEmpty()){
      return 0;
    }else{
      Set<Integer> x =sequencingList.keySet();
      return x.iterator().next();
    }
  }
  //
  //return current sequencing item
    public ArrayList<Integer> getSequencingItem(Integer id){
    return sequencingList.get(id);
  }
    public void addToloader(Integer id, ArrayList<ArrayList<Integer>> loadingitem){
      loadingList.put(id, loadingitem);
      sequencingList.remove(id);     
    }
  
  public void createEmployee(String name, String job) {
    if (job.equals("Sequencer")) {
      Sequencer sq = new Sequencer(name);
      this.hrSystem.add(sq); 
    } else if (job.equals ("Loader")){
      Loader ld = new Loader(name);
      this.hrSystem.add(ld);  
    } else if (job.equals("Replenisher")){
      Replenisher rp = new Replenisher(name);
      this.hrSystem.add(rp);
    }
   
  }

}
