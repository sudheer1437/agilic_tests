Feature: Profile Page Functionality Validation

  Background:
    Given user is already logged in with "venudev6@gmail.com" and password "Train_88"

  @profile @smoke
  Scenario: Profile Page main elements Visibility
    Given Profile page contains the following tabs
      | Tab Name            |
      | My Profile          |
      | Recent Activity     |
      | Personal Settings   |

    When user clicks on My Profile button
    Then verify My Profile page tabs