package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
	//Rule 3 : Initialization By Constructor
		WebDriver driver;
		public OrganizationInfoPage(WebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(className="dvHeaderText")
		private WebElement headerMsg;
		
		@FindBy(id="dtlview_Organization Name")
		private WebElement actOrgName;
		
		
		public WebElement getActOrgName() {
			return actOrgName;
		}


		public WebElement getHeaderMsg() {
			return headerMsg;
		}
		
		
		
		

}
