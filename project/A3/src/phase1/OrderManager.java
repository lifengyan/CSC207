package phase1;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.Thread;

public class OrderManager {
	private Map<Integer, Order> orders;
	private static int trail = 4;
	
	/**
     * Creates a new empty OrderManager.
     */
	public OrderManager() {
		 orders = new HashMap<Integer, Order>();
		
	}
	
	public void readFromCSVFile(String filePath) throws FileNotFoundException {
      
        Scanner scanner = new Scanner(new FileInputStream(filePath));
        String[] record;
        Order order;

        while(scanner.hasNextLine()) {
            record = scanner.nextLine().split(",");
            order = new Order(record[0], record[1], Integer.valueOf(record[2]), 
            		Integer.valueOf(record[3]));
            orders.put(order.getOrderCount(), order);
        }
        scanner.close();
	}
  

    public void add(Order order) {
    	orders.put(order.getOrderCount(), order);
    }
    
	public Map<Integer, Order> generatePick() throws InterruptedException {
		Map<Integer, Order> pick = new HashMap<Integer, Order>();
		 if (this.orders.containsKey(trail)) {
			 for (int i = 0; i < 4; i++) {
				pick.put(4-i, this.orders.get(trail-i));
				this.orders.get(trail-i).setStatus("picked");
			 }
		
			 trail += 4; 
	
		 } else {
			 Thread.sleep(5000);
			 generatePick();
		 }
		return pick;
		
		
	}

}
