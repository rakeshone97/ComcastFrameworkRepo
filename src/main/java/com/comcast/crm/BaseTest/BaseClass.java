package com.comcast.crm.BaseTest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.ObjectRepositoryUtility.HomePage;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;
import com.comcast.crm.generic.DataBaseUtility.DatabaseUtility;
import com.comcast.crm.generic.FileUtility.ExcelUtility;
import com.comcast.crm.generic.FileUtility.FileUtility;
import com.comcast.crm.generic.WebdriverUtility.JavaUtility;
import com.comcast.crm.generic.WebdriverUtility.UtilityClassObject;

public class BaseClass {
	//Create Object of Utility
	public DatabaseUtility dbLib = new DatabaseUtility();
	public FileUtility fLib=new FileUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public JavaUtility  jLib=new JavaUtility();
	public WebDriver driver=null;
	//public ExtentSparkReporter spark;
	//public ExtentReports report;
	///Creating a static driver session to use in Listener because we can not use the driver session 
	///from @BeforeClass annotation due to it is instruction
	///Also we can not make their driver static 
	///Otherwise it will not participate in PARALLEL EXECUTION
	///
	public static WebDriver sdriver=null;
	
	@BeforeSuite(groups={"smokeTest","regressionTest"})
	public void ConfigBS() throws SQLException
	{
		System.out.println("===Connect DB,  Report Config===");
		dbLib.getDbConnection();
		
		/*
		 * // spark report config by creating object of ExtentSparkReporter
		 * ExtentSparkReporter spark = new
		 * ExtentSparkReporter("./AdvanceReport/report.html");
		 * spark.config().setDocumentTitle("CRM TEST Suite Results");
		 * spark.config().setReportName("CRM Report");
		 * spark.config().setTheme(Theme.DARK);
		 * 
		 * // add Environment Information and Create test report = new ExtentReports();
		 * report.attachReporter(spark); report.setSystemInfo("OS", "Win-10");
		 * report.setSystemInfo("BROWSER", "Chrome");
		 */
		
	}
	// @Parameters ("BROWSER")
	@BeforeClass(groups={"smokeTest","regressionTest"})
	public void ConfigBC() throws IOException
	{
		System.out.println("===Launch Browser===");
		String BROWSER=fLib.getDataFromPropertiesFile("browser");
		
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
		sdriver=driver;
		UtilityClassObject.setDriver(driver);
		
	}
	@BeforeMethod(groups={"smokeTest","regressionTest"})
	public void configBM() throws IOException
	{
		System.out.println(" Login To App");
		LoginPage lp=new LoginPage(driver);
		String URL= fLib.getDataFromPropertiesFile("url");
		String BROWSER= fLib.getDataFromPropertiesFile("browser");
		String USERNAME= fLib.getDataFromPropertiesFile("username");
		String PASSWORD= fLib.getDataFromPropertiesFile("password");
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}
	
	@AfterMethod(groups={"smokeTest","regressionTest"})
	public void configAM()
	{
		System.out.println("Logout From App");
		HomePage hp=new HomePage(driver);
		hp.logout();
	}
	@AfterClass(groups={"smokeTest","regressionTest"})
	public void ConfigAC()
	{
		System.out.println("===Close Browser===");
		driver.quit();
	}
	@AfterSuite(groups={"smokeTest","regressionTest"})
	public void ConfigAS() throws SQLException
	{
		System.out.println("===Disconnect DB,  Report BackUp===");
		dbLib.closeDbConnection();
		/* report.flush(); */
	}
	
	
}
