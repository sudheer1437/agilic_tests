package com.agilic.stepdefinitions;

import com.agilic.steps.LoginSteps;
import com.agilic.steps.SummarySteps;
import com.google.common.base.Verify;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

import java.util.List;
import java.util.Map;

public class SummaryPageStepDefinition {

    public List namesList;
    @Steps
    SummarySteps summarySteps;

    @Steps
    LoginSteps loginSteps;

    @Given("user is already logged in with {string} and password {string}")
    public void signInUser(String email, String password){
        loginSteps.navigateAgilicLoginPage();
        loginSteps.loginWith(email,password);
        loginSteps.clickOnButton("SignIn");
        summarySteps.waitTillUserDirectedToSummaryPage();
    }

    @When("user signs out")
    public void signOut(){
        summarySteps.waitTillUserDirectedToSummaryPage();
        summarySteps.clickSignOut();
        loginSteps.waitTillUserNavigatedToLoginPage();
    }

    @Then("user should be redirected to login page")
    public void verifyUserIsRedirectedToLoginPage(){
        loginSteps.shouldRemainOnLoginPage();
    }

    @And("accessing summary again should be not be allowed")
    public void accessingSummaryAgainShouldBeNotBeAllowed(){
        loginSteps.tryNavigatingToSummaryPage();
    }

    @Given("summary page contains the following fields")
    public void storeSummaryFields(DataTable table){
        List<Map<String, String>> list= table.asMaps();
        this.namesList=list;
    }

    @And("Verify Summary page cards exists")
    public void verifySummaryPageCards(){
        summarySteps.verifySummaryPageCardsAndTabs(namesList,"cards");
    }

    @And("Verify Summary page tabs exists")
    public void verifySummaryPageTabs(){
        summarySteps.verifySummaryPageCardsAndTabs(namesList,"tabs");
    }

    @When("When user clicks on Lists View")
    public void clickListsView(){
        summarySteps.clickListsView();
    }

}
