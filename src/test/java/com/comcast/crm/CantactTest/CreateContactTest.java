package com.comcast.crm.CantactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.comcast.crm.BaseTest.BaseClass;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;

public class CreateContactTest extends BaseClass {

	@Test
	public void createContactTest() throws EncryptedDocumentException, IOException {
		///Now File is in the Framework so write the path for this,
		///Here (.) -->means navigate/Go to parent directories/project and (/)--> go and find
		///the folder that given. 
		//FileInputStream fis= new FileInputStream("./configAppData/commondata.properties");
		
		//Read testScript Data from Excel file
		String lastName=eLib.getDataFromExcel("Contact", 1, 2)+jLib.getRandomNumber();
		
		//Navigate to contact Module
		HomePage hp= new HomePage(driver);
		hp.getContactLnk().click();
		
		//click on create contact button
		
		
		//verify Header message Expected Result
	/*	String headermsg=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		if(headermsg.contains(lastName)) {
		System.out.println(lastName+" is Created--PASS");
		}
		else {
			System.out.println(lastName+" is Created--FAIL");

		}
		//Verify  Last name info Expected result
		String actLastName=driver.findElement(By.id("dtlview_Last Name")).getText();
		Thread.sleep(2000);
		if(actLastName.equals(lastName)) {
			System.out.println(lastName+" Information is verified--PASS");
			}
			else {
				System.out.println(lastName+" Information is verified--FAIL");
			}
		WebElement logout= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		Actions action=new Actions(driver);
		action.moveToElement(logout).build().perform();
		
		//Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();
		*/
		driver.quit();
	 

	}

}
