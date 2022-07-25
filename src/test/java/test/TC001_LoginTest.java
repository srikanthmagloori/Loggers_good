package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.Header;
import pages.LoginPage;
import pages.LogoutPage;
import utility.BaseClass;

public class TC001_LoginTest extends BaseClass {

	/**
	 * Login Test
	 * 
	 * Checks the login functionality of the system with the given credentials
	 * 
	 */
	@Test
	public void loginTest() {
		String email = prop.getProperty("EMAIL");
		String pwd = prop.getProperty("PWD");

		Header header = new Header(driver);
		testLog.info("Clicking Login button from My Account dropdown");
		header.clickLoginBtn();

		LoginPage loginPage = new LoginPage(driver);

		testLog.info("Inputting Email :: " + email);
		loginPage.setEmail(email);
		testLog.info("Inputting Password");
		loginPage.setPassword(pwd);

		testLog.info("Clicking Login Button");
		loginPage.clickLoginBtn();

		Assert.assertEquals(driver.getTitle(), "My Account");
	}

	/**
	 * Logout Test
	 * 
	 * Checks the logout functionality of the system if the correct credentials have
	 * been provided i.e. when the lognTest() is successfully passed, then this
	 * function will run the test.
	 * 
	 */
	@Test(dependsOnMethods = { "loginTest" })
	public void logoutTest() {
		Header header = new Header(driver);
		testLog.info("Page title :: " + driver.getTitle());
		testLog.info("Clicking Logout button");
		header.clickLogoutBtn();

		LogoutPage logoutPage = new LogoutPage(driver);
		Assert.assertEquals(logoutPage.getLogoutHeading(), "Account Logout");
	}
}
