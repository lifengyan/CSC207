package phase1;

public class Order {
	private String colour;
	private String model;
	/** The first item is the front SKU and the second is the back SKU*/
	private int[] SKU;
	public String status = "ordered";
	
	public Order(String colour, String model, int[] SKU) {
		this.colour = colour;
		this.model = model;
		this.SKU = SKU;
		
	}
	
	public void setStatus(String status) {
		this.status = status;
		
	}
	
	
	public boolean equals(Order other) {
		return (other.colour == this.colour && other.model== this.model 
	           && other.SKU == this.SKU);
		
	}
	

}
