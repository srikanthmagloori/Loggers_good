package pages;

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
	private WebElement btnMyAccount1;

	@FindBy(xpath = "//a[@title='My Account']")
	@CacheLookup
	private WebElement btnMyAccount2;

	@FindBy(xpath = "//a[text()='Login']")
	@CacheLookup
	private WebElement btnLogin;

	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']/li[5]")
	@CacheLookup
	private WebElement btnLogout;

	public void clickLoginBtn() {
		this.btnMyAccount1.click();
		this.btnLogin.click();
	}

	public void clickLogoutBtn() {
		this.btnMyAccount2.click();
		this.btnLogout.click();
	}
}
