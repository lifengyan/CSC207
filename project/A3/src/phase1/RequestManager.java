package phase1;

import java.util.HashMap;
import java.util.Map;

public class RequestManager {
	public HashMap<Integer, PickRequest> pickRequests;
	public HashMap<String, PickerInfo> pickerStatus;// String is name of picker, boolean is whether the picker is currently in use
	public String[] pickerNames = {"John", "Jamie", "David", "Tony"};
	public int[] fasciaList = {1,2,3,4,5,6,7,8};
	public int numRequest = 0;
	
	//Constructor
	public RequestManager(){
		pickRequests = new HashMap<Integer, PickRequest>();
		pickerStatus = new HashMap<String, PickerInfo>();
		for (int i = 0; i < pickerNames.length; i++) {
			pickerStatus.put(pickerNames[i], new PickerInfo(false, 0));
		}
		
	}
	//this will be the method called by order.java to generate an new request
	public void createRequest(Map<Integer, Order> newRequest){
		numRequest++;
		PickRequest request = new PickRequest(newRequest);
		pickRequests.put(numRequest, request);
		
		// loop through pickerstatus, and check if there is a picker that is not in use
		// if a picker is not in use, assign this picker to this request.
		
		while (pickerAvailable().equals("")) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String pickerName = pickerAvailable();
			
			
		}
		
		
		System.out.println("new pick request created");
		
	}
	// check if there is a picker available, if so, return the name, other wise return empty string
	public String pickerAvailable() {
		// here determine if there is a picker available
		return "";
	}

	
	public void getPickerStatus(){
		
	}
	public void changePickerStatus(){
		
	}
	
	
}
