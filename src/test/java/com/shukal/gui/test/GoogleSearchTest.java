package com.shukal.gui.test;

import com.shukal.gui.pages.GoogleStartPage;
import com.shukal.gui.pages.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GoogleSearchTest extends AbstractTest {

    @Test(description = "Logo, seach input, search btn, im lucky btn are present.")
    public void testGoogleSearchPageEmptyState() {
        SoftAssert softAssert = new SoftAssert();
        GoogleStartPage googleStartPage = openStartPage();
        googleStartPage.validateBaseElements(softAssert);
        softAssert.assertAll();
    }

    @Test(description = "User is able to search google start page")
    public void testGoogleSearch() {
        GoogleStartPage googleStartPage = openStartPage();
        SearchResultsPage searchResultsPage = googleStartPage.searchFor(SEARCH_TERM);
        Assert.assertTrue(searchResultsPage.isPageOpened(), "Search results page is not opened!");

    }
}
