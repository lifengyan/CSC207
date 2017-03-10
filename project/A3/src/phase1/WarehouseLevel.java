package phase1;
//this class will be store Fasscia.
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


public class WarehouseLevel implements Observer   {
	private ArrayList <Fascia> fasciaList = new ArrayList();
	
	public WarehouseLevel(){
		
	}
	
	public void add (Fascia newFascia){
		fasciaList.add(newFascia);
	}
	
	public Fascia remove(){
		Fascia retFascia = fasciaList.get(-1);
		fasciaList.remove(-1);
		this.update(arg0, arg1);
		return retFascia;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
