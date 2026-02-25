package com.agilic.steps;

import com.agilic.driver.PlayWrightDriver;
import com.agilic.pages.LoginPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import net.serenitybdd.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginSteps extends ScenarioSteps {
    private final EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();

    @Steps
    LoginPage loginPage;

    public void navigateAgilicLoginPage(){
        loginPage.navigate(variables.getProperty("environments.default.webdriver.base.url"),
                new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED).setTimeout(60000));
        loginPage.waitForLoadState();
    }

    public void waitTillUserNavigatedToLoginPage(){
        loginPage.waitForLoadState();

    }

    public void loginWith(String email, String password) {
        loginPage.waitForElementToBeVisible(loginPage.emailTextBox);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
    }

    public void clickOnSignButton(){
        loginPage.clickSignInButton();
    }

    public void pressEnterButton(){
        loginPage.pressEnterButton();
    }

    public void shouldSeeInvalidCredentialMessage(){
        loginPage.waitForInvalidCredentialMessage();
    }

    public void shouldSeeEmailRequiredMessage(){
        loginPage.waitForEmailRequiredMessage();
    }

    public void shouldSeePasswordRequiredMessage(){
        loginPage.waitForPasswordRequiredMessage();
    }

    public void shouldRemainOnLoginPage(){
        loginPage.getCurrentUrl();
        assertTrue(loginPage.getCurrentUrl().contains("/auth/login"));
    }

    public void tryNavigatingToSummaryPage(){
        loginPage.navigate("https://qa.agilic4m.com/#/myview/summary/card");
        loginPage.waitTillLoginPageLoaded();

    }

    public void userNotRegisteredMessageTobeShown(){
        loginPage.checkForUserNotRegisteredMessage();
    }

    public void clickOnCancelButtonInModal(){
        loginPage.clickOnCancelButtonInModal();
    }

    public void userShouldSeeSignInButton(){
        assertTrue(loginPage.isSignInButtonVisible());
    }

    public void clickOnLink(String linkName){
        loginPage.clickOnLink(linkName);
    }

    public void checkModalVisibility(String modal){
        assertTrue(loginPage.checkModalVisibility(modal));
    }

    public void checkModalIsClosed(String modal){
        assertTrue(loginPage.checkModalInVisibility(modal));
    }
}
