package phasetwo;

import java.util.ArrayList;

public class Sequencer extends Worker {
  private int id;
  private int count = 0;
  private String cursku;
  private ArrayList<String> frontpallet;
  private ArrayList<String> backpallet;

  public Sequencer(String name) {
    super(name);
    frontpallet = new ArrayList<String>();
    backpallet = new ArrayList<String>();
    
  }
  
  
  public void ready(int pickid) {
    this.id = pickid;
  }
  
  public int scan(String sku) {
    this.cursku = sku;
    return this.count;   
  }
  
  
  public boolean compare(OrderManager om) {
    int in = 3;
    while (in > -1) {
      if (om.getOrders().get(id - in).getFront().equals(cursku)) {
        frontpallet.add(cursku);
        this.count++;
        return true;
      } else if (om.getOrders().get(id - in).getBack().equals(cursku)){
        backpallet.add(cursku);
        this.count++;
        return true;
      } else {
        in--;
      }
    }
    //sequencer sendback id; ordermanager repick;
    
    return false;  
  }
  
  public int rescan(String sku) {
    this.count = 0;
    this.cursku = sku;
    frontpallet = new ArrayList<String>();
    backpallet = new ArrayList<String>();
    return this.count;
  }
  
  public ArrayList<ArrayList<String>> sequencing(OrderManager om) {
    frontpallet = new ArrayList<String>();
    backpallet = new ArrayList<String>();
    ArrayList<ArrayList<String>> whole = new ArrayList<ArrayList<String>>();
    int in = 3;
    while (in > -1) {
      frontpallet.add(om.getOrders().get(id - in).getFront());
      backpallet.add(om.getOrders().get(id - in).getBack());
        in--;
    }
    whole.add(frontpallet);
    whole.add(backpallet);
    return whole;
    
    }
  
  public void repick(OrderManager om) {
   
  }
    
  
  
  
}
