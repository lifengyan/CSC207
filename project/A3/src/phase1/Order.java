package phase1;

public class Order {
  private String colour;
  private String model;
  /**
   * The first item is the front SKU and the second is the back SKU.
   */
  private int frontsku;
  private int backsku;
  private static int orderCount = 0;
  public String status = "ordered";
  
  /**
   * Create a order by giving color and model, the system will look up the SKU.
   */
  public Order(String colour, String model, Translate tr) {
    this.colour = colour;
    this.model = model;
    this.frontsku = tr.translate(colour, model).get(0);
    this.backsku = tr.translate(colour, model).get(1);
    orderCount++;

  }

  public void setStatus(String status) {
    this.status = status;

  }

  public int getOrderCount() {
    return orderCount;
  }

  public int getFront() {
    return this.frontsku;
  }

  public int getBack() {
    return this.backsku;
  }


}
