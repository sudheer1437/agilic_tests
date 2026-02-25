package com.agilic.steps;

import com.agilic.pages.SummaryPage;
import net.serenitybdd.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;


public class SummarySteps extends ScenarioSteps {


    @Steps
    SummaryPage summaryPage;

    @Steps
    LoginSteps loginSteps;

    public void waitTillUserDirectedToSummaryPage(){
        summaryPage.waitTillSummaryPageLoaded();
    }

    public void clickSignOut(){
        summaryPage.clickOnSignOutButton();
    }
}
