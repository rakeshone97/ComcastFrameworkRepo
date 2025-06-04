package com.comcast.crm.ObjectRepositoryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.WebdriverUtility.WebDriverUtility;
/**
 * @author Rakesh
 * Contains Login Page Elements and Business Library like Login()
 */
public class LoginPage extends WebDriverUtility{
	//Rule 3 : Initialization By Constructor
		WebDriver driver;
	public LoginPage(WebDriver driver)
	{	this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		
		//Rule 1 :  Create a separate java class
		//Rule 2 : Object Creation
	@FindBy(name="user_name")
	private WebElement usernameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	
	@FindBy(id ="submitButton")
	private WebElement loginBtn;
				//Rule 3 : Object Initialization--- In the Test Script
	
	//Rule 4: Object Encapsulation
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
	//Rule 5:Provide Action   [Business code/method specific to the App]
	/**
	 * Login to apllication BASED on User name,url,password
	 * @param url
	 * @param username
	 * @param password
	 */
	public void loginToApp(String url,String username,String password)
	{	
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
	
}
