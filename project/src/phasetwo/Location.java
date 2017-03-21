package phasetwo;

public class Location {
  // contains five field of a location of SKU
  String zone;
  String aisle;
  String rack;
  String level;
  String sku;

  // Constructor
  /**
   * Construct the location.
   * @param zn zone
   * @param record aisle
   * @param record2 rack
   * @param record3 level
   * @param record4 sku
   */
  public Location(String zn, String record, String record2, String record3, String record4) {
    this.zone = zn;
    this.aisle = record;
    this.rack = record2;
    this.level = record3;
    this.sku = record4;
  }

  // add a toString method
  public String toString() {
    return "Zone: " + zone + ", Aisle " + aisle + ", Rack " + rack + ", Level " + level + ", SKU# "
        + sku;
  }

  // getter function for sku
  public String getSKU(){
	  return this.sku;
  }

}
