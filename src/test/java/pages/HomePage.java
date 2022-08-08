package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[text()='Desktops']")
	private WebElement desktopHeaderBtn;

	@FindBy(xpath = "//a[text()='Show All Desktops']")
	private WebElement showAllDesktopBtn;

	public void clickDesktopDropdownBtn() {
		this.desktopHeaderBtn.click();
	}

	public void clickShowAllDesktopDropdownBtn() {
		this.showAllDesktopBtn.click();
	}

}
