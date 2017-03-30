package phasetwo;

import java.io.IOException;
import java.util.logging.Level;

public class ReplenisherCommand implements Cammand  {

  warehouseSystem theSystem;
  String[] userInput;
  Replenisher currentRplenisher;
  /***
   * creat a replenisher command pass all the value in to the system
   * @param theSystem
   * @param userInput
   */
  public ReplenisherCommand(warehouseSystem theSystem, String[] userInput) {
    this.theSystem = theSystem;
    this.userInput  = userInput;
    currentRplenisher = theSystem.hrsystem.getReplenisher(userInput[1]);
  }
  /***
   * replenisher replenish target level to 30 fasica
   */
  @Override
  public void execute() throws IOException {
    String outPut = currentRplenisher.replenish(theSystem.warehouse,userInput);
    theSystem.LOGGER.log(Level.INFO, outPut + " by "+ userInput[1]);
  }

}
