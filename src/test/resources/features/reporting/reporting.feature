Feature: Reporting Page Functionality Validation

  Background:
    Given user is already logged in with "venudev6@gmail.com" and password "Train_88"

  @reporting @smoke
  Scenario: Reporting Page main elements Visibility
    Given Reporting page contains the following fields
      | Field Name            |
      | Status                |
      | Kanban                |
      | Work Package Report   |
      | Item Hours            |
      | Gantt / Calendar      |

    When user clicks on Reporting button having eye icon
    Then verify My Reporting tabs