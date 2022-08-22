package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;

import utility.BasePage;
import utility.Driver;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}

	@FindBy(id = "ap_email")
	private WebElement emailInput;

	@FindBy(id = "continue")
	private WebElement continueBtn;

	@FindBy(id = "ap_password")
	private WebElement passwordInput;

	@FindBy(id = "signInSubmit")
	private WebElement signInBtn;

	public void doLogin(String email, String password) {
		Driver.waitForElementToBeDisplayed(this.emailInput);
		this.emailInput.sendKeys(email);
		test.info("Inputting email -> " + email);
		this.continueBtn.click();
		test.info("Clicking on continue button");

		Driver.waitForElementToBeDisplayed(this.passwordInput);
		test.info("Inputting password -> " + password);
		this.passwordInput.sendKeys(password);
		test.info("Clicking on sign in button");
		this.signInBtn.click();

	}

}
