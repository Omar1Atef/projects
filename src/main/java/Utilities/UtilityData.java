package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class UtilityData
{
    private static final String Test_Data_Path_Json = "src/test/resources/TestData/"; //Test Data Folder Path

    public static String getJsonData(String filename, String field)
    {
        try
        {
            FileReader fileReader = new FileReader(Test_Data_Path_Json + filename + ".json"); //src/test/resources/TestData/validLoginData.json
            JsonElement jsonElement = JsonParser.parseReader(fileReader);
            return jsonElement.getAsJsonObject().get(field).getAsString();
        }
        catch (Exception e)
        {
            UtilityLogs.error(e.getMessage()); // lw hsl exception hykml 3adi testcase wy7ot data b string fadi (eli hya data 8lt) flma wy3ml assertion y2oli fail bs fl meza bta3t try catch eno msh hy3ml exception hykml testcase wy3mlha fail fla5r
            return "";
        }
    }

    private static final String Test_Data_Path_Property = "src/test/resources/TestData/";

    public static String getPropertyData(String filename, String key) throws IOException
    {
        FileInputStream fileInputStream = new FileInputStream(Test_Data_Path_Property + filename + ".properties"); //src/test/resources/TestData/validLoginData.json
        Properties properties = new Properties();
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }

}

