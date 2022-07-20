package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.Header;
import pages.LoginPage;
import utility.BaseClass;
import utility.ExcelReader;

public class TC002_LoginTest_DDT extends BaseClass {

	private static ExcelReader excel;

	@Test(dataProvider = "loginData")
	public void loginTestDDT(String email, String password) {
		Header header = new Header(driver);
		testLog.info("Clicking Login button from My Account dropdown");
		header.clickLoginBtn();
		LoginPage loginPage = new LoginPage(driver);

		testLog.info("Inputting Email :: " + email);
		loginPage.setEmail(email);
		testLog.info("Inputting Password :: " + password);
		loginPage.setPassword(password);
		testLog.info("Clicking Login Button");
		loginPage.clickLoginBtn();

		boolean checkTitle = driver.getTitle().equals("My Account");
		Assert.assertTrue(checkTitle);
		if (checkTitle)
			header.clickLogoutBtn();

	}

	@DataProvider(name = "loginData")
	public static Object[][] loginData() {
		excel = new ExcelReader(".//src//test//resources//test_data//ecommerceTestData.xlsx");
		String sheetName = "Login";

		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] loginData = new Object[rows - 1][cols];

		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				loginData[i - 1][j] = excel.getCellData(sheetName, i, j);
			}
		}
		return loginData;
	}

}
