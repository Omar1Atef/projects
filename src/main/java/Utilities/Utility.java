package Utilities;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Utility
{

    public static WebElement byToWebElement (WebDriver driver,By locator)
    {
        return driver.findElement(locator);
    }


    public static void clickOnElements(WebDriver driver, By locator)
    {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }


    public static void sendData(WebDriver driver, By locator, String text)
    {
        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).sendKeys(text);
    }


    public static String getText(WebDriver driver, By locator)
    {
        new WebDriverWait(driver,Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator).getText();
    }

    public static WebDriverWait generalWait(WebDriver driver)
    {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }


    public static void scrolling(WebDriver driver,By locator)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",byToWebElement(driver,locator));

        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.not(ExpectedConditions.attributeToBeNotEmpty(byToWebElement(driver,locator),"disable")));
    }

    public static String getTimeStamp()
    {
        return new SimpleDateFormat("yyyy-MM-dd-h-m-ssa").format(new Date());
    }

    private static final String screenshot_Path = "TestOutputs/Screenshots/";

    public static void takingScreenShots(WebDriver driver,String screenshotName)
    {
        try
        {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File target = new File(screenshot_Path + screenshotName + "-" + getTimeStamp() + ".png");
            FileUtils.copyFile(src,target);

            Allure.addAttachment(screenshotName, new FileInputStream(target.getPath())); //to add it allure report
        }
        catch (Exception e)
        {
            UtilityLogs.error(e.getMessage());
        }

    }

    public static void takingFullScreenShots(WebDriver driver,By locator)
    {
        try
        {
            Shutterbug.shootPage(driver, Capture.FULL_SCROLL)
                    .highlight(byToWebElement(driver,locator))
                    .save(screenshot_Path);

        }
        catch (Exception e)
        {
            UtilityLogs.error(e.getMessage());
        }
    }

    public static int generateRandomNumbers(int maxValue) //range from 0 to maxValue
    {
        return new Random().nextInt(maxValue)+1;
    }

    public static int generateRandomNumbersInRange(int minValue,int maxValue)//range from minValue to maxValue
    {
        return new Random().nextInt(minValue,maxValue);
    }

    public static Set<Integer> generateUniqueNumbers(int noOfProductsYouWantToAddRandomly,int totalProducts)
    {
        Set<Integer> uniqueNumbers = new HashSet<>();
        while(uniqueNumbers.size() < noOfProductsYouWantToAddRandomly)
        {
            int randomNumbers = generateRandomNumbers(totalProducts);
            uniqueNumbers.add(randomNumbers);
        }
        return uniqueNumbers;
    }

    public static boolean verifyURL(WebDriver driver,String expected)
    {
        try
        {
            generalWait(driver).until(ExpectedConditions.urlToBe(expected));
        }
        catch (Exception e)
        {
            UtilityLogs.info(e.getMessage());
            return false;
        }
        return true;
    }

     public static final String logPath = "TestOutputs/Logs";

    public static File getLatestFile(String folderPath)
    {
        File folder = new File(folderPath);
        File [] arrayFiles = folder.listFiles();

        if (arrayFiles == null || arrayFiles.length == 0)
        //arrayFiles == null: This checks if the listFiles() method returned null. This can happen if the folder doesn't exist, if the path is a file (not a directory), or if there was some issue accessing the folder.
        //arrayFiles.length == 0: This checks if the arrayFiles array is empty (i.e., the directory exists but contains no files).
        {
            return null;
        }
        Arrays.sort(arrayFiles,Comparator.comparingLong(File :: lastModified).reversed());
        return arrayFiles[0];
    }


    public static Set<Cookie> getAllCookies(WebDriver driver)
    {
        return driver.manage().getCookies();
    }

    public static void restoreAllCookies(WebDriver driver , Set<Cookie> cookies)
    {
        for (Cookie c : cookies)
        {
            driver.manage().addCookie(c);
        }
    }

}
