package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import utility.BasePage;

public class LogoutPage extends BasePage {
	public LogoutPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@id='content']/h1")
	@CacheLookup
	private WebElement logoutHeading;

	public String getLogoutHeading() {
		return this.logoutHeading.getText();
	}

}
