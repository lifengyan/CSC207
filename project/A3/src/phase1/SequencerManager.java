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
	
	
	

}
