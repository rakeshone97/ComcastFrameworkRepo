package pactice.TestNG;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.BaseTest.BaseClass;

public class Retry_Listener_Test extends BaseClass {
	
	@Test(retryAnalyzer = com.comcast.crm.ListenerUtility.RetryListnerImp.class)
	public void activateSim()
	{
	System.out.println("Execute createInvoiceTest");
	String actTitle=driver.getTitle();
	Assert.assertEquals(actTitle, "Login");
	System.out.println("Step-1");
	System.out.println("Step-2");
	System.out.println("Step-3");
	System.out.println("Step-4");

	}
	

}
