package com.comcast.crm.OrgTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

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

import com.comcast.crm.ObjectRepositoryUtility.CreatingNewOrganizationPage;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationInfoPage;
import com.comcast.crm.ObjectRepositoryUtility.OrganizationPage;
import com.comcast.crm.generic.WebdriverUtility.WebDriverUtility;

public class DeleteOrgTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		//Step 1: Get the java representation object of the physical file
		FileInputStream fis= new FileInputStream("./configAppData/commondata.properties");
		
		// Step 2 : using Properties class, load all the keys
		Properties prop = new Properties();
		prop.load(fis);
		
		//Step 3 : get the value based on key
		String BROWSER=prop.getProperty("browser");
		String URL=prop.getProperty("url");
		String USERNAME=prop.getProperty("username");
		String PASSWORD=prop.getProperty("password");
		FileInputStream fis1 = new FileInputStream("./testScriptData/Book1.xlsx");
		Workbook wb=WorkbookFactory.create(fis1);
		Sheet sh=wb.getSheet("Org");
		Row row=sh.getRow(10);
		String orgName=row.getCell(2).getStringCellValue()+(int)(Math.random()*10000);
		wb.close();
		
		
		
		WebDriver driver=null;
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("Firefox"))
		{
			driver= new FirefoxDriver();
		}
		else if (BROWSER.equalsIgnoreCase("Edge"))
		{
			driver=new EdgeDriver();
		}
		WebDriverUtility wLib=new WebDriverUtility();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		
		/// this initialization have to do in every test script
		///so now Initialize By using Constructor
		///at the place of this--{LoginPage lp= PageFactory.initElements(driver, LoginPage.class);}
		LoginPage lp=new LoginPage(driver);
		
//		lp.getUsernameEdt().sendKeys("admin");
//		lp.getPasswordEdt().sendKeys("admin");
//		lp.getLoginBtn().click();
		lp.loginToApp(URL,USERNAME, PASSWORD);
		
		//Navigate to Organization
		HomePage hp=new HomePage(driver);
		hp.getOrgLnk().click();
		
		//click on Create Organization Button
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateNewOrgBtn().click();
		
		//enter all the details and click on save
		CreatingNewOrganizationPage cnop= new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
				
		//verify Header message Expected Result
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String headermsg=oip.getHeaderMsg().getText();
		if(headermsg.contains(orgName)) {
		System.out.println(orgName+" is Created--PASS");
		}else {
			System.out.println(orgName+" is Created--FAIL");
		} 
		//Go back to Organization page
		hp.getOrgLnk().click();
		
		//Search for Organization Page
		op.getSearchEdt().sendKeys(orgName);
		wLib.select(op.getSearchDD(), "Organization Name");
		op.getSearchBtn().click();
		
		//In Dynamic Web Table select & Delete Org
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
		wLib.switchToAlertAndAccept(driver);
		Thread.sleep(2000);		
		//logout
		hp.logout();
		driver.quit();
	}


}
