package phase1;

public class PickerInfo {
	boolean status; // working or not
	int numberskuPicked; // 1-8 facia which one it is working on 0 means it is not working
	
	public PickerInfo(boolean status, int numberskuPicked) {
		this.status = status;
		this.numberskuPicked = numberskuPicked;
	}
}
