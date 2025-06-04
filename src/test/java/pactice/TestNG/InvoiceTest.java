package pactice.TestNG;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.BaseTest.BaseClass;

@Listeners(com.comcast.crm.ListenerUtility.ListImpleClass.class)
public class InvoiceTest extends BaseClass {
	
	@Test
	public void createInvoiceTest()
	{
	System.out.println("Execute createInvoiceTest");
	String actTitle=driver.getTitle();
	Assert.assertEquals(actTitle, "Login");
	System.out.println("Step-1");
	System.out.println("Step-2");
	System.out.println("Step-3");
	System.out.println("Step-4");

	}
	
	@Test
	public void createInvoiceWithContactTest()
	{
		System.out.println("Execute createInvoiceWithContactTest");	
		System.out.println("Step-1");
		System.out.println("Step-2");
		System.out.println("Step-3");
		System.out.println("Step-4");
	
	}
}
