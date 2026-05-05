package pages;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BaseTest {

    private WebDriver driver;

    private By usernameTextBox = By.id("user-name");
    private By passwordTextBox = By.id("password");
    private By loginButton = By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/input");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        // driver.findElement(usernameTextBox).clear();
        limpiar(usernameTextBox);
        esperarXSegundos(3);
        driver.findElement(usernameTextBox).sendKeys(username);
    }

    public void enterPassword(String password) {
        // driver.findElement(passwordTextBox).clear();
        limpiar(passwordTextBox);
        esperarXSegundos(3);
        driver.findElement(passwordTextBox).sendKeys(password);
    }

    public void clickLogin() {
        esperarXSegundos(3);
        driver.findElement(loginButton).click();
        esperarXSegundos(3);
    }

}
