package phasetwo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

public class LoaderCommand implements Cammand{
  
  private warehouseSystem theSystem;
  private String[] userInput;
  private Loader CurrentLoader;
  /***
   * constuctor the loader command
   */
  public LoaderCommand(warehouseSystem theSystem, String[] userInput) {
    this.theSystem = theSystem;
    this.userInput  = userInput;
    CurrentLoader = theSystem.hrsystem.getLoader(userInput[1]);
  }
  
/***
 * this part is to find our what task the loader need to do
 */
  @Override
  public void execute() throws IOException {
    if (userInput[2].equals("ready")) {
      loaderReady(theSystem.hrsystem, userInput, CurrentLoader);
      
    }else if (userInput[2].equals("scan")){
      loaderScan();      
    }else if (userInput[2].equals("rescan")){
      CurrentLoader.ready(CurrentLoader.getid());
      theSystem.LOGGER.log(Level.FINE, "Loader " + CurrentLoader.getName()
      + "is going to repick  with id of " +CurrentLoader.getid());
    }else if (userInput[2].equals("loading")){
      //loader finish loading send all the item to truck
      CurrentLoader.load(theSystem.hrsystem.loadingList, "order.csv",theSystem.hrsystem);
      theSystem.LOGGER.log(Level.FINE,"Loader " + userInput[1].toString() + " is loading" + "\n");
    }
    
  }

/***
 * loader will give system 8 sku and check if it fit the requirements
 */
  private void loaderScan() {
    ArrayList<String> front = new ArrayList<String>();
    ArrayList<String> back = new ArrayList<String>();
    ArrayList<ArrayList<String>> pallets = new  ArrayList<ArrayList<String>>();
    for (int i= 0; i<4; i ++){
      front.add(userInput[3+i]);
      back.add(userInput[7+i]);      
    }
    pallets.add(front);
    pallets.add(back);
    if (CurrentLoader.scan(pallets,theSystem.hrsystem.getunloadingList(),
        theSystem.hrsystem.loadingList, 
        theSystem.orderManager)){
      theSystem.LOGGER.log(Level.FINE, "Loader " + CurrentLoader.getName()
      +" scaned picked Id of " + CurrentLoader.getid() + " all the item are correct");
    }else{
      theSystem.LOGGER.log(Level.FINE, "Loader " + CurrentLoader.getName()
      +" scaned picked Id of " + CurrentLoader.getid() + 
      "item are incorrect send back to repick");
    }
  }

/***
 * get the loader from the hrsystem, if not, creat a new one
 */
  private void loaderReady(Hrsystem hrsystem, 
      String[] userInput2, Loader currentLoader) {
    int loaderid = hrsystem.getLoaderId();
    if (loaderid != 0){
    currentLoader.ready(loaderid);
    theSystem.LOGGER.log(Level.FINE, "Loader " + CurrentLoader.getName()
    + " recieve pick id of " +CurrentLoader.getid());
                
    }
  }

}
