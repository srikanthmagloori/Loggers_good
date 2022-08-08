package utility;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;

@Listeners({ utility.Reporting.class })
public class BaseClass {
	public static WebDriver driver;
	public static Properties prop;
	protected static ExtentTest testLog;

	@BeforeTest
	@Parameters("browser")
	public void setup(@Optional String browser) {
		Loggers.breakLine();
		Loggers.info("Reading Config file");
		prop = PropertyReader.getInstance();

		browser = (browser == null) ? prop.getProperty("BROWSER") : browser;
		driver = DriverFactory.setBrowserInstance(browser);
		Loggers.config("Driver Started in " + browser + " browser");
	}

	@BeforeClass
	public void urlIntialization() {
		String url = prop.getProperty("OPEN_CART_URL");
		Loggers.nextLine();
		Loggers.info("Test Execution Started");
		Loggers.info("Opening url -> " + url);
		driver.get(url);
	}

	@AfterTest
	public void tearDown() {
		Loggers.nextLine();
		Loggers.info("Test Execution Completed");
		Loggers.info("Closing the browser instance");
		driver.quit();
	}
}
