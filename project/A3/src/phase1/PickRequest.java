package phase1;

import java.util.ArrayList;
import java.util.HashMap;

public class PickRequest{
		private ArrayList<Integer> fasciaList = new ArrayList<Integer>();
		
		//http://www.sergiy.ca/how-to-iterate-over-a-map-in-java/   HasMap read tutorial
		
		//Constructor with a Hash map input argument
		
		public PickRequest(HashMap<Integer, Order> hashMap){
			for (Integer key: hashMap.keySet()){
					Order order = hashMap.get(key);
					this.fasciaList.add(order.getFrontSku); //need to add these two getter method in Order Class
					this.fasciaList.add(order.getBackSku);
			}

		}
		//Method getFasciaList to get the private Integer array list Contains 8 SKU# for one pick request
		public ArrayList<Integer> getFasciaList(){
			return fasciaList;
		}
}
