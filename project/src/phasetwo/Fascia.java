package phasetwo;

public class Fascia {

  private String sku;
  
/***
 * constuctor if has a sku
 */
  public Fascia(String sku) {
    this.sku = sku;
  }
/***
 * constuctor for defult sku
 */
  public Fascia() {  }
  /***
   * return the sku this is a small change
   */
  public String getsku() {

    return this.sku;
  }
}
