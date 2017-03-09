package phase1;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class OrderManagement {
	private Map<int, Order> orders;
	
	/**
     * Creates a new empty OrderManager.
     */
	public OrderManagement() {
		 orders = new HashMap<int, Order>();
		
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
    
	

}
