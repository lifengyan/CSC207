package phaseone;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public  class Worker {

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
  
  
  


}
