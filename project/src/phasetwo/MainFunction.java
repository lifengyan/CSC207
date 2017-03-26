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
    try {
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
      
      warehouseSystem theSystem =  new warehouseSystem (orderManager,hrsystemA,pickerManager,warehousePicking,warehouseA,translateA,LOGGER);
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
            Cammand OrderCommand = new PickerCommand(theSystem,userInput);
            OrderCommand.execute();
            break;

          case "picker":
            Cammand PickerCommand = new PickerCommand(theSystem,userInput);
            PickerCommand.execute();
            break;
            
          case "sequencer":
            Cammand SqeuencerCommand = new SqeuencerCommand(theSystem,userInput);
            SqeuencerCommand.execute();
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
             
            LOGGER.log(Level.FINE,"Loader " + userInput[1].toString() + " is loading" + "\n");
            
            break;

          case "close":
            shutdown = true;
            break;
            
          default:
            LOGGER.log(Level.INFO,"Error during enter command" + "\n");
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




}


