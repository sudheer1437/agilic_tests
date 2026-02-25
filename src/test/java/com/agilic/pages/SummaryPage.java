package com.agilic.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import net.serenitybdd.annotations.Steps;

public class SummaryPage extends PlaywrightPageObject {
    public Locator summaryHeader;
    public Locator loadingSpinner;
    public Locator signOut;
    public Locator myProfileAvatar;


    public SummaryPage() {
        super();
        this.summaryHeader = page.getByText("My Summary", new Page.GetByTextOptions().setExact(true));
        this.loadingSpinner = page.getByText("Please wait...", new Page.GetByTextOptions().setExact(true));
        this.signOut = page.getByText("Sign Out", new Page.GetByTextOptions().setExact(true));
        this.myProfileAvatar = page.locator(".p-avatar");
    }

    public void waitTillSummaryPageLoaded() {

        summaryHeader.waitFor(
                new Locator.WaitForOptions()
                        .setState(WaitForSelectorState.VISIBLE)
        );

        if (loadingSpinner.isVisible()) {
            loadingSpinner.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
        }
    }

    public void clickOnSignOutButton(){
        myProfileAvatar.click();
        signOut.waitFor();
        signOut.click();
    }
}
