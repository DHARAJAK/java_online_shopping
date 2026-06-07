package org.products;

public class RegisteredProducts {

	public RegisteredProducts(String productName, String productDescription, Float productPrice, String categoryId,
			String productImageUrl) {
		this.categoryId = categoryId;
		this.productDescription = productDescription;
		this.productImageUrl = productImageUrl;
		this.productName = productName;
		this.productPrice = productPrice;
	}

	public RegisteredProducts() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "RegisteredProducts [categoryId=" + categoryId + ", productDescription=" + productDescription
				+ ", productId=" + productId + ", productImageUrl=" + productImageUrl + ", productName=" + productName
				+ ", productPrice=" + productPrice + "]";
	}

	String categoryId;
	String productDescription;
	String productId;
	String productImageUrl;
	String productName;
	Float productPrice;

//	public RegisteredProducts(String categoryId, String productDescription, Integer productId, String productImageUrl,
//			String productName, String productPrice) {
//		this.categoryId = categoryId;
//		this.productDescription = productDescription;
//		this.productId = productId;
//		this.productImageUrl = productImageUrl;
//		this.productName = productName;
//		this.productPrice = productPrice;
//	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}

}
