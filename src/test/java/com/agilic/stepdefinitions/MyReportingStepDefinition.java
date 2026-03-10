package com.agilic.stepdefinitions;

import com.agilic.steps.MyReportingSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

import java.util.List;
import java.util.Map;

public class MyReportingStepDefinition {

    @Steps
    MyReportingSteps reportingSteps;

    public List tabsList;

    @Given("Reporting page contains the following fields")
    public void reportingPageTabs(DataTable table) {
        List<Map<String, String>> list = table.asMaps();
        this.tabsList = list;
    }

    @And("verify My Reporting tabs")
    public void verifySummaryPageTabs() {
        reportingSteps.verifyMyReportingPageTabs(tabsList);
    }

    @When("user clicks on Reporting button having eye icon")
    public void clickReportingButton () {
        reportingSteps.clickReportingButton();
    }
}