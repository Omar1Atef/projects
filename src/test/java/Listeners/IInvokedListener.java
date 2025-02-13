package Listeners;

import Pages.P02HomePage;
import Utilities.Utility;
import Utilities.UtilityLogs;
import io.qameta.allure.Allure;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static DriverFactory.DriverFactory.getDriver;

public class IInvokedListener implements IInvokedMethodListener
{
    public void afterInvocation(IInvokedMethod method, ITestResult testResult)
    {
        File logFile = Utility.getLatestFile(Utility.logPath);
        try
        {
            assert logFile != null;
            Allure.addAttachment("Logs", Files.readString(Path.of(logFile.getPath())));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        if(testResult.getStatus() == ITestResult.FAILURE)
        {
            UtilityLogs.info("Test Case " + testResult.getName() + " Failed");
            Utility.takingScreenShots(getDriver(),testResult.getName());
            Utility.takingFullScreenShots(getDriver(),new P02HomePage(getDriver()).getCartLocator());
        }
    }
}
