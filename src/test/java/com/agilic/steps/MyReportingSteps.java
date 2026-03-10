package com.agilic.steps;

import com.agilic.pages.MyReportingPage;
import net.serenitybdd.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class MyReportingSteps extends ScenarioSteps {

    @Steps
    MyReportingPage reportingPage;

    public void verifyMyReportingPageTabs(List<Map<String, String>> list) {
        for (Map<String, String> nameMap : list) {
            String tabName = nameMap.get("Field Name");
            assertTrue(reportingPage.checkForTab(tabName));

        }
    }

    public void clickReportingButton () {
        reportingPage.clickOnElement("ReportingButton");
    }
}
