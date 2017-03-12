package phase1;

public class Order {
	private String colour;
	private String model;
	/** The first item is the front SKU and the second is the back SKU */
	private int frontSKU;
	private int backSKU;
	private static int orderCount = 0;
	public String status = "ordered";

	public Order(String colour, String model, Translate tr) {
		this.colour = colour;
		this.model = model;
		this.frontSKU = tr.translate(colour, model).get(0);
		this.backSKU = tr.translate(colour, model).get(1);
		orderCount++;

	}

	public void setStatus(String status) {
		this.status = status;

	}

	public int getOrderCount() {
		return orderCount;
	}

	public int getFront() {
		return this.frontSKU;
	}

	public int getBack() {
		return this.backSKU;
	}

	public boolean equals(Order other) {
		return (other.colour == this.colour && other.model == this.model && other.frontSKU == this.frontSKU
				&& other.backSKU == this.backSKU);

	}

}
