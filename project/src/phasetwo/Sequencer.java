package phasetwo;

import java.util.ArrayList;

public class Sequencer extends Worker {
  private int id;
  private int count = 0;
  private int cursku;
  private ArrayList<ArrayList<Integer>> pallets;
  

  public Sequencer(String name) {
    super(name);
    pallets = new ArrayList<ArrayList<Integer>>();
    
  }
  
  
  public void ready(int pickid) {
    this.id = pickid;
  }
  
  public int scan(int sku) {
    this.cursku = sku;
    return this.count;   
  }
  
  
  public boolean compare(OrderManager om) {
    int in = 3;
    while (in > -1) {
      if (om.getOrders().get(id - in).getFront() == cursku) {
        this.pallets.get(0).add(3-in, cursku);
        this.count++;
        return true;
      } else if (om.getOrders().get(id - in).getBack() == cursku){
        this.pallets.get(1).add(3-in, cursku);
        this.count++;
        return true;
      } else {
        in--;
      }
    }
    return false;  
  }
  
  public int rescan(int sku) {
    this.count = 0;
    this.cursku = sku;
    this.pallets = new ArrayList<ArrayList<Integer>>();
    return this.count;
  }
  
  
}
