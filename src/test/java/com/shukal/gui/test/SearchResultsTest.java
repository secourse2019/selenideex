package com.shukal.gui.test;

import com.shukal.gui.pages.SearchResultsPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchResultsTest extends AbstractTest {

    @Test(description = "Validate base search results page elements")
    public void testSearchResultsPageBaseState() {
        SoftAssert softAssert = new SoftAssert();
        SearchResultsPage searchResultsPage = openSearchResultsPage(SEARCH_TERM);
        searchResultsPage.vaildateBaseElements(softAssert);
        softAssert.assertAll();
    }
}
