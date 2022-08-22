package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;

import utility.BasePage;
import utility.Driver;

public class NavbarComp extends BasePage {

	public NavbarComp(WebDriver driver, ExtentTest test) {
		super(driver, test);
	}

	@FindBy(id = "nav-link-accountList")
	private WebElement accountList;

	@FindBy(xpath = "(//a[@data-nav-role='signin'])[2]")
	private WebElement signInBtn;

	@FindBy(id = "nav-link-accountList-nav-line-1")
	private WebElement currentUsername;

	public void clickOnSignInButton() {
		Actions action = new Actions(driver);
		test.info("Hovering over Account List on the navbar");
		action.moveToElement(this.accountList).perform();
		test.info("Clicking on the sign in button");
		this.signInBtn.click();
	}

	public String getCurrentUsername() {
		Driver.waitForElementToBeDisplayed(this.currentUsername);
		return this.currentUsername.getText().split(" ")[1].trim();
	}
}
