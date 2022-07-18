package utility;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	public WebDriver driver;
	public Properties prop;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		prop = PropertyReader.getInstance();
		PageFactory.initElements(driver, this);
	}
}
