package phase1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderManager {
  private Map<Integer, Order> orders;
  private Map<Integer, Order> temOrder;
  private static int trail = 4;
  private int hasnext = 0;

  /**
   * Creates a new empty OrderManager.
   */
  public OrderManager() {
    orders = new HashMap<Integer, Order>();
    temOrder = new HashMap<Integer, Order>();

  }
  
  /**
   * Add order to the temOrder system, if there are 4 orders, then they will
   * all move to the orders manager system at once.
   */
  public void addOrder(String colour, String model, Translate tr) {
  
    Order order = new Order(colour, model, tr);
    temOrder.put(order.getOrderCount(), order);
    if (temOrder.size() == 4) {
      orders.putAll(temOrder);
      temOrder.clear();
    }

  }
  
  /**
   * The OrderManager will generate pick by looking at the trail number, see if there are 4
   * orders left to be processed, if there are, then this will return a hashmap, using trail
   * as a key and Object is another HashMap, with the order that orders come in as key and 
   * Order as object.
   */
  public HashMap<Integer, Order> generatePick() {
    HashMap<Integer, Order> order = new HashMap<Integer, Order>();
    if (this.orders.containsKey(trail)) {
      for (int i = 0; i < 4; i++) {
        order.put(4 - i, this.orders.get(trail - i));
        this.orders.get(trail - i).setStatus("picked");
      }
      this.hasnext=trail;
    } else {
      this.hasnext = 0;
    }
    return order;
  }
  
  public int generateNext() {
    return this.hasnext;
  }

  public Map<Integer, Order> getOrders() {
    return this.orders;
  }

}
