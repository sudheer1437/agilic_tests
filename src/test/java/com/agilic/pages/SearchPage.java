package com.agilic.pages;

import com.microsoft.playwright.Locator;

public class SearchPage extends PlaywrightPageObject {

    public Locator searchBox;
    public SearchPage() {
        super();
        this.searchBox = page.locator("//textarea[@name='q']");
    }

    public void clickButton(String button) {
        page.locator("//input[@value='"+button+"']").first().click();
    }
}
