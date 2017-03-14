package phaseone;

public class Location {
// contains five field of a location of SKU
	String zone;
	String aisle;
	String rack;
	String level;
	String sku;
	// Constructor
	/***
	 * 
	 * @param z
	 * @param record
	 * @param record2
	 * @param record3
	 * @param record4
	 */
	public Location(String z, String record,String record2, String record3, String record4){
		this.zone = z;
		this.aisle = record;
		this.rack = record2;
		this.level = record3;
		this.sku = record4;
	}
	// add a toString method
	public String toString(){
		return "Zone: "+zone + ", Aisle " + aisle + ", Rack " + rack+ ", Level "+ level+", SKU# "+ sku;
	}
	
	
	
	
	
	
}
