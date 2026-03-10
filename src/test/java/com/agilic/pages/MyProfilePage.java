package com.agilic.pages;

import com.microsoft.playwright.Locator;

public class MyProfilePage extends AgilicHomePage{
    public Locator myProfileTab;
    public Locator myProfileButton;


    public MyProfilePage() {
        super();
        this.myProfileTab = page.locator("p-tabmenu.p-element");
        this.myProfileButton = page.locator("button:has(img[src*='profile-icon.svg'])");
    }

    public boolean checkForTab(String tabName) {
        System.out.println("Checking for tab: " + myProfileTab.getByText(tabName).textContent());
        return myProfileTab.getByText(tabName).isVisible();
    }

    public void clickOnElement(String elementName) {
        if (elementName.equalsIgnoreCase("My Profile Button")) {
            myProfileButton.click();
        }
    }
}
