Feature: Logged user could select different Categories

  #SC6- Logged user could select different Categories
  Scenario: Logged user could select different Categories
    #Given user navigates to login page
    #And user enter valid email
    #And user enter valid password
    #And user click on login button
    #When user could login successfully
    Given open website
    And hover on random selected category
    And sub category should be displayed if found
    Then user could select category
