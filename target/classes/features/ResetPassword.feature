Feature: User could reset his/her password successfully

  #SC3- User could reset his/her password successfully
  Scenario: user could reset password successfully
    Given user open browser
    And user navigates to login page
    When user click on forget password link
    And user enter valid email
    And user click on recover button
    Then user receive an email to reset password successfully