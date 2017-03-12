package phase1;

import java.util.ArrayList;

public class Sequencer extends Worker {

  public Sequencer(String name) {
    super(name);

  }
  
  /**
   * Return ArrayList of ArrayList of front and back sku numbers of orders of they are correct
   * else will return a Arraylist of Arralist of current picked sku numbers and they are mistakes
   * in picking.
   * 
   */
  public ArrayList<ArrayList<Integer>> sequence(int pickid, ArrayList<Integer> pickedList,
      OrderManager om) {
    ArrayList<ArrayList<Integer>> whole = new ArrayList<ArrayList<Integer>>();
    ArrayList<Integer> front = new ArrayList<Integer>();
    ArrayList<Integer> back = new ArrayList<Integer>();
    int in = 3;
    while (in > -1) {
      int fr = om.getOrders().get(pickid - in).getFront();
      int bk = om.getOrders().get(pickid - in).getBack();
      if (pickedList.contains(fr) && pickedList.contains(bk)) {
        front.add(fr);
        back.add(bk);
        in--;
      } else {
        System.out.println("Picked wrong fascia");
        whole.add(pickedList);
        return whole;
      }
    }
    whole.add(front);
    whole.add(back);
    return whole;



  }
}
