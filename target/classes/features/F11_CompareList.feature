Feature: Logged user could add different products to compare list

  #SC11- Logged user could add different products to compare list
  Scenario: Logged user could add different products to compare list
    Given user logged successfully
    And user select category
    And user select product
    When user click on add to compare list
    Then added to compare list successfully
    And user select another category
    And user select another product
    When user click on add to compare list
    Then added to compare list successfully