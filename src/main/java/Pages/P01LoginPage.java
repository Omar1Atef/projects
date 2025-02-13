package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01LoginPage
{
    private final WebDriver driver;

    private final By usernameLocator = By.cssSelector("[placeholder=\"Username\"]");
    private final By passwordLocator = By.cssSelector("[placeholder=\"Password\"]");
    private final By loginLocator = By.id("login-button");


    public P01LoginPage(WebDriver driver)  //Constructor
    {
        this.driver = driver;
    }

    public P01LoginPage enterUsername(String username)
    {
        Utility.sendData(driver, usernameLocator, username);
        return this;
    }

    public P01LoginPage enterPassword(String password)
    {
        Utility.sendData(driver, passwordLocator, password);
        return this;
    }

    public P02HomePage signInButton()
    {
        Utility.clickOnElements(driver, loginLocator);
        return new P02HomePage(driver);
    }

    public boolean assertLoginTC(String expected)
    {
        return driver.getCurrentUrl().equals(expected);
    }


}
