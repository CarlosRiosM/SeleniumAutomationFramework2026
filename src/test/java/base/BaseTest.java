package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentReportManager;
import utils.Log;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;

public class BaseTest {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentReportManager.getReportInstance();
    }

    @BeforeMethod
    public void setUp() {
        Log.info("Starting WebDriver...");
        System.setProperty("webdriver.chrome.driver", "./src/main/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIMEOUT));
        driver.manage().window().maximize();
        Log.info("Navigating to test URL...");
        driver.get("https://www.saucedemo.com/");
    }

    public void limpiar(By locator) {
        try {
            driver.findElement(locator).clear();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void esperarXSegundos(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void esperaImplicita(int segundos) {
        try {
            driver.manage().timeouts().implicitlyWait(segundos, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = ExtentReportManager.captureScreenshot(driver, "LoginFailure3");
            System.out.println("ScreenShots Capture PATH: "+screenshotPath);
            test.fail("Test Failed. Screenshot attached.",
            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }

        if(driver != null) {
            Log.info("Closing Browser");
            // driver.quit();
        }
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }

}
