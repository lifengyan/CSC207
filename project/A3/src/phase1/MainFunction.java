package phase1;

import java.io.FileNotFoundException;
import java.util.Map;

public class MainFunction {

	public static String filePath = "";
	
	public static boolean newUnhandledRequest = false; // this variable should be in ordermanager. it tells you whether there is a new request
	// that has not yet been sent to the RequestManager
	
	public static void main(String[] args) {
		OrderManager orderManager = new OrderManager();
		RequestManager requestManager = new RequestManager();
		try {
			orderManager.readFromCSVFile(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		while (true) {
			while (!newUnhandledRequest) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// code reaches here if there is a new request to be created by RequestManager
				Map<Integer, Order> newRequest;
				try {
					newRequest = orderManager.generatePick();
					requestManager.createRequest(newRequest);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			
			
		}

		
	}
	
}
