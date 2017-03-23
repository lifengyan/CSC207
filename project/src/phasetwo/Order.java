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

  public void setfrontStatus(String status) {
    this.frontStatus = status;

  }

  public String getfrontStatus() {
    return this.frontStatus;

  }

  public String getbackStatus() {
    return this.backStatus;

  }

  public void setbackStatus(String status) {
    this.backStatus = status;
  }

  public void resetStatus() {
    this.frontStatus = "ordered";
    this.backStatus = "ordered";
  }

  public int getOrderid() {
    return orderid;
  }

  public String getFront() {
    return this.frontsku;
  }

  public String getBack() {
    return this.backsku;
  }

  public String getColour() {
    return this.colour;
  }

  public String getModel() {
    return this.model;
  }

  public boolean equals(Order order) {
    return (this.getFront().equals(order.getFront()) && this.getBack().equals(order.getBack()));
  }


}
