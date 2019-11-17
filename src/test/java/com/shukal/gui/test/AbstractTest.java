package com.shukal.gui.test;

import com.codeborne.selenide.Configuration;
import com.shukal.constant.CommonContstant;
import com.shukal.gui.pages.GoogleStartPage;
import com.shukal.gui.pages.SearchResultsPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AbstractTest {

    private static Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);

    protected static String SEARCH_TERM = "How to learn js";

    @BeforeMethod
    public void createDriver() {
        Configuration.baseUrl = CommonContstant.BASE_URL;
        open("/");
        LOGGER.info("Will maximize the window");
        getWebDriver().manage().window().maximize();
    }

    protected GoogleStartPage openStartPage() {
        open(CommonContstant.BASE_URL);
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
}
