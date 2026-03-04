package com.agilic.stepdefinitions;

import com.agilic.steps.LoginSteps;
import com.agilic.steps.SummarySteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class summaryPageStepDefinition {

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



}
