package phasetwo;

import java.util.logging.Logger;

public class warehouseSystem {
	// all the variable are public, since other command will use them, 
	//this class is a way to store all the manaer together. 
  public OrderManager orderManager;
  public PickerManager pickerManager ;
  public WarehousePicking warehousePicking;
  public Hrsystem hrsystem ;
  public Warehouse warehouse ;
  public Translate translate ;
  public  Logger LOGGER;

  /***
   * constuct the warhousle System, put all other system into this class
   */
  public warehouseSystem(OrderManager orderManager, 
      Hrsystem hrsystemA,
      PickerManager pickerManager,
      WarehousePicking warehousePicking,
      Warehouse warehouse,  
      Translate translate,
      Logger LOGGER)    
  {
    this.orderManager = orderManager;
    this.pickerManager = pickerManager;
    this.hrsystem = hrsystemA;
    this.warehouse = warehouse;
    this.translate = translate;
    this .LOGGER = LOGGER;
    this.warehousePicking = warehousePicking;
  }
  
  
}
