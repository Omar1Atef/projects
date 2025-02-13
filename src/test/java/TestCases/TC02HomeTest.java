package TestCases;

import DriverFactory.DriverFactory;
import Pages.P01LoginPage;
import Pages.P02HomePage;
import Utilities.UtilityData;
import Utilities.UtilityLogs;
import org.testng.Assert;
import org.testng.annotations.*;
import Listeners.IInvokedListener;
import Listeners.ITestListener;

import java.io.IOException;
import java.time.Duration;

import static DriverFactory.DriverFactory.getDriver;
import static DriverFactory.DriverFactory.quitDriver;

@Listeners({IInvokedListener.class, ITestListener.class})
public class TC02HomeTest
{
    private final String USERNAME = UtilityData.getJsonData("validLogin","username");
    private final String PASSWORD = UtilityData.getJsonData("validLogin","password");

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
    public void checkNoOfSelectedItemsTC()
    {
        new P01LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .signInButton()
                .addAllItemsToCart();

        Assert.assertTrue(new P02HomePage(getDriver()).assertCompareSelectedItemsWithCartNo());
//        Assert.assertEquals(new P02HomePage(getDriver()).getNumberOfItemsInCart(),new P02HomePage(getDriver()).noOfSelectedItems());
    }

    @Test
    public void checkNoOfSelectedRandomItemsTC()
    {
        new P01LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .signInButton()
                .addRandomItemsToCart(3,6);

        Assert.assertTrue(new P02HomePage(getDriver()).assertCompareSelectedItemsWithCartNo());
//        Assert.assertEquals(new P02HomePage(getDriver()).getNumberOfItemsInCart(),new P02HomePage(getDriver()).noOfSelectedItems());
    }

    @Test
    public void clickOnCartIcon() throws IOException
    {
        new P01LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .signInButton()
                .addRandomItemsToCart(3,6)
                .clickOnCartIcon();

//        Assert.assertTrue(new P02HomePage(getDriver()).verifyCartPageUrl(UtilityData.getPropertyData("environment","CART_URL")));
        Assert.assertEquals(getDriver().getCurrentUrl(),UtilityData.getPropertyData("environment","CART_URL"));


    }


    @AfterMethod
    public  void quit()
    {
        quitDriver();
    }

}
