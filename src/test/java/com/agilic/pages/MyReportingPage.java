package com.agilic.pages;

import com.microsoft.playwright.Locator;

public class MyReportingPage extends AgilicHomePage {

    public Locator reportingTab;
    public Locator reportingButton;


    public MyReportingPage() {
        super();
        this.reportingTab = page.locator("p-tabmenu.p-element");
        this.reportingButton = page.locator("button:has(img[src*='report-icon.svg'])");
    }

    public boolean checkForTab(String tabName) {
        System.out.println("Checking for tab: " + reportingTab.getByText(tabName).textContent());
        return reportingTab.getByText(tabName).isVisible();
    }

    public void clickOnElement(String elementName) {
        if (elementName.equalsIgnoreCase("ReportingButton")) {
            reportingButton.click();
        }
    }

}
