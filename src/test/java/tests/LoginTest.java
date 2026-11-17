package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExtentReportManager;
import utils.Log;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() {

        Log.info("Starting login test...");
        test = ExtentReportManager.createTest("Login Test");
        LoginPage loginPage = new LoginPage(driver);
        test.info("Navigated to Login Page");

        Log.info("Adding credentials");
        test.info("Adding credentials");
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        test.info("Clicking on Login Button");
        loginPage.clickLogin();
        test.info("Entered credentials and clicked Login");

        String title = driver.getTitle();
        System.out.println("The title of the page is: "+title);
        Log.info("Verifying page title...");
        test.info("Verifying page title...");
        Assert.assertEquals(driver.getTitle(), "Swag Labs");


        Log.info("Login Test completed");
        test.pass("Login Successful");
    }

    @Test
    public void testLoginWithInvalidCredentials() {

        Log.info("Starting login test...");
        test = ExtentReportManager.createTest("Login Test With Invalid Credentials");
        LoginPage loginPage = new LoginPage(driver);
        test.info("Navigated to Login Page");

        Log.info("Adding credentials");
        test.info("Adding credentials");
        loginPage.enterUsername("standard_user123");
        loginPage.enterPassword("secret_sauce123");
        test.info("Clicking on Login Button");
        loginPage.clickLogin();
        test.info("Entered credentials and clicked Login");

        String title = driver.getTitle();
        System.out.println("The title of the page is: "+title);
        Log.info("Verifying page title...");
        test.info("Verifying page title...");
        Assert.assertEquals(driver.getTitle(), "Swag Labs");


        Log.info("Login Test completed");
        test.pass("Login Successful");
    }

}