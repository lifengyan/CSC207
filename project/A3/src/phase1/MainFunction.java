package phase1;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class MainFunction {

	public static String filePath = "";
	public static String warehousePath ="";
	public static String transtanblePath = "";
	
	
	public static boolean newUnhandledRequest = false; // this variable should be in ordermanager. it tells you whether there is a new request
	// that has not yet been sent to the RequestManager
	
	public static void main(String[] args) {
		OrderManager orderManager = new OrderManager();
		RequestManager requestManager = new RequestManager();
		PickerManager pickerManager = new PickerManager();//
		SequencerManager sequencerManager = new SequencerManager();
		ReplenisherManager replenisherManager = new ReplenisherManager();
		LoaderManager loaderManager = new loaderManager();
		
		
		Warehouse WarehouseA = new Warehouse();
		Translate TranslateA = new Translate();
		
		//Recive the user path of Warehouse.csv and TranslationTable.csv
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter Warehouse.csv file path: ");
		warehousePath = reader.next();
		System.out.println("Enter TranslationTable.csv file path: ");
		transtanblePath = reader.next();
		
		//initial the warehouse and translation table
		WarehouseA.storageInital(warehousePath);
		TranslateA.readFromCSVFile(transtanblePath);
			
		boolean shutdown = false;	
		
		while (!shutdown) {
			//user will input the command and use this program.
			String[] userInput;
			System.out.println("Please enter a command");
			userInput = reader.next().split(" ");
			
			//Us a case statement to find out which command it used
			switch (userInput[0]){
			
				case "order": orderManager.addOrder(userInput[1],userInput[2]);
				
				
				case "picker": if (userInput[2]=="ready"){
					pickerManager.storePicker(userInput[1]);}// changed the function name
				
				else if(userInput[2]=="picked"){ 
					System.out.println("Picker scan Barcode(please input one SKU):");
					int userInput2= reader.nextInt();
					pickerManager.getPicker(userInput[1]).addtoFolkLift(userInput2);}
				
				else if(userInput[2]=="Marshaling"){ 
					pickerManager.getPicker(userInput[1]).marshaling();}	;
				
				case "sequencer": sequencerManager.getSequencer(userInput[1]).sequencer();
				
				case "replenisher": replenisherManager.getReplenisher(userInput[1]).replenishing();
				
				case "loader": loaderManager.getLoader(userInput[1]).loading();
				
				case "clouse": shutdown = true;
			}
			
		
				
			}
			
			
			
			
		}

		
	}
	
}
