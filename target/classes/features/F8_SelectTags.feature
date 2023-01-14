Feature: Logged user could select different tags

  #SC8- Logged user could select different tags
  Scenario: Logged user could select different tags
    Given user logged successfully
    And user select any category
    When user select different tag
    Then a text contains this specific tag should be displayed
    And user select different tag again
    Then a text contains this specific tag should be displayed