package com.agilic.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class SummaryPage extends AgilicHomePage {
    public Locator summaryHeader;
    public Locator loadingSpinner;
    public Locator signOut;
    public Locator myProfileAvatar;

    public Locator cardView;
    public Locator listsView;
    public Locator reportingButton;
    public Locator myProfileSummaryButton;
    public Locator settingsButton;
    public Locator summaryCards;


    public SummaryPage() {
        super();
        this.summaryHeader = page.getByText("My Summary", new Page.GetByTextOptions().setExact(true));
        this.loadingSpinner = page.getByText("Please wait...", new Page.GetByTextOptions().setExact(true));
        this.signOut = page.getByText("Sign Out", new Page.GetByTextOptions().setExact(true));
        this.myProfileAvatar = page.locator(".p-avatar");

        this.cardView = page.locator("img[src='assets/demo/images/navigator/card-icon.svg']");
        this.listsView = page.locator("img[src='assets/demo/images/navigator/tab-icon.svg']");
        this.myProfileSummaryButton = page.locator("img[src='assets/demo/images/navigator/profile-icon.svg']");
        this.reportingButton = page.locator("img[src='assets/demo/images/navigator/report-icon.svg']");
        this.settingsButton = page.locator("img[src='assets/demo/images/navigator/settings-icon.svg'']");
        this.summaryCards = page.locator("app-card-view");

    }

    private void verifyPageIdentity() {
        if (!page.url().contains("/summary")) {
            throw new IllegalStateException("Not on Summary Page");
        }

        if (!summaryHeader.isVisible()) {
            throw new IllegalStateException("Summary header not visible");
        }

        if (!sidebar.isVisible()) {
            throw new IllegalStateException("Sidebar header not visible");
        }
    }

    public void verifyPageStructure() {
        if (!myProfileAvatar.isVisible()) {
            throw new IllegalStateException("Profile avatar not visible");
        }
    }

    public void verifyUserSession() {
        if (!myProfileAvatar.isVisible()) {
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
        verifyPageIdentity();
    }

    public void clickOnSignOutButton() {
        myProfileAvatar.click();
        signOut.waitFor();
        signOut.click();
    }

    public boolean checkForCard(String cardName) {
        return summaryCards.getByText(cardName).isVisible();
    }

    public boolean checkForTab(String tabName) {
        return summaryCards.getByText(tabName).isVisible();
    }

    public void clickOnElement(String elementName) {
        if (elementName.equalsIgnoreCase("summaryLists")) {
            listsView.click();
        }
    }
}
