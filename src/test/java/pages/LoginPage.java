package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import utility.BasePage;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "input-email")
	@CacheLookup
	private WebElement email;

	@FindBy(id = "input-password")
	@CacheLookup
	private WebElement password;

	@FindBy(xpath = "//input[@value='Login']")
	@CacheLookup
	private WebElement btnSubmit;

	public void setEmail(String email) {
		this.email.clear();
		this.email.sendKeys(email);
	}

	public void setPassword(String password) {
		this.password.clear();
		this.password.sendKeys(password);
	}

	public void clickLoginBtn() {
		this.btnSubmit.click();
	}

}
