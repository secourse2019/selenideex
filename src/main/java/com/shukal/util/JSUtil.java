package com.shukal.util;

import com.codeborne.selenide.SelenideElement;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;


public class JSUtil {
    private static Logger LOGGER = Logger.getLogger(JSUtil.class);

    public static void hoverWithJS(WebDriver driver, SelenideElement el2hover) {
        String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
        ((JavascriptExecutor) driver).executeScript(mouseOverScript, el2hover.getWrappedElement());
        LOGGER.info("Hover with JS was successfully completed");
    }

    public static void scroll2element(WebDriver driver, SelenideElement el2scroll) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true); return window.scrollY;", el2scroll.getWrappedElement()).toString();
        LOGGER.info("Scrolling with JS was successfully completed");
    }

    public static void scroll2TheBottom(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("scroll(0,document.body.scrollHeight);");
        LOGGER.info("Scrolling to the bottom with JS was successfully completed");
    }

    public static void click2Element(WebDriver driver, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
        LOGGER.info("Click with JS was successfully completed");

    }

    public static void scroll2TheTop(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("scroll(0, -250);");
    }
}
