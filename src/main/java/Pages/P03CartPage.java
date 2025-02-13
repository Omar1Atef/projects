package Pages;

import Utilities.Utility;
import Utilities.UtilityLogs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class P03CartPage
{
    private final WebDriver driver;

    public P03CartPage(WebDriver driver)  //Constructor
    {
        this.driver = driver;
    }


    private final By priceOFSelectedItemsLocator = By.xpath("//*[text()='Remove']/preceding-sibling::div[@class='inventory_item_price']");
    private final By checkoutButtonLocator = By.id("checkout");
    private final By continueShoppingButtonLocator = By.id("continue-shopping");


    private static List<WebElement> pricesOfSelectedProducts;

    static float totalPrice = 0;

    public String getTotalPriceOfSelectedProductsInCartPage()
    {
        try
        {
            pricesOfSelectedProducts = driver.findElements(priceOFSelectedItemsLocator);
            for(int i=1 ; i<=pricesOfSelectedProducts.size();i++)
            {
                By priceOFSelectedItemsDynamicLocator = By.xpath("(//*[text()='Remove']/preceding-sibling::div[@class='inventory_item_price'])[" + i + "]");
                String fullText = Utility.getText(driver,priceOFSelectedItemsDynamicLocator);
                totalPrice += Float.parseFloat(fullText.replace("$",""));
            }
            UtilityLogs.info("Total Price Of Products In Cart Page = " + totalPrice);
            return String.valueOf(totalPrice);
        }
        catch (Exception e)
        {
            UtilityLogs.info(e.getMessage());
            return "0";
        }

    }

    public boolean assertionComparePrices(String expected) //expected eli howa hykon getTotalPriceOfSelectedProductsInHomePage()
    {
        return getTotalPriceOfSelectedProductsInCartPage().equals(expected);
    }


    public P04CheckoutPage clickOnCheckoutButton()
    {
        Utility.clickOnElements(driver,checkoutButtonLocator);
        return new P04CheckoutPage(driver);
    }

    public P02HomePage clickOnContinueShoppingButton()
    {
        Utility.clickOnElements(driver,checkoutButtonLocator);
        return new P02HomePage(driver);
    }




}
