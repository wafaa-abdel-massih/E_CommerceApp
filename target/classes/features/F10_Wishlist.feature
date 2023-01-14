Feature: Logged user could add different products to Wishlist

  #SC10- Logged user could add different products to Wishlist
  Scenario: Logged user could add different products to Wishlist
    Given user logged successfully
    And user select category
    And user select product
    When user click on add to wishlist
    Then added to wishlist successfully
    And user select another category
    And user select another product
    When user click on add to wishlist
    Then added to wishlist successfully