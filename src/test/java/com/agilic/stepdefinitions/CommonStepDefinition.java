package com.agilic.stepdefinitions;

import com.agilic.steps.CommonSteps;
import io.cucumber.java.en.Then;
import net.serenitybdd.annotations.Steps;

public class CommonStepDefinition {

    @Steps
    CommonSteps commonSteps;

    @Then("Enter {string} in field {string}")
    public void enterDataInField(String text, String fieldName) {
        commonSteps.enterDataInField(text, fieldName);
    }
}
