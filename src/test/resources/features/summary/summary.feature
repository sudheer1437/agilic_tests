Feature: Summary Page Functionality Validation

  Background:
    Given user is already logged in with "venudev6@gmail.com" and password "Train_88"

  @summary
  Scenario: Summary Page main elements Visibility
    Given summary page contains the following fields
      | Field Name |
      | Owner/Driver |
      | Assigned |
      | Following |
      | Tagged |
      | RIDE |

    When user navigates to Summary Page
    Then Verify Summary page cards exists

    And When user clicks on Lists View
    Then Verify Summary page tabs exists

