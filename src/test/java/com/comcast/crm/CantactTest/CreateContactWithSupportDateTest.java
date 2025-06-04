package com.comcast.crm.CantactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

import com.comcast.crm.generic.WebdriverUtility.JavaUtility;

public class CreateContactWithSupportDateTest {

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
		Sheet sh=wb.getSheet("Contact");
		Row row=sh.getRow(4);
		JavaUtility jLib=new JavaUtility();
		String lastName=row.getCell(2).getStringCellValue()+jLib.getRandomNumber();
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
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		
		driver.findElement(By.xpath("//img[@alt=\"Create Contact...\"]")).click();
		
		String startdate= jLib.getSystemDateYYYYDDMM();
		String enddate=jLib.getRequiredDateYYYYDDMM(30);
		
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startdate);
		
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(enddate);
		
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		//Thread.sleep(2000);
		//verify Header message Expected Result
		String headermsg=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		if(headermsg.contains(lastName)) {
		System.out.println(lastName+" is Created--PASS");
		}
		else {
			System.out.println(lastName+" is Created--FAIL");

		}
		//Verify the start Date
		String actstart=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actstart.equals(startdate)) {
			System.out.println(startdate+" Information is verified--PASS");
			}
			else {
				System.out.println(startdate+" Information is verified--FAIL");
			}
		//Verify the  End Date
		String actenddate=driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actenddate.equals(enddate)) {
			System.out.println(actenddate+" Information is verified--PASS");
			}
			else {
				System.out.println(actenddate+" Information is verified--FAIL");
			}
		WebElement logout= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		Actions action=new Actions(driver);
		action.moveToElement(logout).build().perform();
		
		//Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
	 

	}

}
