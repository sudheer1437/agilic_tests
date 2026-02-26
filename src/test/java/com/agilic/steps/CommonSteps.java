package com.agilic.steps;

import com.agilic.pages.CommonPage;
import com.microsoft.playwright.Locator;
import net.thucydides.core.steps.ScenarioSteps;

public class CommonSteps extends ScenarioSteps {

    CommonPage commonPage = new CommonPage();
    public void enterDataInField(String text, String fieldName) {
        Locator locator = commonPage.getFieldLocator(fieldName);
        locator.first().fill(text);
    }
}
