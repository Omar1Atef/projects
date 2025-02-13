package Pages;

import Utilities.Utility;
import Utilities.UtilityLogs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P05OverviewPage
{
    private final WebDriver driver;


    public P05OverviewPage(WebDriver driver)
    {
        this.driver = driver;
    }

    private final By totalPriceWithNoTaxLocator = By.className("summary_subtotal_label");
    private final By taxLocator = By.className("summary_tax_label");
    private final By totalPriceWithTaxLocator = By.className("summary_total_label");
    private final By finishButtonLocator = By.id("finish");
    private final By cancelButtonLocator = By.id("cancel");

    static float totalPriceWithNoTax=0;
    static float Taxes=0;
    static float totalPriceWithTax=0;


    public float getTotalPriceWithNoTax()
    {
        String priceTotalText = Utility.getText(driver,totalPriceWithNoTaxLocator);
        totalPriceWithNoTax = Float.parseFloat(priceTotalText.replace("Item total: $",""));
//        UtilityLogs.info("Total Price Of Products With No Taxes = " + totalPriceWithNoTax);
        return totalPriceWithNoTax;
    }

    public float getTaxes()
    {
        String priceTotalText = Utility.getText(driver,taxLocator);
        Taxes = Float.parseFloat(priceTotalText.replace("Tax: $",""));
//        UtilityLogs.info("Total Taxes = " + Taxes);
        return Taxes;
    }

    public float getTotalPriceIncludingTax()
    {
        String priceTotalText = Utility.getText(driver,totalPriceWithTaxLocator);
        totalPriceWithTax = Float.parseFloat(priceTotalText.replace("Total: $",""));
        UtilityLogs.info("Actual Total Price Includes Taxes = " + totalPriceWithTax);
        return totalPriceWithTax;
    }

    public String calculateTotalPrice()
    {
        UtilityLogs.info("Calculated Total Price = " + (getTotalPriceWithNoTax() + getTaxes()));
        return String.valueOf(getTotalPriceWithNoTax() + getTaxes());
    }

    public boolean assertComparingPrices()
    {

        return calculateTotalPrice().equals(String.valueOf(getTotalPriceIncludingTax()));
    }

    public P06FinishingOrderPage clickOnFinishButton()
    {
        Utility.clickOnElements(driver,finishButtonLocator);
        return new P06FinishingOrderPage(driver);
    }

    public P04CheckoutPage clickOnCancelButton()
    {
        Utility.clickOnElements(driver,cancelButtonLocator);
        return new P04CheckoutPage(driver);
    }


}
