package phasetwo;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Scanner;

public class MainFunction {

  static String filePath = new File("").getAbsolutePath();

  public static String hrFilePath = new File("src/phaseone/hrfile.csv").getAbsolutePath();
  public static String warehousePath = new File("src/phaseone/warehouse.csv").getAbsolutePath();
  public static String transtanblePath = new File("src/phaseone/translation.csv").getAbsolutePath();
  public static String genericSoftPath = new File("src/phaseone/traversal_table.csv").getAbsolutePath();
  public static String eventFile = new File("src/phaseone/events.txt").getAbsolutePath();
  public static String orderFile = new File("src/phaseone/order.csv").getAbsolutePath();

  public static boolean newUnhandledRequest = false;

  // that has not yet been sent to the RequestManager

  /**
   * Main methods.
   * 
   * @param args to run
   */
  public static void main(String[] args) {

    System.out.println(filePath);
    


    OrderManager orderManager = new OrderManager();
    PickerManager pickerManager = new PickerManager();
    WarehousePicking warehousePicking = new WarehousePicking();
    Hrsystem hrsystemA = new Hrsystem();

    Warehouse warehouseA = new Warehouse();
    Translate translateA = new Translate();
//test
    
    Scanner reader = new Scanner(System.in);
    
    
    try {
		FileWriter writer = new FileWriter(eventFile);
		writer.append("ware House 1.0 event file.");
		
	
    
    // initial the warehouse and translation table
    try {
      System.out.println("reading file");
      writer.append("reading warehouse.csv file\n");
      warehouseA.storageInital(warehousePath);
      writer.append("reading transtanble.csv file\n");
      translateA.readFromcsvfile(transtanblePath);
      writer.append("reading hrfile.csv file\n");
      hrsystemA.readFromcsvfile(hrFilePath);
      writer.append("reading warehousePicking.csv file\n");
      warehousePicking.warehousePickingreader(genericSoftPath);
    } catch (FileNotFoundException ex) {
      System.out.println("File reading incorrect");
      writer.append("File reading incorrect\n");
    }

    boolean shutdown = false;

    while (!shutdown) {
      // user will input the command and use this program.
      String[] userInput;
      System.out.println("Please enter a command");
      userInput = reader.next().split(",");
      writer.append("User input: " + userInput.toString() +" \n");

      // Us a case statement to find out which command it used
      switch (userInput[0]) {

        case "order":
          createorder(orderManager, pickerManager, warehousePicking, translateA, writer, userInput);
          break;

        case "picker":
        	 Picker currentPicker = pickerManager.getPicker(userInput[1]);
          if (userInput[2].equals("ready")) {
        	  pickerReady(orderManager, pickerManager, warehousePicking, writer, userInput,currentPicker);

          } else if (userInput[2].equals("pick")) {
        	  pickerPicked(pickerManager, warehouseA, reader, writer, userInput,currentPicker);

          } else if (userInput[2].equals("marshaling")) {
        	  pickerToMarshaling(pickerManager, hrsystemA, writer, userInput,currentPicker);
          }
          break;
          
        case "sequencer":
        	
        	  if (userInput[2].equals("ready")) {
        		   Sequencer sequencer = hrsystemA.getSequencer(userInput[1]);
        		   if (hrsystemA.getSequencingid() == 0) {
        	            System.out.println("not enough for Sequenceing");
        	            writer.append("not enough for Sequenceing" + "\n");
        	          } else {
        	            Integer sequencingId = hrsystemA.getSequencingid();

        	            hrsystemA.addToloader(sequencingId, sequencer.sequence(sequencingId,
        	            	    hrsystemA.getSequencingItem(sequencingId), orderManager));
        	            System.out.println("Sequenced, send it to loader");
        	            writer.append("Sequenced, send it to loader" + "\n");
        	          }
        		  
        	  }else if(userInput[2].equals("scan")){
        		  //sequencer scan item one by one
        	  }else if(userInput[2].equals("rescan")){
        		  //sequencer rescan
        	  }else if(userInput[2].equals("finish")){
        		  //sequencer finish sequencing send all the item to loader
        	  }
       

         
          break;

        case "loader":
        	  Loader someONe = hrsystemA.getLoader(userInput[1]);
        	  if (userInput[2].equals("ready")) {
        		
        		  
        	  }else if (userInput[2].equals("scan")){
        		 //loader scan one by one
        	  }else if (userInput[2].equals("rescan")){
        		  //loader rescan
        	  }else if (userInput[2].equals("loading")){
        		//loader finish loading send all the item to truck
        	  }
        	  
        	  
          System.out.println("Loader " + userInput[1] + " is loading");
          
          
          someONe.load(hrsystemA.loadingList, orderFile);
           
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
    try {
      warehouseA.writeDown(warehousePath);
    } catch (IOException ex) {
         ex.printStackTrace();
    }
    
    writer.close();
    } catch (IOException e) {
		System.out.println("Event File does not extis");
	}
    


  }

private static void pickerToMarshaling(PickerManager pickerManager, Hrsystem hrsystemA, FileWriter writer,
		String[] userInput, Picker currentPicker) throws IOException {
	
	System.out.println("picker send his/her items to marshaling room.");
	  writer.append("picker send his/her items to marshaling room." + "\n");
	hrsystemA.addtoSequencing(currentPicker.getRequestid(),
			currentPicker.getForkLift());
	pickerManager.deletPicker(currentPicker);
}

private static void pickerPicked(PickerManager pickerManager, Warehouse warehouseA, Scanner reader, FileWriter writer,
		String[] userInput, Picker currentPicker) throws IOException {
	if (!pickerManager.getPicker(userInput[1]).equals(null)) {
		String userInputSku = userInput[3];
		//check if the picker picked the correct Fasica
		if (currentPicker.checkPickerScanedCorrectSKU(userInputSku)){
			currentPicker.addtoFolkLift(userInputSku, warehouseA);
			//check if the picker get all 8 of the sku
			if (currentPicker.checkgotAllSKU()){ 
				 System.out.println("picker " + userInput[1] + "should go to marshaling.");	
			}else{//picked did not have 8 sku, tell them go to next location
				String nextLocation = "Picker" + userInput[1] + " go to location: "
			            + pickerManager.getPicker(userInput[1]).getLoc();
				System.out.println(nextLocation);
			}
		//when picker picked wrong  Fascia from warehouse
		}else{
			System.out.println("You picked wrong Fasica, Sku did not match");
			String nextLocation = "Picker" + userInput[1] + " go to location: "
		            + pickerManager.getPicker(userInput[1]).getLoc();
			System.out.println(nextLocation);
			//we might need a method to put back the Fascia
		}
	
	}
}

private static void pickerReady(OrderManager orderManager, PickerManager pickerManager,
		WarehousePicking warehousePicking, FileWriter writer, String[] userInput, Picker currentPicker) throws IOException {
	
	HashMap<Integer, Order> newOrderMap = orderManager.generatePick();
	if (orderManager.generateNext() == 0) {
	  System.out.println("not enough orders");
	  writer.append("Not enough orders. \n");
	  pickerManager.addFreePicker(currentPicker);
	  // add ready picker who does is waiting for
	  // request in Arraylist:freePicker

	} else {
		currentPicker.addLocation(
	      warehousePicking.optimize(warehousePicking.pickRequest(newOrderMap)));
		currentPicker.setRequestid(orderManager.generateNext());

	  //Print out the current picker location.
	  System.out.println(
	      "Picker " + userInput[1] + " resived the order location. he is one his way ");
	  writer.append("Picker " + userInput[1].toString() + " resived the order location. he is one his way. "+ "\n");
	  String pickerlocation = "Picker " + userInput[1] + " go to location: " + currentPicker.getLoc();
	  System.out.println( pickerlocation);
	  writer.append( pickerlocation.toString() + "\n");
	}
}

private static void createorder(OrderManager orderManager, PickerManager pickerManager,
		WarehousePicking warehousePicking, Translate translateA, FileWriter writer, String[] userInput)
		throws IOException {
	orderManager.addOrder(userInput[1], userInput[2], translateA);
	  System.out.println("New order has been created.");
	  writer.append("New order has been created\n");

	  // check if there is free picker && there are 4 orders to generate a request
	  if (pickerManager.getFreePicker().size() != 0 && orderManager.hasNext() != 0) {
	    Picker cur = pickerManager.getFreePicker().get(0);
	    pickerManager.deletFreePicker(cur);
	    String pickerName = cur.getName();
	    System.out.println("currently free picker:" + pickerName);
	    writer.append("currently free picker:" + pickerName +" \n");
	    HashMap<Integer, Order> newOrderMap = orderManager.generatePick();
	    cur.addLocation(warehousePicking.optimize(warehousePicking.pickRequest(newOrderMap)));
	    cur.setRequestid(orderManager.generateNext());
	    System.out.println(cur.getName());
	  } else if (pickerManager.getFreePicker().size() == 0) {
	    System.out.println("Currently no free picker");
	    writer.append("Currently no free picker. \n");
	  }
}


}


