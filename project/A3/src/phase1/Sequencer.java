package phase1;

import java.util.ArrayList;
import java.util.HashMap;

public class Sequencer extends Worker {
	private String name;
	private String status = "ready";
	
	public Sequencer(String name) {
		super(name);
		
	}
	
	public ArrayList<ArrayList<Integer>> sequence(int pickid, ArrayList<Integer> pickedList, OrderManager om) {
		ArrayList<ArrayList<Integer>> whole = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> front = new ArrayList<Integer>();
		ArrayList<Integer> back = new ArrayList<Integer>();
		int i = 3;
		while (i > -1) {
				int a = om.getOrders().get(pickid-i).getFront();
				int b = om.getOrders().get(pickid-i).getBack();
				if (pickedList.contains(a) && pickedList.contains(b)) {
					front.add(a);
					back.add(b);
					i--;
				} else {
					System.out.println("Picked wrong fascia");
					whole.add(pickedList);
					return whole;
				}
		}
		whole.add(front);
		whole.add(back);
		return whole;
		
			
		
	}	
}
