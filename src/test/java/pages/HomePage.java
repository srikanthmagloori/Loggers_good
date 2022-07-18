package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import utility.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[@title='My Account']")
	@CacheLookup
	private WebElement btnMyAccount;

	@FindBy(xpath = "//a[text()='Login']")
	@CacheLookup
	private WebElement btnLogin;

	public void clickLoginBtn() {
		this.btnMyAccount.click();
		this.btnLogin.click();
	}
}
