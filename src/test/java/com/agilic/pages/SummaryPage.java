package com.agilic.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class SummaryPage extends PlaywrightPageObject {
    public Locator summaryHeader;
    public Locator loadingSpinner;
    public Locator signOut;
    public Locator myProfileAvatar;
    public Locator sidebar;


    public SummaryPage() {
        super();
        this.summaryHeader = page.getByText("My Summary", new Page.GetByTextOptions().setExact(true));
        this.loadingSpinner = page.getByText("Please wait...", new Page.GetByTextOptions().setExact(true));
        this.signOut = page.getByText("Sign Out", new Page.GetByTextOptions().setExact(true));
        this.myProfileAvatar = page.locator(".p-avatar");
        this.sidebar= page.locator("app-sidebar");
        verifyPageIdentity();
    }

    private void verifyPageIdentity() {
        if (!page.url().contains("/summary")) {
            throw new IllegalStateException("Not on Summary Page");
        }

        if (!summaryHeader.isVisible()) {
            throw new IllegalStateException("Summary header not visible");
        }

        if(!sidebar.isVisible()){
            throw new IllegalStateException("Sidebar header not visible");
        }
    }

    public void verifyPageStructure() {
        if (!myProfileAvatar.isVisible()) {
            throw new IllegalStateException("Profile avatar not visible");
        }
    }

    public void verifyUserSession() {
        if (!signOut.isVisible()) {
            throw new IllegalStateException("User session not active");
        }
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
