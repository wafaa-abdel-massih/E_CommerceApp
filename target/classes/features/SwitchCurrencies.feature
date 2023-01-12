Feature: Logged User could switch between currencies US-Euro

  #SC5- Logged User could switch between currencies US-Euro
  Scenario: Logged User could switch between currencies US-Euro
    Given user navigates to login page
    And user enter valid email
    And user enter valid password
    And user click on login button
    When user could login successfully
    And user selected US-EURO
    And user enter product name in search box
    And user click on search button
    Then € sign should be displayed next to price