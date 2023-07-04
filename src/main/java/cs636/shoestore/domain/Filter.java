package cs636.shoestore.domain;


public class Filter {
	private String brand;
	private String tag;
	private String minPrice;
	private String maxPrice;
	private String colorCode;
	
	public Filter(String brand, String tag, String minPrice, String maxPrice, String colorCode) {
		this.brand = brand;
		this.tag = tag;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.colorCode = colorCode;
		
	}

	public String getBrand() {
		return brand;
	}

	public String getTag() {
		return tag;
	}

	public String getMinPrice() {
		return minPrice;
	}

	public String getColorCode() {
		return colorCode;
	}

	public String getMaxPrice() {
		return maxPrice;
	}
}
