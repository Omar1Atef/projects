package Pages;

import Utilities.UtilityLogs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P06FinishingOrderPage
{
    private final WebDriver driver;

    public P06FinishingOrderPage(WebDriver driver)
    {
        this.driver = driver;
    }

    private final By thanksMessageLocator = By.tagName("h2");

    public boolean assertVisibilityOfThanksMessage()
    {
        UtilityLogs.info("MSG: " + driver.findElement(thanksMessageLocator).getText());
        return driver.findElement(thanksMessageLocator).isDisplayed();
    }
}
