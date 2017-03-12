package phase1;
import java.util.HashMap;
import java.util.Map;

public class OrderManager {
	private Map<Integer, Order> orders;
	private Map<Integer, Order> temOrder;
	private static int trail = 4;
	
	/**
     * Creates a new empty OrderManager.
     */
	public OrderManager() {
		 orders = new HashMap<Integer, Order>();
		 temOrder = new HashMap<Integer, Order>();
		
	}
	
	public void addOrder(String colour, String model, Translate tr) {
		//add translate 
		Order order = new Order(colour, model, tr);
		temOrder.put(order.getOrderCount(), order);
		if (temOrder.size()==4) {
			orders.putAll(temOrder);
			temOrder.clear();
		}
		
	}
	
    
	public HashMap<Integer, HashMap<Integer, Order> > generatePick() throws InterruptedException {
		HashMap<Integer, HashMap<Integer, Order>> pick = new HashMap<Integer, HashMap<Integer, Order>>();
		HashMap<Integer, Order> order = new HashMap<Integer, Order>();
		 if (this.orders.containsKey(trail)) {
			 for (int i = 0; i < 4; i++) {
				order.put(4-i, this.orders.get(trail-i));
				this.orders.get(trail-i).setStatus("picked");
			 }
			 pick.put(trail, order);
			 trail += 4; 
	
		 }
		 else {
			 pick.put(0, order);
		 }
		return pick;
		
		
	}
	
	public Map<Integer, Order> getOrders() {
		return this.orders;
	}

}
