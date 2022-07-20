package utility;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;

@Listeners({ utility.Reporting.class })
public class BaseClass {
	public static WebDriver driver;
	public static Properties prop;
	protected static ExtentTest testLog;

	@BeforeClass
	@Parameters("browser")
	public void setup(@Optional String browser) throws IOException {
		prop = PropertyReader.getInstance();

		browser = (browser == null) ? prop.getProperty("BROWSER") : browser;
		driver = DriverFactory.setBrowserInstance(browser);
		System.out.println("===============================================\n");
		System.out.println("Driver Started in " + browser + " browser");

		String url = prop.getProperty("URL");
		System.out.println("Opening url :: " + url);
		driver.get(url);
	}

	@AfterClass
	public void tearDown() {
		System.out.println("Testing Completed");
		System.out.println("Closing the browser instance");
		System.out.println("\n===============================================");
		driver.quit();
	}
}
