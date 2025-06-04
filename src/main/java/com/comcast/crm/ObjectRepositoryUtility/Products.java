package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Products {
	@FindBy(xpath="//img[@alt=\"Create Product...\"]")
	private WebElement createProductImgBtn;

	public WebElement getCreateProductImgBtn() {
		return createProductImgBtn;
	}
	
	
}
