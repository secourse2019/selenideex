package com.shukal.util;

import com.codeborne.selenide.SelenideElement;
import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WaitUtil {
    public static final int WAIT_TIMEOUT_SEC = 30;
    public static final int WAIT_POLL_INTERVAL_MS = 1000;

    public static synchronized void waitForElementsTextContains(final WebDriver browser, int timeoutSec, int pollIntervalMS,
                                                                final SelenideElement element, final String key, final boolean refreshPage) {
        try {
            new WebDriverWait(browser, timeoutSec, pollIntervalMS).ignoring(StaleElementReferenceException.class)
                    .until(new Function<WebDriver, Boolean>() {
                        @Override
                        public Boolean apply(WebDriver input) {
                            if (refreshPage)
                                browser.navigate().refresh();
                            return (element.getText().toLowerCase().contains(key.toLowerCase()));
                        }
                    });
        } catch (TimeoutException e) {
            throw new RuntimeException(String.format("Element's text '%s' doesn't contains key %s after %d sec waiting", element.getText(),
                    key, WAIT_TIMEOUT_SEC));
        }
    }

    public static synchronized void waitForElementsContainsAnyText(final WebDriver browser, int timeoutSec, int pollIntervalMS,
                                                                   final SelenideElement element, final boolean refreshPage) {
        try {
            new WebDriverWait(browser, timeoutSec, pollIntervalMS).ignoring(StaleElementReferenceException.class)
                    .until(new Function<WebDriver, Boolean>() {
                        @Override
                        public Boolean apply(WebDriver input) {
                            if (refreshPage)
                                browser.navigate().refresh();
                            return (element.getText().length() > 0);
                        }
                    });
        } catch (TimeoutException e) {
            throw new RuntimeException(String.format("Element's text '%s' doesn't contains any text after %d sec waiting", element.getText(), WAIT_TIMEOUT_SEC));
        }
    }

    public static synchronized void waitForElementsTextContains(final WebDriver browser, final SelenideElement element, final String key,
                                                                final boolean refreshPage) {
        waitForElementsTextContains(browser, WAIT_TIMEOUT_SEC, WAIT_POLL_INTERVAL_MS, element, key, refreshPage);
    }

    public static synchronized void waitForElementTextNotEmpty(final WebDriver browser, int timeoutSec, int pollIntervalMS,
                                                               final SelenideElement element) {
        try {
            new WebDriverWait(browser, timeoutSec, pollIntervalMS).ignoring(StaleElementReferenceException.class)
                    .until(new Function<WebDriver, Boolean>() {
                        @Override
                        public Boolean apply(WebDriver input) {
                            return (element.isDisplayed() && !"".equals(element.getText()));
                        }
                    });
        } catch (TimeoutException e) {
            throw new RuntimeException(
                    String.format("Element's '%s' text is still empty after %d sec waiting", element.name(), WAIT_TIMEOUT_SEC));
        }
    }



    public static synchronized void waitForElementPresent(final WebDriver driver, final SelenideElement element) {
        try {
            new WebDriverWait(driver, WAIT_TIMEOUT_SEC, WAIT_POLL_INTERVAL_MS).ignoring(StaleElementReferenceException.class)
                    .until(new Function<WebDriver, Boolean>() {
                        @Override
                        public Boolean apply(WebDriver input) {
                            return (element.getWrappedElement().isDisplayed() && element.getWrappedElement().isEnabled());
                        }
                    });
        } catch (TimeoutException e) {
            throw new RuntimeException(
                    String.format("Can't find Element '%s' on page after %d sec waiting", element.name(), WAIT_TIMEOUT_SEC));
        }
    }

    public static synchronized void waitForElementsListNotEmpty(final WebDriver driver, final List<? extends SelenideElement> elements) {
        waitForElementsListNotEmpty(driver, elements, WAIT_TIMEOUT_SEC);
    }

    public static synchronized void waitForElementsListNotEmpty(final WebDriver driver, final List<? extends SelenideElement> elements, int waitTimeoutSec) {
        try {
            new WebDriverWait(driver, waitTimeoutSec, WAIT_POLL_INTERVAL_MS).ignoring(WebDriverException.class)
                    .until(new Function<WebDriver, Boolean>() {
                        @Override
                        public Boolean apply(WebDriver input) {
                            return elements.size() > 0;
                        }
                    });
        } catch (TimeoutException e) {
            throw new RuntimeException(String.format("Elements list doesn't contain any visible elements"));
        }
    }
}
