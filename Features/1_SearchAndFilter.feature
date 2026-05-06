Feature: Pepperfry Website Functionalities
  Scenario:
    Given the user opens the Pepperfry website
    And the user closes the popup if present
    When the user searches for "Bookshelves"
    And the user applies a maximum price filter of 15000 and select the brand "WoodenMood"
    And the top 3 products with their prices should be displayed
    Then the user validate that prices are less than 15000
