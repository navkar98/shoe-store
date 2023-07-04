package cs636.shoestore.domain;


public class Shoe{
	private long id;
	private String name;
	private String description;
	private Double price;
	private long brandId;
	private long tagId;
	private long materialId;
	private long featureId;
	private long shoeTypeId;
	private long sizeId;
	private String colorCode;
	private Double height;
	private Double width;
	private Double length;
	
	public Shoe(long id, String name, String description, Double price, long brandId, long tagId, long materialId, long featureId, long shoeTypeId, long sizeId, String colorCode, Double height, Double width, Double length) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.brandId = brandId;
		this.tagId = tagId;
		this.materialId = materialId;
		this.featureId = featureId;
		this.shoeTypeId = shoeTypeId;
		this.sizeId = sizeId;
		this.colorCode = colorCode;
		this.height = height;
		this.width = width;
		this.length = length;
	}

	public long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public long getBrandId() {
		return brandId;
	}

	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	public long getTagId() {
		return tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	public long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(long materialId) {
		this.materialId = materialId;
	}

	public long getFeatureId() {
		return featureId;
	}

	public void setFeatureId(long featureId) {
		this.featureId = featureId;
	}

	public long getShoeTypeId() {
		return shoeTypeId;
	}

	public void setShoeTypeId(long shoeTypeId) {
		this.shoeTypeId = shoeTypeId;
	}

	public long getSizeId() {
		return sizeId;
	}

	public void setSizeId(long sizeId) {
		this.sizeId = sizeId;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

}