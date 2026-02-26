package com.agilic.pages;

import com.microsoft.playwright.Locator;

public class CommonPage extends PlaywrightPageObject{
    public Locator getFieldLocator(String fieldName) {
        String fieldAttributeValue = "";
        String xpath = "";
        switch(fieldName) {
            case "Email Address":
//                fieldAttributeValue = "email";
                xpath = "//input[@id='email']";
            case "password":
                xpath = "//input[@name='password']";
                break;
            default:
                xpath = "//input[@name='" + fieldName + "']";
        }

        return page.locator(xpath) ;
    }
}
