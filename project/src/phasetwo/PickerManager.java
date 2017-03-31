package phasetwo;

import java.util.ArrayList;

/**
 * PickerManager stores information of pickers who are currently working on a request into
 * pickerList and pickers who are currently free into freePicker list. Once pickers finished their
 * picking request, they will be removed from the pickerList since no further action is needed form
 * them. Once free pickers are assigned with picking requests, they will be removed from freePicker
 * list
 * 
 * @author TongZhu
 *
 */
public class PickerManager {
  private ArrayList<Picker> pickerList = new ArrayList<Picker>(); 
  private ArrayList<Picker> freePicker = new ArrayList<Picker>();

  /**
   * use input string of a picker's name to get the Picker instance associated with this name, if no
   * such picker exist in the list, then create a new Picker instance with give string name and put
   * it in the pickerList.
   * 
   * @param pickerName the name of the picker
   * @return a picker
   */
  public Picker getORaddPicker(String pickerName) {
    for (int i = 0; i < pickerList.size(); i++) {
      if (pickerList.get(i).getName().equals(pickerName)) {
        return pickerList.get(i);
      }
    }
    this.pickerList.add(new Picker(pickerName));
    Picker currentpicket = this.getORaddPicker(pickerName);
    return currentpicket;
  }

  /**
   * getter function that returns an Picker type arrayList of ready/free pickers, the Picker at
   * index 0 should be the first one to receive next picking request.
   * 
   * @return getFreePicker: an array contains all free pickers
   */
  public ArrayList<Picker> getFreePicker() {
    return this.freePicker;
  }

  /**
   * add new ready picker to the free picker list, added to the end of the arrayList.
   * 
   * @param name a new ready picker
   */
  public void addFreePicker(Picker name) {
    this.freePicker.add(name);
  }

  /**
   * remove pickers from freePicker list after assign them with new request.
   * 
   * @param picker the picker that got a request to work on
   */
  public void deletFreePicker(Picker picker) {
    this.freePicker.remove(picker);
  }

  /**
   * remove pickers from the pickerList when they finished a picking request.
   * 
   * @param picker the picker that has finished the picking request
   */
  public void deletPicker(Picker picker) {
    this.pickerList.remove(picker);
  }

  /**
   * return an ArrayList of pickerList This function is only used in the PickerManagerTest file.
   * 
   * @return ArrayList of Picker type
   */
  public ArrayList<Picker> getPickerList() {
    return this.pickerList;
  }
}
