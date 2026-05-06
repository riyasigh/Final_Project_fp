
Feature: Pepperfry Gift Card – Sender Email Validation

  Scenario Outline: Validate sender email while purchasing a gift card using excel data
    Given the user navigates to the Pepperfry home page
    And any visible popup on the home page is closed
    When the user opens gift card option
    And the user selects the birthday gift card
    And the user enters gift card details from excel row "<row_index>"
    And the user selects the 1000 denomination
    And the user clicks proceed to checkout
    Then the sender email validation message should be displayed correctly

    Examples:
      | row_index |
      | 0         |
      | 1         |
      | 2         |
