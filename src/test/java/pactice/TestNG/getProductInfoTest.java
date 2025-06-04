package pactice.TestNG;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.FileUtility.ExcelUtility;

public class getProductInfoTest {
	
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String mobile,String mName)
	{
		//hello
		WebDriver driver= new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(mobile,Keys.ENTER);
		WebElement element=driver.findElement(By.xpath("//div[@class=\"a-section a-spacing-small a-spacing-top-small\" and contains(.,'"+mName+"')]//span[@class=\"a-price-whole\"]"));
		System.out.println(element.getText());
		
		driver.quit();
	}
		@DataProvider
		public Object[][] getData() throws EncryptedDocumentException, IOException{
			
			ExcelUtility eLib=new ExcelUtility();
			 int rowCount=eLib.getRowCount("product");
			
			
			Object[][]  objArr= new Object[rowCount][2];
			for(int i=0; i<rowCount;i++)
			{
			objArr[i][0]=eLib.getDataFromExcel("product", i+1, 0);
			objArr[i][1]=eLib.getDataFromExcel("product", i+1, 1);
			}
			
			return objArr;
		}
		
		
		
	
}
