package TestCases;

import DriverFactory.DriverFactory;
import Pages.P01LoginPage;
import Pages.P02HomePage;
import Pages.P03CartPage;
import Pages.P04CheckoutPage;
import Utilities.Utility;
import Utilities.UtilityData;
import Utilities.UtilityLogs;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import Listeners.IInvokedListener;
import Listeners.ITestListener;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.quitDriver;


@Listeners({IInvokedListener.class, ITestListener.class})
public class TC04CheckoutTest
{

    private final String FIRSTNAME = UtilityData.getJsonData("information","firstname") + "-" + Utility.getTimeStamp();
    private final String LASTNAME = UtilityData.getJsonData("information","lastname") + "-" + Utility.getTimeStamp();
    private final String ZIPCODE = new Faker().number().digits(5);
    private final int POSTALCode = Utility.generateRandomNumbersInRange(9999,99999);

    @BeforeMethod
    public void setup() throws IOException
    {
        DriverFactory.setupDriver(UtilityData.getPropertyData("environment","Browser"));
        UtilityLogs.info("EDGE Browser IS Opened");
        getDriver().get(UtilityData.getPropertyData("environment","BASE_URL"));
        UtilityLogs.info("Redirected To LoginPage");
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(15));

    }


    @Test
    public void checkOutStepOneTC() throws IOException
    {
        //TODO:Login Steps
        new P01LoginPage(getDriver())
        .enterUsername(UtilityData.getJsonData("validLogin","username"))
        .enterPassword(UtilityData.getJsonData("validLogin","password"))
        .signInButton();
         //TODO:Add product Steps
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

//        Assert.assertTrue(Utility.verifyURL(getDriver(),UtilityData.getPropertyData("environment","OVERVIEW_URL")));
        Assert.assertEquals(getDriver().getCurrentUrl(),UtilityData.getPropertyData("environment","OVERVIEW_URL"));
    }

    @AfterMethod
    public  void quit()
    {
        quitDriver();
    }

}
