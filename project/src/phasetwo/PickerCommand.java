package phasetwo;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PickerCommand implements Cammand  {
  //
  warehouseSystem theSystem;
  String[] userInput;
  Picker currentPicker;
  //pass all the warehouse system information to this picker
  public PickerCommand (warehouseSystem theSystem, String[] userInput){
    this.theSystem = theSystem;
    this.userInput  = userInput;
    currentPicker = theSystem.pickerManager.getORaddPicker((userInput[1]));
  }
  
  
  @Override
  public void execute() throws IOException {
    if (userInput[2].equals("ready")) {
      pickerReady(theSystem.orderManager, theSystem.pickerManager,
theSystem.warehousePicking, theSystem.LOGGER, 
          userInput,currentPicker);

  } else if (userInput[2].equals("pick")) {
      pickerPicked(theSystem.pickerManager, theSystem.warehouse, theSystem.LOGGER, userInput,currentPicker);

  } else if (userInput[2].equals("marshaling")) {
      pickerToMarshaling(theSystem.pickerManager, theSystem.hrsystem, theSystem.LOGGER, userInput,currentPicker);
  }

    
  }
  
  
/***
 * when the command is ready, system will give picker job to the picker
 * @param orderManager
 * @param pickerManager
 * @param warehousePicking
 * @param LOGGER
 * @param userInput
 * @param currentPicker
 * @throws IOException
 */
private  void pickerReady(OrderManager orderManager, PickerManager pickerManager,
        WarehousePicking warehousePicking,  Logger LOGGER, String[] userInput, Picker currentPicker) throws IOException {
    
    HashMap<Integer, Order> newOrderMap = orderManager.generatePick();
    if (orderManager.hasNext() == 0) {
      LOGGER.log(Level.FINE,"not enough orders for " + currentPicker.getName() );
      pickerManager.addFreePicker(currentPicker);
      LOGGER.log(Level.FINE,"add " + currentPicker.getName() + "as a free picker to the system");
      // add ready picker who does is waiting for
      // request in Arraylist:freePicker

    } else {
        currentPicker.addLocation(
          warehousePicking.optimize(warehousePicking.pickRequest(newOrderMap)));
        currentPicker.setRequestid(orderManager.hasNext());

      //Print out the current picker location.
    LOGGER.log(Level.FINE,"Picker " + userInput[1] + " resived the order location. they are on their way.");
      LOGGER.log(Level.FINE, "Picker " + userInput[1] + " go to location: " + currentPicker.getNextLocation() );
    
    }
}
  
/***
 * when picker picked one item form the wares house, it will use the brocade ready
 * scan the SKU and tell the system
 * @param pickerManager
 * @param warehouseA
 * @param LOGGER
 * @param userInput
 * @param currentPicker
 * @throws IOException
 */
private  void pickerPicked(PickerManager pickerManager, Warehouse warehouseA, Logger LOGGER,
        String[] userInput, Picker currentPicker) throws IOException {
    if (!pickerManager.getORaddPicker(userInput[1]).equals(null)) {
        String userInputSku = userInput[3];
        //check if the picker picked the correct Fasica
        if (currentPicker.checkPickerScanedCorrectSKU(userInputSku)){
            currentPicker.addtoFolkLift(userInputSku, warehouseA);
            //check if the picker get all 8 of the sku
            if (currentPicker.checkgotAllSKU()){ 
              LOGGER.log(Level.FINE,"picker " + userInput[1] + "should go to marshaling."); 
            }else{//picked did not have 8 sku, tell them go to next location
                String nextLocation = "Picker" + userInput[1] + " go to location: "
                        + pickerManager.getORaddPicker(userInput[1]).getNextLocation();
                LOGGER.log(Level.FINE, nextLocation);
            }
        //when picker picked wrong  Fascia from warehouse
        }else{
            LOGGER.log(Level.FINE,"You picked wrong Fasica, Sku did not match");
            String nextLocation = "Picker" + userInput[1] + " go to location: "
                    + pickerManager.getORaddPicker(userInput[1]).getNextLocation();
            LOGGER.log(Level.FINE,nextLocation);
            //we might need a method to put back the Fascia
        }
    
    }
}

/***
 *  when picker picked enough item, it will move to the Marshaling room
 *  and put all the item from forkeleft to the marshling room
 * @param pickerManager
 * @param hrsystemA
 * @param LOGGER
 * @param userInput
 * @param currentPicker
 * @throws IOException
 */
private  void pickerToMarshaling(PickerManager pickerManager, Hrsystem hrsystemA, Logger LOGGER,
    String[] userInput, Picker currentPicker) throws IOException {
hrsystemA.addtoSequencing(currentPicker.getRequestid(),currentPicker.getForkLift());
LOGGER.log(Level.FINE,"picker send his/her items to marshaling room.");
pickerManager.deletPicker(currentPicker);

}

}
