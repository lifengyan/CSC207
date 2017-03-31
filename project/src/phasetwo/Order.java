package phasetwo;

public class Order {
  private String colour;
  private String model;
  private String frontsku;
  private String backsku;
  private static int orderCount = 0;
  private String frontStatus = "ordered";
  private String backStatus = "ordered";
  private int orderid;

  /**
   * Create a order by giving color and model, the system will look up the SKU.
   */
  public Order(String colour, String model, Translate tr) {
    this.colour = colour;
    this.model = model;
    this.frontsku = tr.translate(colour, model).get(0);
    this.backsku = tr.translate(colour, model).get(1);
    orderCount++;
    orderid = orderCount;

  }

  /**
   * set the pallet front part status.
   */
  public void setfrontStatus(String status) {
    this.frontStatus = status;

  }

  /**
   * get the pallet front part status.
   */
  public String getfrontStatus() {
    return this.frontStatus;

  }

  /**
   * get the pallet back part status.
   */
  public String getbackStatus() {
    return this.backStatus;

  }

  /**
   * set the pallet back status.
   */
  public void setbackStatus(String status) {
    this.backStatus = status;
  }

  /**
   * Reset order status.
   */
  public void resetStatus() {
    this.frontStatus = "ordered";
    this.backStatus = "ordered";
  }

  /**
   * return order id.
   */
  public int getOrderid() {
    return orderid;
  }

  /**
   * return order`s front sku.
   */
  public String getFront() {
    return this.frontsku;
  }

  /**
   * return order`s back sku.
   */
  public String getBack() {
    return this.backsku;
  }

  /**
   * return the order color.
   */
  public String getColour() {
    return this.colour;
  }

  /**
   * return the order Model.
   */
  public String getModel() {
    return this.model;
  }

  /**
   * check if two order is euqal, compare colour and model.
   */
  public boolean equals(Order order) {
    return (this.getFront().equals(order.getFront()) && this.getBack().equals(order.getBack()));
  }


}
