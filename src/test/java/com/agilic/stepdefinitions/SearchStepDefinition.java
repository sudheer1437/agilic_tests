package com.agilic.stepdefinitions;

import com.agilic.steps.SearchSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import net.serenitybdd.annotations.Steps;

public class SearchStepDefinition {
    @Steps
    SearchSteps searchSteps;

    @Given("user opens the Google homepage")
    public void userOpensTheGoogleHomepage() {
        searchSteps.openGoogleHomePage();
    }

    @Then("user enters {string} into the search bar")
    public void userEntersIntoTheSearchBar(String searchString) {
        searchSteps.userEntersIntoTheSearchBar(searchString);
    }

    @Then("user clicks {string} button")
    public void userClicksButton(String button) {
        searchSteps.userClicksButton(button);
    }

}
