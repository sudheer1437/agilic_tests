package com.agilic.stepdefinitions;

import com.agilic.steps.MyProfileSteps;
import com.agilic.steps.MyReportingSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

import java.util.List;
import java.util.Map;

public class MyProfileStepDefinitions {

    @Steps
    MyProfileSteps myProfileSteps;

    public List tabsList;

    @Given("Profile page contains the following tabs")
    public void myProfilePageTabs(DataTable table) {
        List<Map<String, String>> list = table.asMaps();
        this.tabsList = list;
    }

    @When("user clicks on My Profile button")
    public void clickOnMyProfileButton () {
        myProfileSteps.clickOnMyProfileButton();
    }

    @And("verify My Profile page tabs")
    public void verifyMyProfilePageTabs() {
        myProfileSteps.verifyMyProfilePageTabs(tabsList);
    }


}
