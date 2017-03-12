package phase1;

import java.util.ArrayList;

public class PickerManager {
		ArrayList<Picker> pickerList = new ArrayList<Picker>();
		ArrayList<Picker> freePicker = new ArrayList<Picker>();
		
		// Method addPicker 
		public void addPicker(Picker pickerName){
			this.pickerList.add(pickerName);
			
		}
		
		// Method to return a list of pickers that are ready, the first one in the list should be the first one called next time.
		public ArrayList<Picker> getFreePicker(){
			return this.freePicker;
		}
		//
		public void addFreePicker(Picker name){
			this.freePicker.add(name);
		}
		
		// store new picker in pickerList
		public void storePicker(String pickerName){
			Picker newPicker = new Picker(pickerName);
			pickerList.add(newPicker);
		}
}
