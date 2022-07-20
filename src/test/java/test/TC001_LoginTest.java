package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.Header;
import pages.LoginPage;
import utility.BaseClass;

public class TC001_LoginTest extends BaseClass {

	@Test
	public void loginTest() {

		String email = prop.getProperty("EMAIL");
		String pwd = prop.getProperty("PWD");

		Header hp = new Header(driver);
		testLog.info("Clicking Login button from My Account dropdown");
		hp.clickLoginBtn();

		LoginPage lp = new LoginPage(driver);

		testLog.info("Inputting Email :: " + email);
		lp.setEmail(email);
		testLog.info("Inputting Password");
		lp.setPassword(pwd);

		testLog.info("Clicking Login Button");
		lp.clickLoginBtn();

		Assert.assertEquals(driver.getTitle(), "MyAccount");
	}
}
