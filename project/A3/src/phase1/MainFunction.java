package phase1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainFunction {
	//String basePath = new File("").getAbsolutePath();


   
	public static String hrFilePath = new File("group_0411/project/A3/src/phase1/hrfile.csv").getAbsolutePath();
	public static String warehousePath = new File("group_0411/project/A3/src/phase1/warehouse.csv").getAbsolutePath();
	public static String transtanblePath = new File("group_0411/project/A3/src/phase1/translation.csv").getAbsolutePath();
	public static String genericSoftPath = new File("group_0411/project/A3/src/phase1/traversal_table.csv").getAbsolutePath();
	
	
	public static boolean newUnhandledRequest = false; // this variable should be in order manager. it tells you whether there is a new request
	// that has not yet been sent to the RequestManager
	
	public static void main(String[] args)   {
		String filePath = new File("").getAbsolutePath();
		System.out.println(filePath);
		
		OrderManager orderManager = new OrderManager();
		PickerManager pickerManager = new PickerManager();
		WarehousePicking warehousePicking = new WarehousePicking();
		hrSystem hrsystemA = new hrSystem();
		
		Warehouse WarehouseA = new Warehouse();
		Translate TranslateA = new Translate();
		
		//Receive the user path of Warehouse.csv and TranslationTable.csv
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter Warehouse.csv file path: ");
		//warehousePath = reader.next();
		System.out.println("Enter TranslationTable.csv file path: ");
		//transtanblePath = reader.next();
		System.out.println("Enter Hysystem.csv file path: ");
        //transtanblePath = reader.next();
        System.out.println("Enter Generic Software file path: ");
        //genericSoftPath = reader.next();
		
		//initial the warehouse and translation table
        try{
        System.out.println("reading file");
        WarehouseA.storageInital(warehousePath);
		TranslateA.readFromCSVFile(transtanblePath);
		hrsystemA.readFromCSVFile(hrFilePath);
		warehousePicking.warehousePickingreader(genericSoftPath);
        } catch (FileNotFoundException e){
        	System.out.println("something not right");
        }
        
		boolean shutdown = false;	
		
		while (!shutdown) {
			//user will input the command and use this program.
			String[] userInput;
			System.out.println("Please enter a command");
			userInput = reader.next().split(",");
			
			//Us a case statement to find out which command it used
			switch (userInput[0]){
			
				case "order": orderManager.addOrder(userInput[1],userInput[2],TranslateA);
				               System.out.println("New order has been created.");;

				case "picker": if (userInput[2].equals("ready")){
				    Picker someOne = new Picker(userInput[1]);
				        
				     pickerManager.addPicker(someOne);
				     HashMap<Integer, Order> newOrderMap =  orderManager.generatePick();
				    
				    if (orderManager.generateNext()==0){
				      System.out.println("not enough orders");
				      
				    }else{				      	      
				      someOne.addLocation(warehousePicking.optimize(warehousePicking.PickRequest(newOrderMap)));
				      someOne.setRequestid(orderManager.generateNext());
				      
				      System.out.println("Picker"+userInput[1]+"resived the order location. he is one his way ");
				      System.out.println("Picker"+ userInput[1] + " go to location: "+someOne.getLoc());
				     }
				    
				}
				
				else if(userInput[2].equals("picked")){ 
					System.out.println("Picker enter pickedsku");
					int userInput2= reader.nextInt();
					
					pickerManager.getPicker(userInput[1]).addtoFolkLift(userInput2);
					System.out.println("Picker"+ userInput[1] + " go to location: "+pickerManager.getPicker(userInput[1]).getLoc());
					}
				

				
				
			}
			
		
				
			}
			
			
			
			
		}

		
	}
	
