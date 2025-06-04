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
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithIndustry_TypeTest {
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
		Row row=sh.getRow(4);
		String orgName=row.getCell(2).getStringCellValue()+(int)(Math.random()*10000);
		String industry=row.getCell(3).toString();
		String type=row.getCell(4).toString();
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
		WebElement ind= driver.findElement(By.name("industry"));
		Select sel=new Select(ind);
		sel.selectByVisibleText(industry); //industry is reading from Excel sheet
		
		WebElement typ= driver.findElement(By.xpath("//select[@name=\"accounttype\"]"));
		//Thread.sleep(2000);
		Select sel2=new Select(typ);
		sel2.selectByVisibleText(type);
		
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		//Thread.sleep(2000);
		
		//Verify the Drop down of industries and type Info
		
		String actIndustries=driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustries.equals(industry)) {
			System.out.println(industry+" Information is verified--PASS");
			}
			else {
				System.out.println(industry+"Information is Verified--FAIL");
			}
		String actType=driver.findElement(By.id("dtlview_Type")).getText();
		if(actType.equals(type)) {
			System.out.println(actType+"Information is Verified--PASS");
			}
			else {
				System.out.println(actType+"Information is Verified--FAIL");
			}
		
		WebElement logout= driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		Actions action=new Actions(driver);
		action.moveToElement(logout).build().perform();
		
		//Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
	}


}
