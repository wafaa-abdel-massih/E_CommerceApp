Feature: Logged user could filter with color

  #SC7- Logged user could filter with color
  Scenario: Logged user could filter with color
    Given user logged successfully
    And user select Apparel > Shoes category
    When user checked in specific color
    Then product with checked specific color should be displayed