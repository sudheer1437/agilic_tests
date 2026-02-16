package com.agilic.stepdefinitions;

import com.agilic.steps.SearchSteps;
import io.cucumber.java.en.Given;
import net.serenitybdd.annotations.Steps;

public class SearchStepDefinition {
    @Steps
    SearchSteps searchSteps;

    @Given("user opens the Google homepage")
    public void userOpensTheGoogleHomepage() {

    }

}
