package com.shukal.constant;

import com.shukal.manager.ContextManager;

public class CommonContstant {
    public static final String BASE_URL = ContextManager.getInstance().getProperty("base_url");
}