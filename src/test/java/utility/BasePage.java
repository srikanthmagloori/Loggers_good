package utility;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;

public class BasePage {
	public WebDriver driver;
	public Properties prop;
	public ExtentTest test;

	public BasePage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		prop = PropertyReader.getInstance();
		PageFactory.initElements(driver, this);
	}
}
