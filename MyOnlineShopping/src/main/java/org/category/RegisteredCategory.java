package org.category;

public class RegisteredCategory {
	String categoryDesc;
	Integer categoryId;
	String categoryImgUrl;
	String categoryName;
	
	

	public RegisteredCategory(String categoryDesc,  String categoryImgUrl, String categoryName) {
		super();
		this.categoryDesc = categoryDesc;
		this.categoryImgUrl = categoryImgUrl;
		this.categoryName = categoryName;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public RegisteredCategory() {
		super();
	}

	public String getCategoryImgUrl() {
		return categoryImgUrl;
	}

	public void setCategoryImgUrl(String categoryImgUrl) {
		this.categoryImgUrl = categoryImgUrl;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
