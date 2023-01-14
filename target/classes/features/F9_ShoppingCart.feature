Feature: Logged user could add different products to Shopping cart

  #SC9- Logged user could add different products to Shopping cart
  Scenario: Logged user could add different products to Shopping cart
    Given user logged successfully
    And user select category
    And user select product
    When user click on add to cart
    Then a successful message should be displayed
    And user select another category
    And user select another product
    When user click on add to cart
    Then a successful message should be displayed