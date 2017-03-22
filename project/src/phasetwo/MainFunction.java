package phasetwo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.*;
import java.util.logging.Level;

public class MainFunction {

  static String filePath = new File("").getAbsolutePath();

  public static String hrFilePath = new File("src/phaseone/hrfile.csv").getAbsolutePath();
  public static String warehousePath = new File("src/phaseone/warehouse.csv").getAbsolutePath();
  public static String transtanblePath = new File("src/phaseone/translation.csv").getAbsolutePath();
  public static String genericSoftPath = new File("src/phaseone/traversal_table.csv").getAbsolutePath();
  public static String orderFile = new File("src/phaseone/order.csv").getAbsolutePath();
  public static String commandFile = new File("src/16order.txt").getAbsolutePath();
  private static final Logger LOGGER = Logger.getLogger( MainFunction.class.getName() );
  public static boolean newUnhandledRequest = false;

  // that has not yet been sent to the RequestManager

  /**
   * Main methods.
   * 
   * @param args to run
   */
  public static void main(String[] args) {
	// Creating and Assigning handlers to LOGGER object
	Handler consoleHandler = new ConsoleHandler();
	Handler fileHandler = new FileHandler("./eventslog.log");
	LOGGER.addHandler(consoleHandler);
	LOGGER.addHandler(fileHandler);
	LOGGER.setLevel(Level.ALL);
	//Initiate all the Manager
    OrderManager orderManager = new OrderManager();
    PickerManager pickerManager = new PickerManager();
    WarehousePicking warehousePicking = new WarehousePicking();
    Hrsystem hrsystemA = new Hrsystem();

    Warehouse warehouseA = new Warehouse();
    Translate translateA = new Translate();
//test
    
    Scanner reader = new Scanner(System.in);
    
    
    try {
    	Scanner scanner = new Scanner(new FileInputStream(commandFile));
		
	
    
    // initial the warehouse and translation table

      System.out.println("reading file");
      LOGGER.log(Level.CONFIG, "reading warehouse.csv file"); 
      warehouseA.storageInital(warehousePath);
      LOGGER.log(Level.CONFIG, "reading transtanble.csv file"); 
      translateA.readFromcsvfile(transtanblePath);
      LOGGER.log(Level.CONFIG, "reading hrfile.csv file"); 
      hrsystemA.readFromcsvfile(hrFilePath);
      LOGGER.log(Level.CONFIG, "reading warehousePicking.csv file"); 
      warehousePicking.warehousePickingreader(genericSoftPath);
      

      boolean shutdown = false;

      while (scanner.hasNextLine()) {
        // user will input the command and use this program.
        String[] userInput;
        LOGGER.log(Level.FINER,"Please enter a command" );
        System.out.println("Please enter a command");
        userInput = scanner.next().split(",");
        LOGGER.log(Level.FINEST,"User input: " + userInput.toString() +" \n" );
        // Us a case statement to find out which command it used
        switch (userInput[0]) {

          case "order":
            createorder(orderManager, pickerManager, warehousePicking, translateA, LOGGER, userInput);
            break;

          case "picker":
               Picker currentPicker = pickerManager.getPicker(userInput[1]);
            if (userInput[2].equals("ready")) {
                pickerReady(orderManager, pickerManager, warehousePicking, LOGGER, userInput,currentPicker);

            } else if (userInput[2].equals("pick")) {
                pickerPicked(pickerManager, warehouseA, reader, LOGGER, userInput,currentPicker);

            } else if (userInput[2].equals("marshaling")) {
                pickerToMarshaling(pickerManager, hrsystemA, LOGGER, userInput,currentPicker);
            }
            break;
            
          case "sequencer":
            Sequencer currSequencer = hrsystemA.getSequencer(userInput[1]);
                if (userInput[2].equals("ready")) {
               
                     sequencerReady(hrsystemA, userInput, currSequencer);
                    
                }else if(userInput[2].equals("scan")){
                    //sequencer scan item one by one
                  currSequencer.scan(userInput[3]);
                  
                }else if(userInput[2].equals("rescan")){
                    //sequencer rescan
                }else if(userInput[2].equals("finish")){
                    //sequencer finish sequencing send all the item to loader
                }
         

           
            break;

          case "loader":
                Loader currentLoader = hrsystemA.getLoader(userInput[1]);
                if (userInput[2].equals("ready")) {
                  
                    
                }else if (userInput[2].equals("scan")){
                   //loader scan one by one
                }else if (userInput[2].equals("rescan")){
                    //loader rescan
                }else if (userInput[2].equals("loading")){
                  //loader finish loading send all the item to truck
                }
                
                
            System.out.println("Loader " + userInput[1] + " is loading");
            
            
            currentLoader.load(hrsystemA.loadingList, orderFile);
             
            writer.append("Loader " + userInput[1].toString() + " is loading" + "\n");
            
            break;

          case "close":
            shutdown = true;
            break;
          default:
              
               writer.append("Error during enter command" + "\n");
            break;

        }
     

      }
      reader.close();
      warehouseA.writeDown(warehousePath);
      

    } catch (FileNotFoundException ex) {
      LOGGER.log(Level.SEVERE, "File reading incorrect", ex); 
  } catch (IOException ex) {
    ex.printStackTrace();
  }
    


  }

