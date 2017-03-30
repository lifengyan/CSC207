package phasetwo;

public class Fascia {
<<<<<<< HEAD
  private String sku;

  public Fascia(String sku) {
    this.sku = sku;
  }

  public Fascia() {

  }

  public String getsku() {
=======
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
>>>>>>> 985472fc3aefb540841089272e6c3304d462d077
    return this.sku;
  }
}
