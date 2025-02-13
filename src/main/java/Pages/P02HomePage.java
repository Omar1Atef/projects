package Pages;

import Utilities.Utility;
import Utilities.UtilityLogs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

import static Utilities.Utility.generalWait;

public class P02HomePage
{
    private final WebDriver driver;

    private final By addToCartAllProductsLocator = By.xpath("//button[@class]");
    private final By cartItemsNumberLocator = By.className("shopping_cart_badge");
    private final By selectedItemsLocator = By.xpath("//*[text()='Remove']");
    private final By cartLocator = By.className("shopping_cart_link");
    private final By priceOFItemsLocator = By.className("inventory_item_price");
    private final By priceOFSelectedItemsLocator = By.xpath("//*[text()='Remove']/preceding-sibling::div[@class='inventory_item_price']");

    private static List<WebElement> allProducts;
    private static List<WebElement> selectedProducts;
    private static List<WebElement> pricesOfSelectedProducts;

    static float totalPrice = 0;


    public By getCartLocator()
    {
        return cartLocator;
    }

    public P02HomePage(WebDriver driver)  //Constructor
    {
        this.driver = driver;
    }


    public P02HomePage addAllItemsToCart()
    {
        allProducts = driver.findElements(addToCartAllProductsLocator); //list a3rf mnha size bta3 el products eli 3ndi
        UtilityLogs.info("Number Of All Products " + allProducts.size() );
        for(int i=1 ; i<=allProducts.size();i++)
        {
           By addToCartAllProductsDynamicLocator = By.xpath("(//button[@class])[" + i + "]");
           Utility.clickOnElements(driver,addToCartAllProductsDynamicLocator);
        }
        return this;
    }


    public P02HomePage addRandomItemsToCart(int noOfProductsYouWantToAddRandomly,int totalProducts)
    {
        Set<Integer> uniqueNumbers = Utility.generateUniqueNumbers(noOfProductsYouWantToAddRandomly,totalProducts);
        for(int randomNo : uniqueNumbers)
        {
            UtilityLogs.info("random number " + randomNo);
            By addToCartAllProductsDynamicLocator = By.xpath("(//button[@class])[" + randomNo + "]");
            Utility.clickOnElements(driver,addToCartAllProductsDynamicLocator);
        }
        return this;
    }


    public String noOfSelectedItems()
    {
        try
        {
            selectedProducts = driver.findElements(selectedItemsLocator) ; //lw m3mltsh try w catch lw mafish element selected hydeni no such element exception
            UtilityLogs.info("Number Of selectedProducts " + selectedProducts.size());
            return String.valueOf(selectedProducts.size());
        }
        catch (Exception e)
        {
            UtilityLogs.error(e.getMessage());
            return "0";
        }

    }


    public String getNumberOfItemsInCart()
    {
        try
        {
            UtilityLogs.info("Number Of Products Added in Cart " + Utility.getText(driver,cartItemsNumberLocator) );
            return Utility.getText(driver,cartItemsNumberLocator); //lw m3mltsh try w catch wmsln m3mltsh add lproduct hydeni exception no such element
        }

        catch (Exception e)
        {
            UtilityLogs.error(e.getMessage());
            return "0";
        }
    }


    public boolean assertCompareSelectedItemsWithCartNo() //assertion of homepage
    {
        return getNumberOfItemsInCart().equals(noOfSelectedItems());
    }


    public String getTotalPriceOfSelectedProductsInHomePage()
    {
        try
        {
//            totalPrice = 0;
            pricesOfSelectedProducts = driver.findElements(priceOFSelectedItemsLocator);
            for(int i=1 ; i<=pricesOfSelectedProducts.size();i++)
            {
                By priceOFSelectedItemsDynamicLocator = By.xpath("(//*[text()='Remove']/preceding-sibling::div[@class='inventory_item_price'])[" + i + "]");
                String fullText = Utility.getText(driver,priceOFSelectedItemsDynamicLocator);
                totalPrice += Float.parseFloat(fullText.replace("$",""));
            }
            UtilityLogs.info("Total Price Of Selected Products In Home Page = " + totalPrice);
            return String.valueOf(totalPrice);
        }
        catch (Exception e)
        {
            UtilityLogs.info(e.getMessage());
            return "0";
        }

    }


    public P03CartPage clickOnCartIcon()
    {
        Utility.clickOnElements(driver,cartLocator);
        return new P03CartPage(driver);
    }


    public boolean verifyCartPageUrl(String expected)
    {
        try
        {
            generalWait(driver).until(ExpectedConditions.urlToBe(expected));
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }

//    public boolean verifyCartPageUrl(String expected)
//    {
//        return driver.getCurrentUrl().equals(expected);
//    }

}
