package phase1;

public class Order {
	private String colour;
	private String model;
	/** The first item is the front SKU and the second is the back SKU*/
	private int frontSKU;
	private int backSKU;
	private static int orderCount = 0;
	public String status = "ordered";
	
	public Order(String colour, String model, int front, int back) {
		this.colour = colour;
		this.model = model;
		this.frontSKU = front;
		this.backSKU = back;
		orderCount++;
		
	}
	
	public void setStatus(String status) {
		this.status = status;
		
	}
	
	public int getOrderCount() {
		return orderCount;
	}
	
	public boolean equals(Order other) {
		return (other.colour == this.colour && other.model== this.model 
	           && other.frontSKU == this.frontSKU && other.backSKU == this.backSKU);
		
	}
	

}
