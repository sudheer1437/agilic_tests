package com.agilic.steps;

import com.agilic.pages.SearchPage;
import net.serenitybdd.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class SearchSteps extends ScenarioSteps {

    private final EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();

    @Steps
    SearchPage searchPage;
    public void openGoogleHomePage() {
        searchPage.navigate(variables.getProperty("environments.default.webdriver.base.url"));
        searchPage.getPage().setDefaultTimeout(90000);
    }

    public void userEntersIntoTheSearchBar(String searchString) {
        searchPage.waitForElementToBeVisible(searchPage.searchBox);
        searchPage.searchBox.fill(searchString);
    }

    public void userClicksButton(String button) {
        searchPage.clickButton(button);
    }
}
