package phase1;

public class Location {
// contains five field of a location of SKU
	String zone;
	String aisle;
	String rack;
	String level;
	String sku;
	// Constructor
	public Location(String z, String record,String record2, String record3, String record4){
		this.zone = z;
		this.aisle = record;
		this.rack = record2;
		this.level = record3;
		this.sku = record4;
	}
}
