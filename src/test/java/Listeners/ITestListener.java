package Listeners;

import Utilities.UtilityLogs;
import org.testng.ITestResult;

public class ITestListener implements org.testng.ITestListener
{
    public void onTestStart(ITestResult result)
    {
        UtilityLogs.info("Test Case " + result.getName() + " Started");
    }

    public void onTestSuccess(ITestResult result)
    {
        UtilityLogs.info("Test Case " + result.getName() + " Passed");
    }

    public void onTestSkipped(ITestResult result)
    {
        UtilityLogs.info("Test Case " + result.getName() + " Skipped");
    }

}
