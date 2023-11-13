package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesFile {
    public static Properties properties = new Properties();

    public static String readProperties(String key) {
        try {
            InputStream inputStream = new FileInputStream("C:\\AutomationProject\\src\\test\\java\\config\\config.properties");
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    public static void setProperties(String key, String value) {
        try {
            OutputStream outputStream = new FileOutputStream("C:\\AutomationProject\\src\\test\\java\\config\\config.properties");
            properties.setProperty(key, value);
            properties.store(outputStream, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
