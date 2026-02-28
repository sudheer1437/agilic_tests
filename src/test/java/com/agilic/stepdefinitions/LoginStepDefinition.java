package com.agilic.stepdefinitions;

import com.agilic.steps.BrowserSteps;
import com.agilic.steps.LoginSteps;
import com.agilic.steps.SummarySteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;

public class LoginStepDefinition {

    @Steps
    LoginSteps loginSteps;
    @Steps
    SummarySteps summarySteps;
    @Steps
    BrowserSteps browserSteps;

    @Given("user navigates to Agilic Login Page")
    public void navigatesToAgilicLoginPage(){
        loginSteps.navigateAgilicLoginPage();
    }

    @When("user signs in with {string} and password {string}")
    public void signInWith(String email, String password){
        loginSteps.loginWith(email,password);
    }

    @And("user presses enter button")
    public void pressEnterButton(){
        loginSteps.pressEnterButton();
    }

    @Then("user should be navigated to Summary Page")
    public void userShouldBeNavigatedToSummaryPage(){
        summarySteps.waitTillUserDirectedToSummaryPage();
    }

    @Then("login result should be {string}")
    public void verifyLoginResult(String result) {

        switch (result) {
            case "success":
                summarySteps.waitTillUserDirectedToSummaryPage();
                break;

            case "invalid_credentials":
                loginSteps.shouldSeeInvalidCredentialMessage();
                loginSteps.shouldRemainOnLoginPage();
                break;

            case "email_required":
                loginSteps.shouldSeeEmailRequiredMessage();
                loginSteps.shouldRemainOnLoginPage();
                break;

            case "password_required":
                loginSteps.shouldSeePasswordRequiredMessage();
                loginSteps.shouldRemainOnLoginPage();
                break;

            default:
                throw new IllegalArgumentException("Unknown result: " + result);
        }
    }

    @Then("user should see email not verified message")
    public void verifyEmailNotVerifiedMessage(){
        loginSteps.userNotRegisteredMessageTobeShown();
    }

    @And("user should remain on login page")
    public void userShouldRemainOnLoginPage(){
        loginSteps.shouldRemainOnLoginPage();
    }

    @And("user clicks on {string} button")
    public void clickOnButton(String button){
        loginSteps.clickOnButton(button);
    }

    @And("user clicks on browser's {string} button")
    public void clickOnBrowserButton(String button){
        browserSteps.clickOnBrowserButton(button);
    }

    @When("user clicks on {string} link")
    public void clickOnLink(String linkName){
        loginSteps.clickOnLink(linkName);
    }

    @Then("{string} modal should be displayed")
    public void checkModalVisibility(String modal){
        loginSteps.checkModalVisibility(modal);
    }

    @And("user should see Sign In button")
    public void userShouldSeeSignInButton(){
        loginSteps.userShouldSeeSignInButton();
    }

    @Then("{string} modal should be closed")
    public void checkModalIsClosed(String modal){
        loginSteps.checkModalIsClosed(modal);
    }
}
