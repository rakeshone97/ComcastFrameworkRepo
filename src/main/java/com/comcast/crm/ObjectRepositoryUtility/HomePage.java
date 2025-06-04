package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	//Rule 3 : Initialization By Constructor
			WebDriver driver;
		public HomePage(WebDriver driver)
		{	this.driver=driver;
			PageFactory.initElements(driver, this);
		}
	
	@FindBy(linkText="Organizations")
	private WebElement orgLnk;
	
	@FindBy(linkText="Products")
	private WebElement productLnk;
	
	
	@FindBy(linkText="Contacts")
	private WebElement contactLnk;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignLnk;
	
	@FindBy(linkText="More")
	private WebElement moreLnk;
	
	@FindBy(xpath="//img[@src=\"themes/softed/images/user.PNG\"]")
	private WebElement adminImg;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOut;
	
	
	public WebElement getProductLnk() {
		return productLnk;
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getOrgLnk() {
		return orgLnk;
	}

	public WebElement getContactLnk() {
		return contactLnk;
	}

	public WebElement getCampaignLnk() {
		return campaignLnk;
	}
	
	public void navigateToCampaignPage()
	{
		Actions  act= new Actions(driver);
		act.moveToElement(moreLnk).perform();
		campaignLnk.click();
	}
	
	public void logout()
	{
		Actions  act= new Actions(driver);
		act.moveToElement(adminImg).perform();
		signOut.click();
	}
	
	

	

}
