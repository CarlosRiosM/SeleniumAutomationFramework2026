package pages;

import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Log;

public class LoginPage extends BaseTest {
    private WebDriver driver;

    @FindBy(id = "user-name")
    WebElement usernameTextBox;

    @FindBy(id = "password")
    WebElement passwordTextBox;

    @FindBy(xpath = "/html/body/div/div/div[2]/div[1]/div/div/form/input")
    WebElement loginButton;

    /*
    private By usernameTextBox = By.id("user-name");
    private By passwordTextBox = By.id("password");
    private By loginButton = By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/input");
     */

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        usernameTextBox.clear();
        esperarXSegundos(3);
        usernameTextBox.sendKeys(username);
        // driver.findElement(usernameTextBox).clear();
        // driver.findElement(usernameTextBox).sendKeys(password);
    }

    public void enterPassword(String password) {
        passwordTextBox.clear();
        esperarXSegundos(3);
        passwordTextBox.sendKeys(password);
        // driver.findElement(passwordTextBox).clear();
        // driver.findElement(passwordTextBox).sendKeys(password);
    }

    public void clickLogin() {
        esperarXSegundos(3);
        Log.info("Clicking login button...");
        loginButton.click();
        // driver.findElement(loginButton).click();
        esperarXSegundos(3);
    }

}