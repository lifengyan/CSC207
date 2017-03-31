package phasetwo;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderCommand implements Cammand {

  private warehouseSystem theSystem;
  private String[] userInput;

  // pass all the warehouse system information to this picker
  public OrderCommand(warehouseSystem theSystem, String[] userInput) {
    this.theSystem = theSystem;
    this.userInput = userInput;
  }

  /**
   * order command excute, creat a order base on the input.
   */
  @Override
  public void execute() throws IOException {
    createorder(theSystem.orderManager, theSystem.pickerManager, theSystem.warehousePicking,
        theSystem.translate, theSystem.LOGGER, userInput);
  }

  /**
   * creat a order, if there are enough order and a free pickerm assign the picking task to this.
   * picker
   */
  private static void createorder(OrderManager orderManager, PickerManager pickerManager,
      WarehousePicking warehousePicking, Translate translateA, Logger logger, String[] userInput)
      throws IOException {
    orderManager.addOrder(userInput[1], userInput[2], translateA);
    logger.log(Level.FINE, "New order " + userInput[1] + " " + userInput[2] + " has been created.");

    // check if there is free picker && there are 4 orders to generate a request
    if (pickerManager.getFreePicker().size() != 0 && orderManager.freeOrderNumber() != 0) {
      // there is free pick and also enough order for pick
      Picker curFreePicker = pickerManager.getFreePicker().get(0);
      pickerManager.deletFreePicker(curFreePicker);
      String pickerName = curFreePicker.getName();
      logger.log(Level.FINE,
          "currently free picker " + pickerName + " recieve the picking request");
      // assign the free picker with the order
      HashMap<Integer, Order> newOrderMap = orderManager.generatePick();
      curFreePicker
          .addLocation(warehousePicking.optimize(warehousePicking.pickRequest(newOrderMap)));
      curFreePicker.setRequestid(orderManager.hasNext());
    } else if (pickerManager.getFreePicker().size() == 0) {
      logger.log(Level.FINE, "Currently not enough free picker or order for picking");
    }
  }
}
