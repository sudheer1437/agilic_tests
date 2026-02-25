Feature: Login Authentication

  @login @smoke
  Scenario Outline: Successful login with valid credentials
    Given user navigates to Agilic Login Page
    When user signs in with "<email>" and password "<password>"
    And user clicks on SignIn button
    Then login result should be "<result>"

  Examples:
    | email                    | password        | result                |
    | vobawe9654@deposin.com   | Train_88        | success               |
    | wrong@test.com           | 123456          | invalid_credentials   |
    |                          | 123456          | email_required        |
    | vobawe9654@deposin.com   |                 | password_required     |

  @login
  Scenario: Login using Enter Key
    Given user navigates to Agilic Login Page
    When user signs in with "vobawe9654@deposin.com " and password "Train_88"
    And user presses enter button
    Then user should be navigated to Summary Page

  @security
  Scenario: Unverified user cannot signin
    Given user navigates to Agilic Login Page
    When user signs in with "agilictest12345@gmail.com" and password "Train_88"
    And user clicks on SignIn button
    Then user should see email not verified message
    And user should remain on login page

  @links
  Scenario: User can read "Terms and Conditions" section
    Given user navigates to Agilic Login Page
    When user clicks on "Terms & Conditions" link
    Then "Terms & Conditions" modal should be displayed
    When user clicks on Cancel button in modal
    Then "Terms & Conditions" modal should be closed
    And user should see Sign In button

  @links
  Scenario: User can read "Privacy Policy" section
    Given user navigates to Agilic Login Page
    When user clicks on "Privacy Policy" link
    Then "Privacy Policy" modal should be displayed
    When user clicks on Cancel button in modal
    Then "Privacy Policy" modal should be closed
    And user should see Sign In button

  @links
  Scenario: User can read "Read me" section
    Given user navigates to Agilic Login Page
    When user clicks on "Read me" link
    Then "Read me" modal should be displayed
    When user clicks on Cancel button in modal
    Then "Read me" modal should be closed
    And user should see Sign In button

  @links
  Scenario: User can read "Do Not Share Personal Information" section
    Given user navigates to Agilic Login Page
    When user clicks on "Do Not Share Personal Information" link
    Then "Do Not Share Personal Information" modal should be displayed
    When user clicks on Cancel button in modal
    Then "Do Not Share Personal Information" modal should be closed
    And user should see Sign In button