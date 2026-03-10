package com.agilic.steps;

import com.agilic.pages.MyProfilePage;
import com.agilic.pages.MyReportingPage;
import net.serenitybdd.annotations.Steps;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class MyProfileSteps {
    @Steps
    MyProfilePage myProfilePage;

    public void verifyMyProfilePageTabs(List<Map<String, String>> list) {
        for (Map<String, String> nameMap : list) {
            String tabName = nameMap.get("Tab Name");
            assertTrue(myProfilePage.checkForTab(tabName));

        }
    }

    public void clickOnMyProfileButton () {
        myProfilePage.clickOnElement("My Profile Button");
    }
}
