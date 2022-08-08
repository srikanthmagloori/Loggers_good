package pages;

import java.util.List;

import static utility.DriverFactory.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utility.BasePage;

public class DesktopsResultPage extends BasePage {
	private final static String XPATH_FOR_PRODUCTS = "//div[contains(@class,'product-layout')]";

	public DesktopsResultPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@id='content']/h2")
	private WebElement headerValue;

	@FindBy(xpath = XPATH_FOR_PRODUCTS)
	private List<WebElement> products;

	public String getHeaderValue() {
		waitForElementToBeDisplayed(this.headerValue);
		return this.headerValue.getText().trim();
	}

	public long getTotalProducts() {
		waitForAllElementsToBeDisplayed(By.xpath(XPATH_FOR_PRODUCTS));
		return this.products.size();
	}
}
