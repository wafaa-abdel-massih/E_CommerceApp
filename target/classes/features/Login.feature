Feature: the user should be able to login successfully

  #SC2- User could log in with valid email and password
  Scenario: user login with valid username and password
    Given user open browser
    And user navigates to login page
    When user enter valid email
    And user enter valid password
    And user click on login button
    Then user could login successfully