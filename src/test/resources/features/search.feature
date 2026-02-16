Feature: Google Search
    @search
    Scenario: Validate search results for a common query
        Given user opens the Google homepage
        When user enters "Cucumber BDD" into the search bar
        Then user clicks "Google Search" button