package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import pages.NavbarComp;
import utility.BaseClass;
import utility.Loggers;

public class TC001_LoginTest extends BaseClass {

	@Test(testName = "TC001 Login Test", groups = { "positive", "smoke" })
	public void loginTest() {
		testLog.assignCategory("positive", "smoke");

		NavbarComp navbar = new NavbarComp(driver, testLog);
		navbar.clickOnSignInButton();

		// Parameters for the test
		String email = prop.getProperty("EMAIL");
		String password = prop.getProperty("PASSWORD");

		LoginPage loginPage = new LoginPage(driver, testLog);
		loginPage.doLogin(email, password);

		Assert.assertEquals(navbar.getCurrentUsername(), "Francis");
	}

}
