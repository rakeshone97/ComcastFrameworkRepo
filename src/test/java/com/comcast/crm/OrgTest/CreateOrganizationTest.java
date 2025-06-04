package com.comcast.crm.OrgTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.BaseTest.BaseClass;
import com.comcast.crm.ListenerUtility.ListImpleClass;
import com.comcast.crm.ObjectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationPage;
import com.comcast.crm.generic.WebdriverUtility.UtilityClassObject;
/**
 * @author Rakesh
 */
//@Listeners(com.comcast.crm.ListenerUtility.ListImpleClass.class)
public class CreateOrganizationTest extends BaseClass {
	@Test
	public void createOrgTest() throws EncryptedDocumentException, IOException {

		/// this initialization have to do in every test script
		/// so now Initialize By using Constructor
		/// at the place of this--{LoginPage lp= PageFactory.initElements(driver,
		/// LoginPage.class);}
	//	ListImpleClass.test.log(Status.INFO, "Read data From Excel");
		System.out.println(UtilityClassObject.getTest());
	UtilityClassObject.getTest().log(Status.INFO, "Read data From Excel");
		
		/* Read TestScript data from Excel File*/
		String orgName = eLib.getDataFromExcel("Org", 4, 2) + jLib.getRandomNumber();

		// Navigate to Organization
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Organization");

		HomePage hp = new HomePage(driver);
		hp.getOrgLnk().click();

		// click on Create Organization Button
		UtilityClassObject.getTest().log(Status.INFO, "Create Organization Button");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// enter all the details and click on save
		UtilityClassObject.getTest().log(Status.INFO, "Create Organization");

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		UtilityClassObject.getTest().log(Status.INFO, orgName+" is Created");

		// verify Header message Expected Result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String headermsg = oip.getHeaderMsg().getText();
		///Hard Assert
		boolean status= headermsg.contains(orgName);
		Assert.assertEquals(status, true);
		/*
		 * if (headermsg.contains(orgName)) { System.out.println(orgName +
		 * " is Created--PASS"); } else { System.out.println(orgName +
		 * " is Created--FAIL"); }
		 */
		
		///Soft Assert
		SoftAssert soft= new SoftAssert();
		String actOrgName=oip.getActOrgName().getText();
		soft.assertNotEquals(actOrgName, orgName);
	}

	@Test(groups="RegressionTest")
	public void createOrgWithPhone() throws EncryptedDocumentException, IOException {
		String orgName = eLib.getDataFromExcel("Org", 7, 2)+jLib.getRandomNumber();
		String phone = eLib.getDataFromExcel("Org", 7, 3);

		// Navigate to Organization
		HomePage hp = new HomePage(driver);
		hp.getOrgLnk().click();

		// click on Create Organization Button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// enter all the details and click on save
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrgWithPhone(orgName, phone);

		// Verify Phone Number info Expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actPhone = oip.getHeaderMsg().getText();
		if (actPhone.equals(phone)) {
			System.out.println(actPhone + " is Verified--PASS");
		} else {
			System.out.println(actPhone + " is Verified--FAIL");
		}

	}

	@Test(groups="RegressionTest")
	public void createOrgWithIndustry() throws EncryptedDocumentException, IOException {
		
		String orgName = eLib.getDataFromExcel("Org", 4, 2)+jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("Org", 4, 3);

		// Navigate to Organization
		HomePage hp = new HomePage(driver);
		hp.getOrgLnk().click();

		// click on Create Organization Button
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();

		// enter all the details and click on save
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, industry);

		// Verify Phone Number info Expected result
		String actPhone = cnop.getOrgIndustry().getText();
		if (actPhone.equals(industry)) {
			System.out.println(actPhone + " is Verified--PASS");
		} else {
			System.out.println(actPhone + " is Verified--FAIL");
		}
	}

}
