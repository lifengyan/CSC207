package phasetwo;

public class Fascia {
  private int sku=1;
/***
 * constuctor if has a sku
 */
  public Fascia(int sku) {
    this.sku = sku;
  }
/***
 * constuctor for defult sku
 */
  public Fascia() {  }
  /***
   * return the sku
   */
  public int getsku() {
    return this.sku;
  }
}
