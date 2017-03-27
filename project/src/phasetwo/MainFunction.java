package phasetwo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.*;
import java.util.logging.Level;
/**
 * This system get inputs from user to simulate real world event happens in the company: receiving orders, 
 * send picking request, assign picking request to pickers, send picker location information, 
 * keep track of order status. 
 * <p>
 * This system takes input files: translation.csv, traversal_table.csv, warehouse.csv, hrfile.csv and 16order.txt
 * This system generates two output files: warehouse.csv and order.csv.
 * For more details please refer to UML.pdf and help.txt
 *
 */
public class MainFunction {
	// set up all the files
  static String filePath = new File("").getAbsolutePath();
  public static String hrFilePath = new File("src/phasetwo/hrfile.csv").getAbsolutePath();
  public static String warehousePath = new File("src/phasetwo/warehouse.csv").getAbsolutePath();
  public static String transtanblePath = new File("src/phasetwo/translation.csv").getAbsolutePath();
  public static String genericSoftPath = new File("src/phasetwo/traversal_table.csv").getAbsolutePath();
  public static String orderFile = new File("src/phasetwo/order.csv").getAbsolutePath();
  public static String commandFile = new File("src/phasetwo/16orders.txt").getAbsolutePath();
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
	Handler fileHandler = new FileHandler("log.txt");
	consoleHandler.setLevel(Level.ALL);
	fileHandler.setLevel(Level.ALL);
	fileHandler.setFormatter(new SimpleFormatter());
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
    Scanner scanner = new Scanner(new FileInputStream(commandFile));
    // change the logger format to simple type 
    // initial the warehouse and translation table
    // fix git vairson 
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
      while (scanner.hasNextLine()) {
        // user will input the command and use this program.
        String[] userInput;
        LOGGER.log(Level.FINER,"Please enter a command" );
        String logevent;
        logevent=scanner.next();
        userInput = logevent.split(",");
        LOGGER.log(Level.FINEST,"User input: " + logevent +" \n" );
        // Us a case statement to find out which command it used
        switch (userInput[0]) {

          case "order":
            Cammand OrderCommand = new OrderCommand(theSystem,userInput);
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
        
            break;
            
          default:
            LOGGER.log(Level.INFO,"Error during enter command" + "\n");
            break;
        }
      }
      scanner.close();
      warehouseA.writeDown(warehousePath);
      LOGGER.log(Level.CONFIG, "Program Closed"); 
    } catch (FileNotFoundException ex) {
      LOGGER.log(Level.SEVERE, "File reading incorrect", ex); 
  } catch (IOException ex) {
    ex.printStackTrace();
  }

  }


}


