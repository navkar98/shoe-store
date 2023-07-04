package cs636.shoestore.domain;

public class Inventory {
	private long id;
	private long shoeId;
	private long quantity;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getShoeId() {
		return shoeId;
	}
	public void setShoeId(long shoeId) {
		this.shoeId = shoeId;
	}
}
