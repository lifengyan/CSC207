package phase1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class hrSystem {
  private ArrayList<Worker> hrSystem;
  
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
  
  public void createEmployee(String name, String job) {
    if (job == "Sequencer") {
      Sequencer sq = new Sequencer(name);
      this.hrSystem.add(sq); 
    } else if (job == "Loader"){
      Loader ld = new Loader(name);
      this.hrSystem.add(ld);  
    } else if (job == "Replenisher"){
      Replenisher rp = new Replenisher(name);
      this.hrSystem.add(rp); 
   
  }

}
