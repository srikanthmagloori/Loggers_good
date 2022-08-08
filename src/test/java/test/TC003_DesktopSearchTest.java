package test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pages.DesktopsResultPage;
import pages.HomePage;
import utility.BaseClass;

public class TC003_DesktopSearchTest extends BaseClass {

	@Test(testName = "Check for Number of default products in desktops")
	public void checkForDesktopCategory() {
		SoftAssert softAssert = new SoftAssert();
		String expectedText = "Desktops";
		long expectedNoOfProducts = 12;

		HomePage homePage = new HomePage(driver);
		testLog.info("Clicking Desktops button from home page navbar");
		homePage.clickDesktopDropdownBtn();
		testLog.info("Clicking Show all Desktops button from desktop dropdown");
		homePage.clickShowAllDesktopDropdownBtn();

		DesktopsResultPage resultPage = new DesktopsResultPage(driver);
		String actualText = resultPage.getHeaderValue();
		long actualNoOfProducts = resultPage.getTotalProducts();

		softAssert.assertEquals(actualText, expectedText);
		softAssert.assertEquals(actualNoOfProducts, expectedNoOfProducts);
		softAssert.assertAll();
	}
}