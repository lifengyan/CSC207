package phase1;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class MainFunction {

	public static String hrFilePath = "";
	public static String warehousePath ="";
	public static String transtanblePath = "";
	
	
	public static boolean newUnhandledRequest = false; // this variable should be in ordermanager. it tells you whether there is a new request
	// that has not yet been sent to the RequestManager
	
	public static void main(String[] args) {
		OrderManager orderManager = new OrderManager();
		RequestManager requestManager = new RequestManager();
		HrSystem hrsystem = new HrSystem();
		
		Warehouse WarehouseA = new Warehouse();
		Translate TranslateA = new Translate();
		
		//Recive the user path of Warehouse.csv and TranslationTable.csv
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter Warehouse.csv file path: ");
		warehousePath = reader.next();
		System.out.println("Enter TranslationTable.csv file path: ");
		transtanblePath = reader.next();
		System.out.println("Enter Hysystem.csv file path: ");
        transtanblePath = reader.next();
		
		//initial the warehouse and translation table
		WarehouseA.storageInital(warehousePath);
		TranslateA.readFromCSVFile(transtanblePath);
		hrsystem.readFromcsv(hrFilePath);
			
		boolean shutdown = false;	
		
		while (!shutdown) {
			//user will input the command and use this program.
			String[] userInput;
			System.out.println("Please enter a command");
			userInput = reader.next().split(" ");
			
			//Us a case statement to find out which command it used
			switch (userInput[0]){
			
				case "order": orderManager.addOrder(userInput[1],userInput[2],TranslateA);

				case "picker": if (userInput[2]=="ready"){
					pickerManager.add(userInput[1]);}
				
				else if(userInput[2]=="picked"){ 
					System.out.println("Picker enter pickedsku");
					int userInput2= reader.nextInt();

					pickerManager.getPicker(userInput[1]).picke(userInput2);}
					hrsystem.getWorker(userInput[1]).addtoFolkLift(userInput2);}

				
				else if(userInput[2]=="Marshaling"){ 
				  hrsystem.getWorker(userInput[1]).marshaling();}	;
				

				case "sequencer": sequencerManager.getSequencer(userInput[1]).sequence();

				case "sequencer": hrsystem.getWorker(userInput[1]).sequencer();

				
				case "replenisher": hrsystem.getWorker(userInput[1]).replenishing();
				
				case "loader": hrsystem.getWorker(userInput[1]).loading();
				
				case "clouse": shutdown = true;
			}
			
		
				
			}
			
			
			
			
		}

		
	}
	
}
