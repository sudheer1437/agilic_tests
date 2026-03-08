package com.agilic.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class LoginPage extends PlaywrightPageObject {

    public final Locator emailTextBox;
    public final Locator passwordTextBox;
    public final Locator signInButton;
    public final Locator invalidCredentialsMessage;
    public final Locator emailRequiredMessage;
    public final Locator passwordRequiredMessage;
    public final Locator userNotRegisteredMessage;

    public final Locator modalCancelButton;

    public final Locator termsAndConditionsLink;
    public final Locator termsAndConditionsModal;

    public final Locator readMeLink;
    public final Locator readMeModal;

    public final Locator doNotSharePersonalInformationLink;
    public final Locator doNotSharePersonalInformationModal;

    public final Locator privacyPolicyLink;
    public final Locator privacyPolicyModal;


    public LoginPage() {
        super();
        this.emailTextBox = page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("Email"));
        this.passwordTextBox = page.getByRole(AriaRole.TEXTBOX,new Page.GetByRoleOptions().setName("Password"));
        this.signInButton = page.getByRole(AriaRole.BUTTON,new Page.GetByRoleOptions().setName("Sign In"));
        this.invalidCredentialsMessage=page.locator(".p-toast-detail").filter(new Locator.FilterOptions().setHasText("auth/invalid-credential"));
        this.emailRequiredMessage=page.getByText("Email is required.",new Page.GetByTextOptions().setExact(true));
        this.passwordRequiredMessage=page.getByText("Password is required.",new Page.GetByTextOptions().setExact(true));
        this.userNotRegisteredMessage=page.getByText("User email is not verified yet!",new Page.GetByTextOptions().setExact(true));

        this.modalCancelButton=page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel"));

        this.termsAndConditionsLink=page.getByText("Terms & Conditions",new Page.GetByTextOptions().setExact(true));
        this.termsAndConditionsModal=page.getByText("Our services are currently in the Proof of Concept (PoC) phase and may not be fully developed. Use at your own risk.", new Page.GetByTextOptions().setExact(true));

        this.privacyPolicyLink=page.getByText("Privacy Policy",new Page.GetByTextOptions().setExact(true));
        this.privacyPolicyModal=page.getByText("Introduction",new Page.GetByTextOptions().setExact(true));

        this.readMeLink=page.getByText("Read me",new Page.GetByTextOptions().setExact(true));
        this.readMeModal=page.getByText("First Time User",new Page.GetByTextOptions().setExact(true));

        this.doNotSharePersonalInformationLink=page.getByText("Do Not Share Personal Information",new Page.GetByTextOptions().setExact(true));
        this.doNotSharePersonalInformationModal=page.getByText("Consumer Privacy Act (CCPA)",new Page.GetByTextOptions());
    }

    public void clickOnLink(String linkName){
        switch (linkName) {
            case "Terms & Conditions":
                termsAndConditionsLink.click();
                break;

            case "Privacy Policy":
                privacyPolicyLink.click();
                break;

            case "Read me":
                readMeLink.click();
                break;

            case "Do Not Share Personal Information":
                doNotSharePersonalInformationLink.click();
                break;

            default:
                throw new IllegalArgumentException("Link not present");
        }
    }

    public boolean checkModalVisibility(String modal){
        switch (modal) {
            case "Terms & Conditions":
                termsAndConditionsModal.waitFor();
                return termsAndConditionsModal.isVisible();

            case "Privacy Policy":
                privacyPolicyModal.waitFor();
                return privacyPolicyModal.isVisible();

            case "Read me":
                readMeModal.waitFor();
                return readMeModal.isVisible();

            case "Do Not Share Personal Information":
                doNotSharePersonalInformationModal.waitFor();
                return doNotSharePersonalInformationModal.isVisible();

            default:
                throw new IllegalArgumentException("Modal not relevant");
        }
    }

    public void clickOnButton(String button){
        switch (button) {

            case "SignIn":
                signInButton.waitFor();
                signInButton.click();

            case "Cancel":
                modalCancelButton.waitFor();
                modalCancelButton.click();


            default:
                throw new IllegalArgumentException("Irrelevant button");
        }
    }

    public boolean checkModalInVisibility(String modal){
        switch (modal) {
            case "Terms & Conditions":
                return !termsAndConditionsModal.isVisible();

            case "Privacy Policy":
                return !privacyPolicyModal.isVisible();

            case "Read me":
                return !readMeModal.isVisible();

            case "Do Not Share Personal Information":
                return !doNotSharePersonalInformationModal.isVisible();

            default:
                throw new IllegalArgumentException("Modal not relevant");
        }
    }

    public boolean isSignInButtonVisible(){
        return signInButton.isVisible();
    }

    public void enterEmail(String email){
        emailTextBox.fill(email);
    }

    public void enterPassword(String password){
        passwordTextBox.fill(password);
    }

    public void pressEnterButton(){
        passwordTextBox.press("Enter");
    }

    public void waitForInvalidCredentialMessage(){
        invalidCredentialsMessage.waitFor(waitForElementVisibility());
    }

    public void waitForEmailRequiredMessage(){
        emailRequiredMessage.waitFor(waitForElementVisibility());
    }

    public void waitForPasswordRequiredMessage(){
        passwordRequiredMessage.waitFor(waitForElementVisibility());
    }

    public String getCurrentUrl(){
        return page.url();
    }

    public void checkForUserNotRegisteredMessage(){
        userNotRegisteredMessage.waitFor(waitForElementVisibility());
    }

    public void waitTillLoginPageLoaded(){
        emailTextBox.waitFor(waitForElementVisibility());
        signInButton.waitFor(waitForElementVisibility());
    }

    private Locator.WaitForOptions waitForElementVisibility(){
        return new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE);}


}
