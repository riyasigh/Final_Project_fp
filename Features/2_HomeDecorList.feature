Feature: Home Decor list
  Scenario: Verify Home Decor menu items are displayed on hover
    Given the user launches the Pepperfry website
    And any popup on the page is closed
    When the user hovers over the Home Decor menu
    Then the Home Decor menu items should be captured and printed
