package com.shukal.gui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.Selenide.$;

public class GoogleStartPage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleStartPage.class);

    private SelenideElement logoImg = $(By.id("hplogo"));
    private SelenideElement searchInput = $(By.name("q"));
    private SelenideElement searchBtn = $(By.name("btnK"));
    private SelenideElement imLuckyBtn = $(By.name("btnI"));


    public GoogleStartPage inputSearchTerm(String text) {
        LOGGER.info("Type: ".concat(text));
        searchInput.val(text);
        return this;
    }

    public void submitSearch() {
        LOGGER.info("Will start search.");
        searchInput.pressEnter();
    }

    public SearchResultsPage searchFor(String text) {
        if (text.isEmpty()) {
            throw new RuntimeException("If you want to use empty search term, see `submitSearch()` method");
        }
        inputSearchTerm(text);
        submitSearch();
        return new SearchResultsPage();
    }

    public void validateBaseElements(SoftAssert sa) {
        LOGGER.info("Will validate base page elements...");
        sa.assertTrue(logoImg.isDisplayed(), "Logo image is not present on page.");
        sa.assertTrue(searchInput.isDisplayed(), "Search input is not present on page.");
        sa.assertTrue(searchBtn.exists(), "Search button is not present on page.");
        sa.assertTrue(imLuckyBtn.exists(), "I'm lucky button is not present on page.");
    }


    @Override
    public boolean isPageOpened() {
        return searchInput.isDisplayed();
    }
}
