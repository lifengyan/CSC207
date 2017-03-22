package phasetwo;

import java.util.ArrayList;

public class Sequencer extends Worker {
  private int id;
  private int count = 0;
  private int cursku;
  private ArrayList<Integer> frontpallet;
  private ArrayList<Integer> backpallet;
  

  public Sequencer(String name) {
    super(name);
    frontpallet = new ArrayList<Integer>();
    backpallet = new ArrayList<Integer>();
    
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
        frontpallet.add(cursku);
        this.count++;
        return true;
      } else if (om.getOrders().get(id - in).getBack() == cursku){
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
  
  public int rescan(int sku) {
    this.count = 0;
    this.cursku = sku;
    frontpallet = new ArrayList<Integer>();
    backpallet = new ArrayList<Integer>();
    return this.count;
  }
  
  public ArrayList<ArrayList<Integer>> sequencing(OrderManager om) {
    frontpallet = new ArrayList<Integer>();
    backpallet = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> whole = new ArrayList<ArrayList<Integer>>();
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
    
  
  
  
}
