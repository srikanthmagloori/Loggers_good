package utility;

import static utility.Loggers.nextLine;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;

@Listeners({utility.Reporting.class})
public class BaseClass {
    public static WebDriver driver;
    public static Properties prop;
    protected static ExtentTest testLog;

    @BeforeClass
    @Parameters("browser")
    public void setup(@Optional String browser) {
        browser = (browser == null) ? prop.getProperty("BROWSER") : browser;
        Loggers.config("Driver Started in " + browser + " browser");
        driver = Driver.setBrowserInstance(browser);
        String url = prop.getProperty("URL");
        Loggers.nextLine();
        Loggers.info("Test Execution Started");
        Loggers.info("Opening url -> " + url);
        driver.get(url);
    }

    @AfterClass
    public void tearDown() {
        nextLine();
        Loggers.info("Test Execution Completed");
        Loggers.info("Closing the browser instance");
        driver.quit();
    }
}
