Feature: Create successful Order

  #SC12- Create successful Order
  Scenario: Create successful Order
    Given user logged successfully
    And user select category
    And user select product
    When user click on add to cart
    And user go to cart
    And user agree terms
    And  click on checkout
    And user fill billing address required data
    And user choose shipping method
    And user choose payment method
    And user continue button after check payment info
    And user confirm the order
    Then a confirm message should be displayed
