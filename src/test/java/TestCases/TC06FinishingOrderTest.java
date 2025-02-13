package TestCases;

import DriverFactory.DriverFactory;
import Pages.*;
import Utilities.Utility;
import Utilities.UtilityData;
import Utilities.UtilityLogs;
import com.github.javafaker.Faker;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.*;
import Listeners.IInvokedListener;
import Listeners.ITestListener;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.quitDriver;

@Listeners({IInvokedListener.class, ITestListener.class})
public class TC06FinishingOrderTest
{
    private final String FIRSTNAME = UtilityData.getJsonData("information","firstname") + "-" + Utility.getTimeStamp();
    private final String LASTNAME = UtilityData.getJsonData("information","lastname") + "-" + Utility.getTimeStamp();
    private final String ZIPCODE = new Faker().number().digits(5);
    private final int POSTALCode = Utility.generateRandomNumbersInRange(9999,99999);

    private Set<Cookie> cookies;

    @BeforeClass
    public void login() throws IOException
    {
        DriverFactory.setupDriver(UtilityData.getPropertyData("environment","Browser"));
        UtilityLogs.info("EDGE Browser IS Opened");
        getDriver().get(UtilityData.getPropertyData("environment","BASE_URL"));
        UtilityLogs.info("Redirected To LoginPage");
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(15));
        //TODO:Login Steps
        new P01LoginPage(getDriver())
                .enterUsername(UtilityData.getJsonData("validLogin","username"))
                .enterPassword(UtilityData.getJsonData("validLogin","password"))
                .signInButton();

         cookies = Utility.getAllCookies(getDriver());
         quitDriver();
    }


    @BeforeMethod
    public void setup() throws IOException
    {
        DriverFactory.setupDriver(UtilityData.getPropertyData("environment","Browser"));
        UtilityLogs.info("EDGE Browser IS Opened");
        getDriver().get(UtilityData.getPropertyData("environment","BASE_URL"));
        UtilityLogs.info("Open Login Page");
        Utility.restoreAllCookies(getDriver(),cookies);
        getDriver().get(UtilityData.getPropertyData("environment","HOME_URL"));
        UtilityLogs.info("Redirected To HomePage Direct Because Cookies Of Login Is Saved");

    }

    @Test
    public void finishOrderTC() throws IOException
    {

        new P02HomePage(getDriver())
                .addRandomItemsToCart(4,6)
                .clickOnCartIcon();
        //TODO:Go TO Checkout Steps
        new P03CartPage(getDriver())
                .clickOnCheckoutButton();
        //TODO:Filling Information Steps
        new P04CheckoutPage(getDriver())
                .enterFirstName(FIRSTNAME)
                .enterLastName(LASTNAME)
                .enterPostalCode(String.valueOf(POSTALCode))
                .clickContinueButton();
        UtilityLogs.info("Zipcode is " + POSTALCode + " Firstname is " + FIRSTNAME + " Lastname is " + LASTNAME);

        //TODO: Go To Finish Order Page
        new P05OverviewPage(getDriver()).clickOnFinishButton();

        Assert.assertTrue(new P06FinishingOrderPage(getDriver()).assertVisibilityOfThanksMessage());
    }

    @AfterMethod
    public  void quit()
    {
        quitDriver();
    }
}
