package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentManager {
    private static Properties properties = new Properties();

    static {
        String env = System.getProperty("env", "prod");
        String configFilePath = "src/test/resources/properties/" + env + ".config.properties";

        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Configuration file could not be loaded: " + configFilePath, e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
