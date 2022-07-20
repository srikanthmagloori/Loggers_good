package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.Header;
import pages.LoginPage;
import pages.LogoutPage;
import utility.BaseClass;

public class TC003_LogoutTest extends BaseClass {

	@Test
	public void logoutTest() {
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

		testLog.info("Page title :: " + driver.getTitle());
		testLog.info("Clicking Logout button");
		header.clickLogoutBtn();

		LogoutPage logoutPage = new LogoutPage(driver);
		Assert.assertEquals(logoutPage.getLogoutHeading(), "Account Logout");
	}
}
