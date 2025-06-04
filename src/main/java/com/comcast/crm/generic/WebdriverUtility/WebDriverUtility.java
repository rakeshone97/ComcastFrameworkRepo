package com.comcast.crm.generic.WebdriverUtility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
		public void waitForPageToLoad(WebDriver driver)
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
		
		public void waitForElementPresent(WebDriver driver,WebElement element)
		{
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		
		public void switchToTabOnUrl(WebDriver driver, String partialUrl)
		{
			for (String windowID : driver.getWindowHandles()) {
			    driver.switchTo().window(windowID);
			    String actUrl = driver.getCurrentUrl();

			    if (actUrl.contains(partialUrl)) {
			        break; // Found the desired child window
			    }
			}
		}
		
		public void switchToTabOnTitle(WebDriver driver, String partialTitle)
		{
			for (String windowID : driver.getWindowHandles()) {
			    driver.switchTo().window(windowID);
			    String actUrl = driver.getCurrentUrl();

			    if (actUrl.contains(partialTitle)) {
			        break; // Found the desired child window
			    }
			}
		}
		
		public void switchToFrame(WebDriver driver,int index)
		{
			driver.switchTo().frame(index);
		}
		
		public void switchToFrame(WebDriver driver,String nameID)
		{
			driver.switchTo().frame(nameID);
		}
		
		public void switchToFrame(WebDriver driver,WebElement element)
		{
			driver.switchTo().frame(element);
		}
		
		public void switchToAlertAndAccept(WebDriver driver)
		{
			driver.switchTo().alert().accept();
		}
		
		public void switchToAlertAndcancel(WebDriver driver)
		{
			driver.switchTo().alert().dismiss();
		}
		
		public void select(WebElement element,String text)
		{
			Select sel= new Select(element);
			sel.selectByVisibleText(text);
		}
		
		public void select(WebElement element,int index)
		{
			Select sel= new Select(element);
			sel.selectByIndex(index);
		}
		
		public void mouseMoveOnElement(WebDriver driver, WebElement element)
		{
			Actions act = new Actions(driver);
			act.moveToElement(element).perform();;
		}
		
		public void doubleClick(WebDriver driver, WebElement element)
		{
			Actions act = new Actions(driver);
			act.doubleClick(element).perform();
		}
		
		
		
		


}
