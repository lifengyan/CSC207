package phasetwo;

import java.util.ArrayList;

public class Sequencer implements Worker {
  private int id;
  private  String name;
  private int count = 0;
  private String cursku;
  private ArrayList<String> frontpallet;
  private ArrayList<String> backpallet;

  /**
   * Initiate a new sequencer.
   * @param name Sequencer's name
   */
  public Sequencer(String name) {
    frontpallet = new ArrayList<String>();
    backpallet = new ArrayList<String>();
    this.name = name;
        
  }

  /**
   * Retrun the pickId of the picking request that the sequencer is processing.
   * @return the pickId of the picking request that the sequencer is processing
   */

  /**
   * return pickerid
   */
  public int getid() {
    return id;
  }
  /***
   * assign the id to picker
   */
  public void ready(int pickid) {
    this.id = pickid;
  }
  /***
   * picker enter the barcode reading sky, return total number of crorrect sku
   */
  public int scan(String sku) {
    this.cursku = sku;
    return this.count;
  }

  /**
   * See if order manager with pickid contains this fascia.
   * @param om OrderManager
   * @return boolean
   */
  public boolean compare(OrderManager om) {
    int in = 3;
    while (in > -1) {
      if (om.getOrders().get(id - in).getFront().equals(cursku)) {
        if (!om.getOrders().get(id - in).getfrontStatus().equals("sequenced")) {
          frontpallet.add(cursku);
          this.count++;
          om.getOrders().get(id - in).setfrontStatus("sequenced");
          return true;
        }else{
          in--;
        }
        
      } else if (om.getOrders().get(id - in).getBack().equals(cursku)) {
        if (!om.getOrders().get(id - in).getbackStatus().equals("sequenced")) {
          backpallet.add(cursku);
          this.count++;
          om.getOrders().get(id - in).setbackStatus("sequenced");
          return true;
        }else{in--;}
      } else {
        in--;
      }
    }
    repick(om);
    return false;
  }

  /**
   * Reset all sequencers front pallet, count and set back the status or orders that they scaned.
   * @param om the OrderManager
   */
  public void rescan(OrderManager om) {
    this.count = 0;
    frontpallet = new ArrayList<String>();
    backpallet = new ArrayList<String>();
    int in = 3;
    while (in > -1) {
      om.getOrders().get(id - in).setfrontStatus("ordered");
      om.getOrders().get(id - in).setbackStatus("ordered");
      in--;
    }
  }

  /**
   * When the sequencer knows all 8 fascias are scanned and checked correct, they will 
   * sequence it to pallets.
   * @param om OrderManager
   * @return two pallets. first the front,second the back.
   */
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
  /***
   * find error, send back to Order Manager to repick
   */
  public void repick(OrderManager om) {
    om.repick(this.id);
  }
  /***
   * return woker`s name
   */
  public String getName() {
    // TODO Auto-generated method stub
    return name;
  }




}
