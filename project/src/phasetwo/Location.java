package phasetwo;

/**
 * This class is used to store fascia's location information as well as their SKU number.
 * 
 * @author TongZhu
 * 
 *
 */
public class Location {
  // contains a fascia's location informations and its SKU number
  String zone;
  String aisle;
  String rack;
  String level;
  String sku;


  /**
   * Constructor for the location class that assign values to five String variables.
   * 
   * @param zn zone information
   * @param ai aisle information
   * @param ra rack information
   * @param le level information
   * @param sku SKU number
   */
  public Location(String zn, String ai, String ra, String le, String sku) {
    this.zone = zn;
    this.aisle = ai;
    this.rack = ra;
    this.level = le;
    this.sku = sku;
  }

  /**
   * Returns the location information of a SKU in as string type.
   */
  public String toString() {
    return "Zone: " + zone + ", Aisle " + aisle + ", Rack " + rack + ", Level " + level + ", SKU# "
        + sku;
  }

  /**
   * This is a getter function that returns SKU number as a string type.
   * 
   * @return sku
   */
  public String getSku() {
    return this.sku;
  }

}
