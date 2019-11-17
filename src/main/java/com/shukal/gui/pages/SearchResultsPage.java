package com.shukal.gui.pages;

import com.codeborne.selenide.SelenideElement;
import com.shukal.constant.CommonContstant;
import com.shukal.constant.PagesConstant;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchResultsPage extends AbstractPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchResultsPage.class);

    private SelenideElement resultsCountLabel = $(By.id("resultStats"));
    private SelenideElement searchInput = $(By.name("q"));
    private List<SelenideElement> resultTypeTabs = $$(By.xpath("//div[@role='tab']//span"));

    public void vaildateBaseElements(SoftAssert sa) {
        LOGGER.info("Will validate base elements...");
        sa.assertTrue(isResultStatsPresent(), "Result stats is not present.");

        List<String> expectedSearchTypes = Arrays.asList(PagesConstant.SEARCH_RESULT_TYPES
                .split(CommonContstant.DOUBLE_COLON_SPLITTER));
        List<String> actualSearchTypes = resultTypeTabs.stream().map(type -> type.getText()).collect(Collectors.toList());
        sa.assertEquals(actualSearchTypes, expectedSearchTypes);
    }

    public boolean isResultStatsPresent() {
        return resultsCountLabel.isDisplayed();
    }

    @Override
    public boolean isPageOpened() {
        return resultsCountLabel.isDisplayed() && searchInput.isDisplayed();
    }
}
