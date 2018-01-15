package phasetwo;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.junit.Before;
import org.junit.Test;


/**
 * Set up OrderCommand.
 *
 */
public class OrderCommandTest {
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
  OrderManager orderManager = new OrderManager();
  PickerManager pickerManager = new PickerManager();
  WarehousePicking warehousePicking = new WarehousePicking();
  Hrsystem hrsystemA = new Hrsystem();
  Warehouse warehouseA = new Warehouse();
  Translate translateA = new Translate();
  Handler consoleHandler = new ConsoleHandler();
  warehouseSystem theSystem = new warehouseSystem(orderManager, hrsystemA, pickerManager,
      warehousePicking, warehouseA, translateA, LOGGER);


  /**
   * Set up order command.
   * 
   * @throws java.lang.Exception when there is an exception.
   */
  @Before
  public void setUp() throws Exception {
    Handler consoleHandler = new ConsoleHandler();
    Handler fileHandler = new FileHandler("log.txt");
    consoleHandler.setLevel(Level.ALL);
    fileHandler.setLevel(Level.ALL);
    fileHandler.setFormatter(new SimpleFormatter());
    LOGGER.addHandler(consoleHandler);
    LOGGER.addHandler(fileHandler);
    LOGGER.setLevel(Level.ALL);
  }

  /**
   * test order command.
   * 
   * @throws IOException exception of execute
   * @throws java.lang.Exception catch this exception
   */
  @Test
  public void testOrderCommand() throws IOException {
    String[] input = new String[] {"order", "Blue", "SES"};
    Picker pk = new Picker("Amber");
    pickerManager.addFreePicker(pk);
    OrderCommand oc1 = new OrderCommand(theSystem, input);
    oc1.execute();
    OrderCommand oc2 = new OrderCommand(theSystem, input);
    oc2.execute();
    OrderCommand oc3 = new OrderCommand(theSystem, input);
    oc3.execute();
    OrderCommand oc4 = new OrderCommand(theSystem, input);
    oc4.execute();
    assertTrue(!(orderManager.hasNext() == 0));
  }


}
