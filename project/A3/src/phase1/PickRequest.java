package phase1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PickRequest{
		private ArrayList<Integer> fasciaList = new ArrayList<Integer>();
		private ArrayList<String> locationInfo;  // make an another class like PickerInfo with 5 fields: string, and 4 number
		private String picker;
		private String zone;
		private Integer aisle;
		private Integer rack;
		private Integer level;
		private Integer SKU;
		//http://www.sergiy.ca/how-to-iterate-over-a-map-in-java/   HasMap read tutorial
		
		//Constructor with a Hash map input argument
		
		public PickRequest(Map<Integer, Order> newRequest){
			for (Integer key: newRequest.keySet()){
					Order order = newRequest.get(key);
					this.fasciaList.add(order.getFront()); //need to add these two getter method in Order Class
					this.fasciaList.add(order.getBack());
			}
			
			this.locationInfo = optimize(fasciaList);

		}
		
				//Method getFasciaList： returns the private Integer array list Contains 8 SKU# for one pick request	
		public static ArrayList<String> optimize(ArrayList<Integer> fasciaList){
			// using generic software to get ordered locations
			ArrayList<String> location = new ArrayList<String>();
			return location;
			
		}

		//method to send location of each fascia to the picker
		public void sendLocation(){
			System.out.println(picker + "next go to zone: " + zone + ", aisle: "+ aisle +", rack: " + rack + ", level: "+ level);
		}



}
