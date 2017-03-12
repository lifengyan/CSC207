package phase1;

import java.util.ArrayList;

public class Sequencer {
	private String name;
	private String status = "ready";
	
	public Sequencer(String name, SequencerManager sqs) {
		this.setName(name);
		sqs.add(this);
		
	}
	
	public ArrayList<ArrayList<Order>> sequence(int pickid, ArrayList<Integer> aL) {
		
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
