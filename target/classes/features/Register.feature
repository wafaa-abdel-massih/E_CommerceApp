Feature: the user should be able to register successfully

  #SC1- User could register with valid data
  Scenario: user register with valid data
    Given user open browser
    And user navigates to register page
    When user fill the required fields with valid data
    And user click on register button
    Then user could register successfully
