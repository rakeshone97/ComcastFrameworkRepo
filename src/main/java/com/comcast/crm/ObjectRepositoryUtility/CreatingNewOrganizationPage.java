package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {
	
	//Rule 3 : Initialization By Constructor
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement orgSaveBtn;
	
	@FindBy(name="industry")
	private WebElement industrySel;
	
	@FindBy(id="phone")
	private WebElement orgPhoneEdt;
	
	@FindBy(id="dtlview_Industry")
	private WebElement orgIndustry;
	

	public WebElement getOrgIndustry() {
		return orgIndustry;
	}

	public WebElement getOrgPhoneEdt() {
		return orgPhoneEdt;
	}

	public WebElement getOrgNameEdt() {
		return orgNameEdt;
	}

	public WebElement getOrgSaveBtn() {
		return orgSaveBtn;
	}
	
	public void createOrg(String orgName) 
	{
		orgNameEdt.sendKeys(orgName);
		orgSaveBtn.click();
	}
	
	public void createOrgWithPhone(String orgName,String phone) 
	{
		orgNameEdt.sendKeys(orgName);
		orgPhoneEdt.sendKeys(phone);
		orgSaveBtn.click();
	}
	
	public void createOrg(String orgName,String industry) 
	{	
		orgNameEdt.sendKeys(orgName);
		Select sel=new Select(industrySel);
		sel.selectByVisibleText(industry);
		orgSaveBtn.click();
	}
	
	

}
