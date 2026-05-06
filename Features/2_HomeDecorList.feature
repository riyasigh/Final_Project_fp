Feature: Home Decor list
  Scenario:
    Given the user launches the Pepperfry website
    And any popup on the page is closed
    When the user hovers over the Home Decor menu
    Then the Home Decor menu items should be captured and printed
