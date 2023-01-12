Feature: Logged User could search for any product

  #SC4- Logged User could search for any product
  Scenario: Logged User could search for any product
    Given user navigates to login page
    And user enter valid email
    And user enter valid password
    And user click on login button
    When user could login successfully
    And user enter product name in search box
    And user click on search button
    Then searched product should be displayed

