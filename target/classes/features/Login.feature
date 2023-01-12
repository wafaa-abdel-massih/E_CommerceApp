Feature: the user should be able to login successfully

  #first test scenario 1
  Scenario: user login with valid username and password
    #prerequesities
    Given user open the browser "s" and "s"
    And user navigates to login page
    #actions
    When user enter valid username
    #And user enter valid password
    #And user click on login button
    #expected result
    Then user could login successfully
    #And user should go to home page