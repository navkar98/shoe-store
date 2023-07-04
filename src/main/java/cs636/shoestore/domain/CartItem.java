package cs636.shoestore.domain;


public class CartItem {

	private long shoeId;
	private int quantity;
	public CartItem() {}
	
	public CartItem (long shoeId, int quantity) {
		this.shoeId = shoeId;
		this.quantity = quantity;
	}
	
	public long getShoeId() {
		return shoeId;
	}

	public void setShoeId(long id) {
		this.shoeId = id;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
