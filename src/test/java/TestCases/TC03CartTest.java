package TestCases;

import DriverFactory.DriverFactory;
import Pages.P01LoginPage;
import Pages.P02HomePage;
import Pages.P03CartPage;
import Utilities.UtilityData;
import Utilities.UtilityLogs;
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
public class TC03CartTest
{

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
    public void comparingPricesTC()
    {
        String totalPrice = new P01LoginPage(getDriver())
                .enterUsername(UtilityData.getJsonData("validLogin","username"))
                .enterPassword(UtilityData.getJsonData("validLogin","password"))
                .signInButton()
                .addRandomItemsToCart(4,6)
                .getTotalPriceOfSelectedProductsInHomePage();
           new P02HomePage(getDriver()).clickOnCartIcon();

        Assert.assertTrue(new P03CartPage(getDriver()).assertionComparePrices(totalPrice));
//        Assert.assertEquals(new P02HomePage(getDriver()).getTotalPriceOfSelectedProductsInHomePage(),new P03CartPage(getDriver()).getTotalPriceOfSelectedProductsInCartPage()); //lw hst5dm deh lazm a3ml hagten y2ma m3mlsh variable static 3lshan fun getTotalPriceOfSelectedProductsInHomePage() 3mltlha call mrten wl variable static fhy3ml total price mrten aw eni asebo static bs asfr dyman variable fawl function

    }


    @AfterMethod
    public  void quit()
    {
        quitDriver();
    }

}
