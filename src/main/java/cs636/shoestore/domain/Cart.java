package cs636.shoestore.domain;

import java.util.List;
import java.util.ArrayList;

public class Cart{

	private long id;
	private String discountId;
	private String finalPrice;
	private String userId;
	private String shoeId;
	
	public Cart(long id, String discountId, String finalPrice, String userId, String shoeId) {
		this.id = id;
		this.discountId = discountId;
		this.finalPrice = finalPrice;
		this.userId = userId;
		this.shoeId = shoeId;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long user_id) {
		this.id = user_id;
	}
	
	public String getDiscountId() {
		return discountId;
	}

	public void setDiscountId(String discountId) {
		this.discountId = discountId;
	}

	public String getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(String finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getShoeId() {
		return shoeId;
	}

	public void setShoeId(String shoeId) {
		this.shoeId = shoeId;
	}
}