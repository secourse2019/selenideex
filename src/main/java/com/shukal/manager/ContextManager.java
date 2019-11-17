package com.shukal.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextManager {
    private static volatile ContextManager instance;
    private static Logger LOGGER = LoggerFactory.getLogger(ContextManager.class);

    private Properties prop = new Properties();

    public static ContextManager getInstance() {
        ContextManager localInstance = instance;
        if (localInstance == null) {
            synchronized (ContextManager.class) {
                localInstance = instance;
                if (localInstance == null) {
                    try {
                        instance = localInstance = new ContextManager();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        return localInstance;
    }

    private ContextManager() throws IOException {
        String propFileName = System.getProperty("user.dir") + File.separator +
                "src" + File.separator +
                "main" + File.separator +
                "resources" + File.separator + "config.properties";
        LOGGER.info("Will read from ".concat(propFileName));
        InputStream inputStream = new FileInputStream(propFileName);
        this.prop.load(inputStream);
    }

    public String getProperty(String key) {
        return this.prop.getProperty(key);
    }

}
