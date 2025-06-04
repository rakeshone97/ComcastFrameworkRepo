package com.comcast.crm.CantactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

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

public class CreateContactWithOrgTest {
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
		Row row=sh.getRow(7);
		String orgName=row.getCell(3).getStringCellValue()+(int)(Math.random()*10000);
		String contactLN=row.getCell(2).getStringCellValue()+(int)(Math.random()*10000);
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
		driver.findElement(By.linkText("Organizations")).click();
		
		driver.findElement(By.xpath("//img[@alt=\"Create Organization...\"]")).click();
		
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		//Thread.sleep(2000);
		//verify Header message Expected Result
		String headermsg=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]")).getText();
		if(headermsg.contains(orgName)) {
		System.out.println(orgName+" is Created--PASS");
		}
		else {
			System.out.println(orgName+" is Created--FAIL");

		}
		//Verify header orgName info Expected result
		String actOrgName=driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actOrgName.trim().equals(orgName)) {
			System.out.println(orgName+" is Verified--PASS");
			}
			else {
				System.out.println(orgName+" is Verified--FAIL");
			}
		/// WE HAVE TO WRITE AND RUN THE PRE CONDITION ALSO FOR GOOD PRACTICE,BECAUSE THERE MIGHT NOT BE PRESENT THE ALREADY CREATED ORG
		//Navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		
		driver.findElement(By.xpath("//img[@alt=\"Create Contact...\"]")).click();
		
		driver.findElement(By.name("lastname")).sendKeys(contactLN);
		driver.findElement(By.xpath("//input[@name=\"account_name\"]/following-sibling::img")).click();
		
		// Store the parent window handle before opening a new window
		String parentWindowID = driver.getWindowHandle();
		//SWITCH TO CHILD WINDOW
		for (String windowID : driver.getWindowHandles()) {
		    driver.switchTo().window(windowID);
		    String actUrl = driver.getCurrentUrl();

		    if (actUrl.contains("module=Accounts")) {
		        break; // Found the desired child window
		    }
		}
		
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
				// //a[text()='Facebook2742'] this is static Xpath SO we MAKE IT DYANAMIC HERE 
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		// SWITCH to PARENT WINDOW
		// 🔁 Instead of using getWindowHandles() again, directly switch back to parent window
		driver.switchTo().window(parentWindowID);
		/*
		 * Set<String> set1= driver.getWindowHandles(); Iterator<String> it1=
		 * set1.iterator();
		 * 
		 * while(it1.hasNext()) { String windowID=it1.next();
		 * driver.switchTo().window(windowID);
		 * 
		 * String actUrl=driver.getCurrentUrl(); if(actUrl.contains("module=Contacts"))
		 * { break; } }
		 */
		
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		//Verify  Last name info Expected result
		String actLastName=driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actLastName.equals(contactLN)) {
			System.out.println(contactLN+" Information is verified--PASS");
			}
			else {
				System.out.println(contactLN+" Information is verified--FAIL");
			}
		//Verify  Last name info Expected result
		String actOrg=driver.findElement(By.id("mouseArea_Organization Name")).getText();
		if(actOrg.trim().equals(orgName)) {
			System.out.println(orgName+" Organization is verified--PASS");
			}
			else {
				System.out.println(orgName+" Organization is verified--FAIL");
			}
		WebElement logout= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		Actions action=new Actions(driver);
		action.moveToElement(logout).build().perform();
		
		//Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
	}


}
