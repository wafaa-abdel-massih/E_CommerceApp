Feature: Logged user could select different Categories

  #SC6- Logged user could select different Categories
  Scenario: Logged user could select different Categories
    Given user logged successfully
    And hover on random selected category
    And sub category should be displayed if found
    Then user could select category
    And hover on random selected category
    And sub category should be displayed if found
    Then user could select category

