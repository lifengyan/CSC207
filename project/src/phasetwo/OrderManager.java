package phasetwo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class OrderManager {
  private Map<Integer, Order> orders;
  private Map<Integer, Order> temOrder;
  private ArrayList<Order> repick;
  private static int trail = 4;
  private int hasnext = 0;
  private int freeorder = 0;

  /**
   * Creates a new empty OrderManager. temOrder is to store orders when there are less than four new
   * comes in repick is to store orders that need to be repicked
   */
  public OrderManager() {
    orders = new HashMap<Integer, Order>();
    temOrder = new HashMap<Integer, Order>();
    repick = new ArrayList<Order>();
  }


  /**
   * Add order to the temOrder system, if there are 4 orders, then they will all move to the orders
   * manager system at once.
   */
  public void addOrder(String colour, String model, Translate tr) {
    Order order = new Order(colour, model, tr);
    temOrder.put(order.getOrderid(), order);
    if (temOrder.size() == 4) {
      orders.putAll(temOrder);
      temOrder.clear();
      freeorder += 4;
    }

  }

  /**
   * The OrderManager will generate pick by looking at the trail number, see if there are 4 orders
   * left to be processed, if there are, then this will return a hashmap, using trail as a key and
   * Object is another HashMap, with the order that orders come in as key and Order as object.
   */
  public HashMap<Integer, Order> generatePick() {
    HashMap<Integer, Order> order = new HashMap<Integer, Order>();
    if (!this.repick.isEmpty()) {
      int mn = 1;
      while (mn < 5) {
        Order or = this.repick.remove(0);
        order.put(mn, or);
        mn++;
      }
      this.hasnext = order.get(4).getOrderid();
    } else {
      if (this.orders.containsKey(trail)) {
        for (int i = 0; i < 4; i++) {
          order.put(4 - i, this.orders.get(trail - i));
          this.orders.get(trail - i).setfrontStatus("picked");
          this.orders.get(trail - i).setbackStatus("picked");
        }
        this.hasnext = trail;
        trail += 4;
        freeorder -= 4;
      } else {
        this.hasnext = 0;
      }
    }

    return order;
  }

  /**
   * return how many free order left.
   * 
   * @return the integer of free order number 
   */
  public int freeOrderNumber() {
    return freeorder;
  }

  /**
   * Change the orders status in the main system to "repicked" and move them to the repick.
   * 
   * @param id the pickid
   */
  public void repick(int id) {
    int in = 3;
    while (in > -1) {
      this.orders.get(id - in).setfrontStatus("repick");
      this.orders.get(id - in).setbackStatus("repick");
      this.repick.add(this.orders.get(id - in));
      in--;
    }
  }

  /**
   * return the current picking id.
   */
  public int hasNext() {
    return this.hasnext;
  }

  /**
   * return the repick list.
   */
  public ArrayList<Order> getRepick() {
    return this.repick;
  }

  /**
   * get all the order.
   */
  public Map<Integer, Order> getOrders() {
    return this.orders;
  }
}
