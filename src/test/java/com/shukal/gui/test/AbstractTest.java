package com.shukal.gui.test;

import com.codeborne.selenide.Configuration;
import com.shukal.constant.ConfigContstant;
import com.shukal.gui.pages.GoogleStartPage;
import com.shukal.gui.pages.SearchResultsPage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AbstractTest {

    private static Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

    protected static String SEARCH_TERM = "How to learn js";

    RemoteWebDriver webDriver;

    @BeforeMethod
    public void createDriver() {
        Configuration.baseUrl = ConfigContstant.BASE_URL;
        Configuration.remote = ConfigContstant.SELENIUM_HOST;
        Configuration.browser = ConfigContstant.BROWSER;
        open("/");
        LOGGER.info("Will maximize the window");
        webDriver = (RemoteWebDriver) getWebDriver();
        webDriver.manage().window().maximize();
    }

    protected GoogleStartPage openStartPage() {
        open(ConfigContstant.BASE_URL);
        GoogleStartPage googleStartPage = new GoogleStartPage();
        Assert.assertTrue(googleStartPage.isPageOpened(), "Google start page is not opened");
        return googleStartPage;
    }

    protected SearchResultsPage openSearchResultsPage(String searchTerm) {
        GoogleStartPage googleStartPage = openStartPage();
        SearchResultsPage searchResultsPage = googleStartPage.searchFor(searchTerm);
        Assert.assertTrue(searchResultsPage.isPageOpened(), "Search Results page is not opened!");
        return searchResultsPage;
    }

    @BeforeMethod
    public void tearDown() {
        Configuration.remote = null;
    }
}
