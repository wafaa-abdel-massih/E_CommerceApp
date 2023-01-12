Feature: the user should be able to register successfully

  #SC1- User could register with valid data
  Scenario: user register with valid data
    Given user navigates to register page
    When user enter valid data
    And user click on register button
    Then user could register successfully