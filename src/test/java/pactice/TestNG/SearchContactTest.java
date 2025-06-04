package pactice.TestNG;
/**
 * @author Rakesh
 */

import org.testng.annotations.Test;

import com.comcast.crm.BaseTest.BaseClass;
import com.comcast.crm.ObjectRepositoryUtility.LoginPage;

public class SearchContactTest extends BaseClass{
	/**
	 * scenario: Login()==> Navigate==>CreateContact()==> Verify
	 */
	@Test
	public void searchContact()
	{	
		/*Step:1 Login to Application */
		LoginPage lp= new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
	}
}
