package fr.eni.papeterie.dal;

import java.util.Properties;

public class Settings {

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(Settings.class.getResourceAsStream("settings.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
}
