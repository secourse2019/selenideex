package com.shukal.constant;

import com.shukal.manager.ContextManager;

public class ConfigContstant {
    public static final String BASE_URL = ContextManager.getInstance().getProperty("base_url");
    public static final String SELENIUM_HOST = ContextManager.getInstance().getProperty("selenium_host");
    public static final String BROWSER = ContextManager.getInstance().getProperty("browser");
    public static final String DOUBLE_COLON_SPLITTER = "::";
}
