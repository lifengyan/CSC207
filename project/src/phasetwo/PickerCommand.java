package phasetwo;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PickerCommand acts as an helper function for the main function, it processes
 * all the userInput that starts with "picker"
 */
public class PickerCommand implements Cammand  {
  warehouseSystem theSystem;
  String[] userInput;
  Picker currentPicker;
  
  /**
   * Constructor for PickerCommand class, store warehouseSystem information and userInput to variables. 
   * <p>
   * This constructor create a new picker instance if the given picker does not exist in the pickerManager.
   * @param theSystem: warehouseSystem information containing all the manager classes
   * @param userInput: user input line as a string array
   */
  public PickerCommand (warehouseSystem theSystem, String[] userInput){
    this.theSystem = theSystem;
    this.userInput  = userInput;
    currentPicker = theSystem.pickerManager.getORaddPicker((userInput[1]));
  }
  
  /**
   * Processes user input of 'picker ready', 'picker pick' and 'picker marshaling'. 
   * Uses helper functions: pickerReady, pickerPicked and pickerToMarshaling
   */
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
  }}
  
  
/***
 * Process user input of "picker xxx ready", system will assign picking request to the picker.
 * @param orderManager		order system manager to generate picking request
 * @param pickerManager		picker system manager to store picker and their status
 * @param warehousePicking	generic software
 * @param LOGGER			log events
 * @param userInput			the user input string
 * @param currentPicker		Picker instance that is created here
 * @throws IOException		Exception
 */
private  void pickerReady(OrderManager orderManager, PickerManager pickerManager,
        WarehousePicking warehousePicking,  Logger LOGGER, String[] userInput, Picker currentPicker) throws IOException {
    
    HashMap<Integer, Order> newOrderMap = orderManager.generatePick();
    if (orderManager.hasNext() == 0) {
      LOGGER.log(Level.FINE,"not enough orders for " + currentPicker.getName() );//not enough orders to create a request yet.
      pickerManager.addFreePicker(currentPicker);		//put this picker to the freePicker list
      LOGGER.log(Level.FINE,"add " + currentPicker.getName() + "as a free picker to the system");
    } else {
        currentPicker.addLocation(
        warehousePicking.optimize(warehousePicking.pickRequest(newOrderMap)));	//8 locations generated by the generic software
        currentPicker.setRequestid(orderManager.hasNext());		//store the request id in the picker instance
        //tell picker to go to the first location
        LOGGER.log(Level.FINE,"Picker " + userInput[1] + " resived the order location. they are on their way.");
        LOGGER.log(Level.FINE, "Picker " + userInput[1] + " go to location: " + currentPicker.getNextLocation() );
    }
}
  
/***
 * Process user input of "picker pick SKU", simulate the event of picker scan barCode.
 * Tell picker to rescan if the picked SKU is incorrect. 
 * If picked SKU is correct, reduce number of fascia at certain location by 1. 
 * After picker got all 8 correct fascias in this request, send picker to marshaling. 
 * @param pickerManager		picker system manager 
 * @param warehouseA		warehouse status information
 * @param LOGGER			log events
 * @param userInput			user input string
 * @param currentPicker		the picker that is working on this request
 * @throws IOException		Exception
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
              LOGGER.log(Level.FINE,"picker " + userInput[1] + " please go to marshaling."); 
            }else{
            	//picked did not have 8 sku, tell them go to next location
                String nextLocation = "Picker " + userInput[1] + " please go to location: "
                        	+ pickerManager.getORaddPicker(userInput[1]).getNextLocation();
                LOGGER.log(Level.FINE, nextLocation);
            }
        //when picker picked wrong  Fascia from warehouse
        }else{
            LOGGER.log(Level.FINE,"picker " + userInput[1] +" picked wrong Fasica, Sku did not match");
            String nextLocation = "Picker " + userInput[1] + " please go to location: "
                    + pickerManager.getORaddPicker(userInput[1]).getNextLocation();
            LOGGER.log(Level.FINE,nextLocation);
        }
    }
}

/***
 * Helper function used when user got all 8 fascias in one picking request.
 * 
 * @param pickerManager		picker system manager
 * @param hrsystemA			human resources system manager (including sequencer)
 * @param LOGGER			log event
 * @param userInput			user input string
 * @param currentPicker		Picker instance with forkLift containing correct 8 fascias
 * @throws IOException		exception
 */
private  void pickerToMarshaling(PickerManager pickerManager, Hrsystem hrsystemA, Logger LOGGER,
    String[] userInput, Picker currentPicker) throws IOException {
	hrsystemA.addtoSequencing(currentPicker.getRequestid(),currentPicker.getForkLift());
	LOGGER.log(Level.FINE,"picker send his/her items to marshaling room.");
	pickerManager.deletPicker(currentPicker);
}
}
