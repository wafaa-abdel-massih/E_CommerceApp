Feature: Logged User could search for any product

  #SC4- Logged User could search for any product
  Scenario: Logged User could search for any product
    Given user logged successfully
    And user enter product name in search box
    And user click on search button
    Then searched product should be displayed

