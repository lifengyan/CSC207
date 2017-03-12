package phase1;
import java.util.ArrayList;

public class SequencerManager {
	private ArrayList<Sequencer> sqs;
	
	public SequencerManager() {
		sqs = new ArrayList<Sequencer>();
		
	}
	
	public void add(Sequencer sq) {
		this.sqs.add(sq);
	}
	
	public Sequencer getSequencer() {
		for (int i = 0; i < this.sqs.size(); i++) {
			if (this.sqs.get(i).getStatus() == "ready") {
				return this.sqs.get(i);
			}
			
		}
	}
	
	
	

}
