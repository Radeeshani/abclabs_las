package com.healthsoft.abclabs.abclabs_las_web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfigReaderUtil {

    private Properties properties;

    public AppConfigReaderUtil() {
        this.properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}

