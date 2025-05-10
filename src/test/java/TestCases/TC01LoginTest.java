package TestCases;

import DriverFactory.DriverFactory;
import Pages.P01LoginPage;
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


@Listeners({IInvokedListener.class,ITestListener.class})

public class TC01LoginTest
{
    private final String USERNAME = UtilityData.getJsonData("validLogin","username");
    private final String PASSWORD = UtilityData.getJsonData("validLogin","password");

    @BeforeMethod
    public void setup() throws IOException
    {
        String browser = System.getProperty("myBrowser") != null ? System.getProperty("myBrowser") : UtilityData.getPropertyData("environment","Browser");
        UtilityLogs.info("myBrowser = " + System.getProperty("myBrowser"));
        DriverFactory.setupDriver(browser);
        UtilityLogs.info("EDGE Browser IS Opened");
        getDriver().get(UtilityData.getPropertyData("environment","BASE_URL"));
        UtilityLogs.info("Redirected To LoginPage");
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void validLoginTC() throws IOException
    {
        new P01LoginPage(getDriver())
                .enterUsername(USERNAME)
                .enterPassword(PASSWORD)
                .signInButton();
        Assert.assertTrue(new P01LoginPage(getDriver()).assertLoginTC(UtilityData.getPropertyData("environment","HOME_URL")));
//        Assert.assertEquals(getDriver().getCurrentUrl(),UtilityData.getPropertyData("environments","home_url"));
        //Omar
    }

    @AfterMethod
    public  void quit()
    {
        quitDriver();
        //DriverFactory.quitDriver(); //both right to implement static method
    }

}
