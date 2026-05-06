Feature: Pepperfry Website Functionalities
  Scenario:
    Given the user opens the Pepperfry website
    And the user closes the popup if present
    When the user searches for "Bookshelves"
    And the user applies a maximum price filter of 15000 and select the brand "WoondenMood"
    And the top 3 products with their prices should be displayed
    Then the user validate that prices are less than 15000















#Feature: Pepperfry Website Functionalities
#
##  Scenario: Open Pepperfry website and close popup
##    Given I open the Pepperfry website
##    When I close the popup if present
##    Then the page title should contain "Pepperfry"
##
##  Scenario: Search for Bookshelves
##    Given I open the Pepperfry website
##    And  I close the popup if present
##    When I search for "Bookshelves"
##    Then the URL should contain "search" or "bookshelves"
#
##  Scenario: Apply Price and Brand Filter
##    Given I open the Pepperfry website
##    And I close the popup if present
##    When I search for "Bookshelves"
##    And the URL should contain "search" or "bookshelves"
##    When I apply a maximum price of 15000 and select brand "WoodenMood"
##    Then the URL should contain "15000" and "WoodenMood"
#
#  Scenario: Verify all product prices are below 15000
#    Given I open the Pepperfry website
#    And I close the popup if present
#    When I search for "Bookshelves"
#    And the URL should contain "search" or "bookshelves"
#    When I apply a maximum price of 15000 and select brand "WoodenMood"
#    Then the URL should contain "15000" and "WoodenMood"
#    And I am on the filtered search results page
#    Then each product price should be below 15000
#
#  Scenario: Fill Gift Card Form
#    Given I am on the Pepperfry homepage
#    When I go to Gift Cards and select Birthday card
#    And I fill the gift card form with valid details except receiver email
#    And I select Rs.1000 and proceed to checkout
#    Then the form should be submitted
#
#  Scenario: Capture validation error for missing receiver email
#    Given I am on the gift card checkout page with missing receiver email
#    Then I should see the error message "Receiver's Email ID Cannot Be Empty"
#
#  Scenario: Verify first product price is below 500
#    Given I am on the search results page
#    Then the first product price should be below 500
#

