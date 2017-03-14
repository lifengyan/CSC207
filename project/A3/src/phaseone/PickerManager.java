package phaseone;

import java.util.ArrayList;

public class PickerManager {
		private ArrayList<Picker> pickerList = new ArrayList<Picker>();
		private ArrayList<Picker> freePicker = new ArrayList<Picker>();
		
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
		// After a picker finished picking for the current request, remove this picker from the 
				public void deletFreePicker(Picker picker){
					this.freePicker.remove(picker);
				}
		
		
		
		//according to the input string to return this picker
		public Picker getPicker(String pickerName){
			for(int i = 0; i < pickerList.size();i++){
				if (pickerList.get(i).getName().equals( pickerName)){
					return pickerList.get(i);
				}
			
			}
			System.out.println("Given picker does not exist");
			return null; 
		}
		
		
		// store new picker in pickerList
		public void storePicker(String pickerName){
			Picker newPicker = new Picker(pickerName);
			pickerList.add(newPicker);
		}
// After a picker finished picking for the current request, remove this picker from the 
		public void deletPicker(Picker picker){
			this.pickerList.remove(picker);
		}
}
