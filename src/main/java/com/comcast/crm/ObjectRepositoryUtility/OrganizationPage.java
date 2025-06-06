package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
		
		//Rule 3 : Initialization By Constructor
			WebDriver driver;
		public OrganizationPage(WebDriver driver)
		{	this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	
		@FindBy(xpath="//img[@alt=\"Create Organization...\"]")
		private WebElement createNewOrgBtn;
		
		@FindBy(name="search_text")
		private WebElement searchEdt;
		
		@FindBy(name="search_field")
		private WebElement searchDD;
		
		@FindBy(name="submit")
		private WebElement searchBtn;
		
				
		public WebElement getSearchBtn() {
			return searchBtn;
		}

		public WebElement getSearchEdt() {
			return searchEdt;
		}

		public WebElement getSearchDD() {
			return searchDD;
		}

		public WebElement getCreateNewOrgBtn() {
			return createNewOrgBtn;
		}
		
		
}
