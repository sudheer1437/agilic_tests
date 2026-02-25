Feature: Signout Validation

  Background:
    Given user is already logged in with "vobawe9654@deposin.com" and password "Train_88"

  @signout @smoke
  Scenario: Successful logout
    When user signs out
    Then user should be redirected to login page

  @security  @agilicTest
  Scenario: User cannot access protected summary page after signout
    When user signs out
    And accessing summary again should be not be allowed
    Then user should be redirected to login page

  @security
  Scenario: Browser back should not restore session or navigate to summary page
    When user signs out
    And user clicks on "back" button
    Then user should be redirected to login page

  @security
  Scenario: Refresh after signout should not let user back in summary page
    When user signs out
    And user clicks on "refresh" button
    Then user should be redirected to login page

