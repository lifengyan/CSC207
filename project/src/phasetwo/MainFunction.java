package phasetwo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * This system get inputs from user to simulate real world event happens in the company: receiving
 * orders, send picking request, assign picking request to pickers, send picker location
 * information, keep track of order status. This system takes input files: translation.csv,
 * traversal_table.csv, warehouse.csv, hrfile.csv and 16order.txt This system generates two output
 * files: warehouse.csv and order.csv. For more details please refer to UML.pdf and help.txt
 *
 */
public class MainFunction {
  // set up all the files
  static String filePath = new File("").getAbsolutePath();
  public static String hrFilePath = new File("src/phasetwo/hrfile.csv").getAbsolutePath();
  public static String warehousePath = new File("src/phasetwo/warehouse.csv").getAbsolutePath();
  public static String transtanblePath = new File("src/phasetwo/translation.csv").getAbsolutePath();
  public static String genericSoftPath =
      new File("src/phasetwo/traversal_table.csv").getAbsolutePath();
  public static String orderFile = new File("src/phasetwo/order.csv").getAbsolutePath();
  public static String commandFile = new File("src/phasetwo/16orders.txt").getAbsolutePath();
  private static final Logger LOGGER = Logger.getLogger(MainFunction.class.getName());
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

      // Initiate all the Manager

     
      Warehouse warehouseA = new Warehouse();
      System.out.println("reading file");
      LOGGER.log(Level.CONFIG, "reading warehouse.csv file");
      warehouseA.storageInital(warehousePath);
      
      Translate translateA = new Translate();
      LOGGER.log(Level.CONFIG, "reading transtanble.csv file");
      translateA.readFromcsvfile(transtanblePath);
      
      Hrsystem hrsystemA = new Hrsystem();
      LOGGER.log(Level.CONFIG, "reading hrfile.csv file");
      hrsystemA.readFromcsvfile(hrFilePath);
      
      WarehousePicking warehousePicking = new WarehousePicking();
      LOGGER.log(Level.CONFIG, "reading warehousePicking.csv file");
      warehousePicking.warehousePickingreader(genericSoftPath);
      
      OrderManager orderManager = new OrderManager();
      PickerManager pickerManager = new PickerManager();
      
      Scanner scanner = new Scanner(new FileInputStream(commandFile));

      warehouseSystem theSystem = new warehouseSystem(orderManager, hrsystemA, pickerManager,
          warehousePicking, warehouseA, translateA, LOGGER);
      while (scanner.hasNextLine()) {
        // user will input the command and use this program.
        String[] userInput;
        LOGGER.log(Level.FINER, "Please enter a command");
        String logevent;
        logevent = scanner.nextLine();
        userInput = logevent.split("\\s+");
        LOGGER.log(Level.FINEST, "User input: " + logevent + " \n");
        // Us a case statement to find out which command it used
        switch (userInput[0]) {

          case "order":
            Cammand orderCommand = new OrderCommand(theSystem, userInput);
            orderCommand.execute();
            break;

          case "picker":
            Cammand pickerCommand = new PickerCommand(theSystem, userInput);
            pickerCommand.execute();
            break;

          case "sequencer":
            Cammand sqeuencerCommand = new SqeuencerCommand(theSystem, userInput);
            sqeuencerCommand.execute();
            break;

          case "loader":
            Cammand loaderCommand = new LoaderCommand(theSystem, userInput);
            loaderCommand.execute();
            break;
          case "replenisher":
            Cammand replenisherCommand = new ReplenisherCommand(theSystem, userInput);
            replenisherCommand.execute();
            break;
          default:
            LOGGER.log(Level.INFO, "Input did not follow the requirement" + "\n");
            break;
        }
      }
      scanner.close();
      warehouseA.writeDown(warehousePath);
      LOGGER.log(Level.CONFIG, "Program Closed");
    } catch (FileNotFoundException ex) {
      LOGGER.log(Level.SEVERE, "File reading incorrect", ex);
    } catch (NullPointerException en) {
      LOGGER.log(Level.SEVERE, "Error during enter command", en);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}


