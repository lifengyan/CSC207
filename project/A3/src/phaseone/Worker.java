package phaseone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Worker {

  // Worker Superclass
  private String name;
  private String status = "ready";

  public Worker(String name) {
    this.name = name;

  }

  public String getName() {
    return name;
  }

  public String getStatus() {
    return this.status;
  }
  
  public void load(HashMap<Integer, ArrayList<ArrayList<Integer>>> loadinglist, String filePath) 
		  throws IOException {}
  
  


}