  private static void sequencerReady(Hrsystem hrsystemA, String[] userInput,
      Sequencer currSequencer) {
    if (hrsystemA.getSequencingid() == 0) {
          LOGGER.log(Level.FINE,"not enough for Sequenceing" + "\n");
        } else {
          Integer sequencingId = hrsystemA.getSequencingid();
          currSequencer.ready(hrsystemA.getSequencingid());
          LOGGER.log(Level.FINE, "Sequencer " + userInput[1]+" resive the picked ID of " + 
          hrsystemA.getSequencingid().toString());
        }
  }

private static void pickerToMarshaling(PickerManager pickerManager, Hrsystem hrsystemA, Logger LOGGER,
		String[] userInput, Picker currentPicker) throws IOException {
	hrsystemA.addtoSequencing(currentPicker.getRequestid(),currentPicker.getForkLift());
	LOGGER.log(Level.FINE,"picker send his/her items to marshaling room.");
	pickerManager.deletPicker(currentPicker);
	
}

private static void pickerPicked(PickerManager pickerManager, Warehouse warehouseA, Scanner reader, Logger LOGGER,
		String[] userInput, Picker currentPicker) throws IOException {
	if (!pickerManager.getPicker(userInput[1]).equals(null)) {
		String userInputSku = userInput[3];
		//check if the picker picked the correct Fasica
		if (currentPicker.checkPickerScanedCorrectSKU(userInputSku)){
			currentPicker.addtoFolkLift(userInputSku, warehouseA);
			//check if the picker get all 8 of the sku
			if (currentPicker.checkgotAllSKU()){ 
			  LOGGER.log(Level.FINE,"picker " + userInput[1] + "should go to marshaling.");	
			}else{//picked did not have 8 sku, tell them go to next location
				String nextLocation = "Picker" + userInput[1] + " go to location: "
			            + pickerManager.getPicker(userInput[1]).getLoc();
				LOGGER.log(Level.FINE, nextLocation);
			}
		//when picker picked wrong  Fascia from warehouse
		}else{
		    LOGGER.log(Level.FINE,"You picked wrong Fasica, Sku did not match");
			String nextLocation = "Picker" + userInput[1] + " go to location: "
		            + pickerManager.getPicker(userInput[1]).getLoc();
			LOGGER.log(Level.FINE,nextLocation);
			//we might need a method to put back the Fascia
		}
	
	}
}

private static void pickerReady(OrderManager orderManager, PickerManager pickerManager,
		WarehousePicking warehousePicking,  Logger LOGGER, String[] userInput, Picker currentPicker) throws IOException {
	
	HashMap<Integer, Order> newOrderMap = orderManager.generatePick();
	if (orderManager.generateNext() == 0) {
	  LOGGER.log(Level.FINE,"not enough orders for " + currentPicker.getName() );
	  pickerManager.addFreePicker(currentPicker);
	  LOGGER.log(Level.FINE,"add " + currentPicker.getName() + "as a free picker to the system");
	  // add ready picker who does is waiting for
	  // request in Arraylist:freePicker

	} else {
		currentPicker.addLocation(
	      warehousePicking.optimize(warehousePicking.pickRequest(newOrderMap)));
		currentPicker.setRequestid(orderManager.generateNext());

	  //Print out the current picker location.
	LOGGER.log(Level.FINE,"Picker " + userInput[1] + " resived the order location. they are on their way.");
	  LOGGER.log(Level.FINE, "Picker " + userInput[1] + " go to location: " + currentPicker.getLoc() );
	
	}
}

private static void createorder(OrderManager orderManager, PickerManager pickerManager,
		WarehousePicking warehousePicking, Translate translateA, Logger LOGGER, String[] userInput)
		throws IOException {
	orderManager.addOrder(userInput[1], userInput[2], translateA);
	  LOGGER.log(Level.FINE, "New order " + userInput[1]+ " " + userInput[2] + " has been created.");
	  
	  // check if there is free picker && there are 4 orders to generate a request
	  if (pickerManager.getFreePicker().size() != 0 && orderManager.hasNext() != 0) {
	    //there is free pick and also enough order for pick 
	    Picker curFreePicker = pickerManager.getFreePicker().get(0);
	    pickerManager.deletFreePicker(curFreePicker);
	    String pickerName = curFreePicker.getName();
	    LOGGER.log(Level.FINE,"currently free picker " + pickerName + " resive the picking request");
	    //assign the free picker with the order
	    HashMap<Integer, Order> newOrderMap = orderManager.generatePick();
	    curFreePicker.addLocation(warehousePicking.optimize(warehousePicking.pickRequest(newOrderMap)));
	    curFreePicker.setRequestid(orderManager.generateNext());
	  } else if (pickerManager.getFreePicker().size() == 0) {
	    LOGGER.log(Level.FINE,"Currently not enough free picker or order for picking");
	  }
}


}


