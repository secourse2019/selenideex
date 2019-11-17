package com.shukal.gui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class SearchResultsPage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchResultsPage.class);

    private SelenideElement resultsCountLabel = $(By.id("resultStats"));
    private SelenideElement searchInput = $(By.name("q"));


    @Override
    public boolean isPageOpened() {
        return resultsCountLabel.isDisplayed() && searchInput.isDisplayed();
    }
}
