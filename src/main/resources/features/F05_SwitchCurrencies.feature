Feature: Logged User could switch between currencies US-Euro

  #SC5- Logged User could switch between currencies US-Euro
  Scenario: Logged User could switch between currencies US-Euro
    Given user logged successfully
    And user select US-EURO
    And user enter product name in search box
    And user click on search button
    Then € or $ sign based on selected currency should be displayed next to price