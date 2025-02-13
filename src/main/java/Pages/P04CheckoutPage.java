package Pages;

import Utilities.Utility;
import Utilities.UtilityLogs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static Utilities.Utility.generalWait;

public class P04CheckoutPage
{
    private final WebDriver driver;

    public P04CheckoutPage(WebDriver driver)
    {
        this.driver = driver;
    }

    private final By firstNameLocator = By.id("first-name");
    private final By lastNameLocator = By.id("last-name");
    private final By zipLocator = By.id("postal-code");
    private final By continueButtonLocator = By.id("continue");
    private final By cancelButtonLocator = By.id("cancel");


    public P04CheckoutPage enterFirstName(String firstname)
    {
        Utility.sendData(driver,firstNameLocator,firstname);
//        UtilityLogs.info("Firstname is " + firstname);
        return this;
    }

    public P04CheckoutPage enterLastName(String lastname)
    {
        Utility.sendData(driver,lastNameLocator,lastname);
//        UtilityLogs.info("Lastname is " + lastname);
        return this;
    }

    public P04CheckoutPage enterPostalCode(String zipcode)
    {
        Utility.sendData(driver,zipLocator,zipcode);
//        UtilityLogs.info("Zipcode is " + zipcode);
        return this;
    }

    public P05OverviewPage clickContinueButton()
    {
        Utility.clickOnElements(driver,continueButtonLocator);
        return new P05OverviewPage(driver);
    }

    public P03CartPage clickOnCancelButton(String zipcode)
    {
        Utility.clickOnElements(driver,cancelButtonLocator);
        return new P03CartPage(driver);
    }

    public boolean verifyOverviewPageUrl(String expected)
    {
        return driver.getCurrentUrl().equals(expected);
    }


}
