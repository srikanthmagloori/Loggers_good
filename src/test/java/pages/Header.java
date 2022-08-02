package pages;

import static utility.DriverFactory.waitForElemenetToBeDisplayed;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import utility.BasePage;

public class Header extends BasePage {

	public Header(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[@title='My Account']")
	@CacheLookup
	private WebElement btnMyAccount;

	@FindBy(xpath = "//a[text()='Login']")
	@CacheLookup
	private WebElement btnLogin;

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']/li[5]")
	@CacheLookup
	private WebElement btnLogout;

	public void clickLoginBtn() {
		waitForElemenetToBeDisplayed(this.btnMyAccount);
		this.btnMyAccount.click();

		waitForElemenetToBeDisplayed(this.btnLogin);
		this.btnLogin.click();
	}

	public void clickLogoutBtn() {
		waitForElemenetToBeDisplayed(this.btnMyAccount);
		this.btnMyAccount.click();

		waitForElemenetToBeDisplayed(this.btnLogout);
		this.btnLogout.click();
	}
}
