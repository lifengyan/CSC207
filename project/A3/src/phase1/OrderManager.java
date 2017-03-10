package phase1;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OrderManager {
	private Map<Integer, Order> orders;
	private static int trail = 4;
	
	/**
     * Creates a new empty OrderManager.
     */
	public OrderManagement() {
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
    
	public HashMap<Integer, Order> generatePick() {
		 pick = new HashMap<Integer, Order>();
		 if (this.orders.containsKey(trail)) {
			 i = 0;
			 if 
		 }
		
	}

}
