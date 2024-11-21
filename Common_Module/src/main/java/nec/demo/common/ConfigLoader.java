package nec.demo.common;

import java.io.InputStream;
import java.util.Properties;


public class ConfigLoader {
    private Properties properties = new Properties();

    public ConfigLoader() {

        String configFile = "config/" + System.getProperty("config.file", "application-dev.properties");
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFile)) {
            if (input == null) {
                throw new RuntimeException("Configuration file not found: " + configFile);
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration file: " + configFile, e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }




}
