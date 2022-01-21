package com.shopping.aswini;

public class PojoInsert {
	private String ProductName;
	private String BrandName;
	private String Price;
	private String Person;
	private String Colour1;
	private String Colour2;
	private String Size;
	private String Quantity;
	private String Review;

	public PojoInsert() {
	}

	public PojoInsert(String productName, String brandName, String price, String person, String colour1, 
			String colour2, String size, String quantity, String review) {
		
		this.ProductName = productName;
		this.BrandName = brandName;
		this.Price = price;
		this.Person = person;
		this.Colour1 = colour1;
		this.Colour2 = colour2;
		this.Size = size;
		this.Quantity = quantity;
		this.Review = review;
	}

	@Override
	public String toString() {
		return "Pojoinsert PdtName:" + ProductName + ", BrandName:" + BrandName + ", Price:" + Price + ", Person:" 
				+ Person + ", Colour1:" + Colour1 + ", Colour2:" + Colour2 + ", Size:" + Size + ", Quantity:" 
				+ Quantity + ", Review:" + Review +"";
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String pdtName) {
		this.ProductName= pdtName;
	}

	public String getBrandName() {
		return BrandName;
	}

	public void setBrandName(String brandName) {
		this.BrandName = brandName;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		this.Price = price;
	}

	public String getPerson() {
		return Person;
	}

	public void setPerson(String person) {
		this.Person = person;
	}

	public String getColour1() {
		return Colour1;
	}

	public void setColour1(String colour1) {
		this.Colour1 = colour1;
	}

	public String getColour2() {
		return Colour2;
	}

	public void setColour2(String colour2) {
		this.Colour2 = colour2;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		this.Size = size;
	}

	public String getQuantity() {
		return Quantity;
	}

	public void setQuantity(String quantity) {
		this.Quantity = quantity;
	}

	public String getReview() {
		return Review;
	}

	public void setReview(String review) {
		this.Review = review;
	}

}
