package Utilities;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityLogs
{
    private static final String Log_Path = "TestOutputs/Logs";

//    public final static Logger logger = LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString());  not make it like this right it inside each function to get appropriate line

    public static void trace(String message)
    {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .trace(message);
//        Allure.addAttachment("Logs",message);
    }

    public static void debug(String message)
    {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .debug(message);
//        Allure.addAttachment("Logs",message);
    }

    public static void info(String message)
    {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .info(message);
//        Allure.addAttachment("Logs",message);
    }

    public static void warn(String message)
    {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .warn(message);
//        Allure.addAttachment("Logs",message);
    }

    public static void error(String message)
    {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .error(message);
//        Allure.addAttachment("Logs",message);
    }

    public static void fatal(String message)
    {
        LogManager.getLogger(Thread.currentThread().getStackTrace()[2].toString())
                .fatal(message);
//        Allure.addAttachment("Logs",message);
    }

}
