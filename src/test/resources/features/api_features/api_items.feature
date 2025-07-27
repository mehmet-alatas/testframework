Feature: Manage Items


  Scenario: Create a new item successfully
    Given the user wants to create a new item with name "New Bug"
    When the user sends a POST request to "/items" with the name "New Bug"
    Then the response status code should be 201
    And the response body should contain the item name "New Bug"
    And the response body should contain the new item ID


  Scenario: Retrieve all items
    Given there are items in the system
    When the user sends a GET request to "/items"
    Then the response status code should be 200
    And the response body should be a JSON array of items

  Scenario: Create a new item with missing name
    Given the user wants to create a new item with an empty name
    When the user sends a POST request to "/items" with an empty name
    Then the response status code should be 400
    And the response body should contain the error "Item name required"

  Scenario: Update an existing item successfully
    Given an item with ID 1 exists in the system
    And the user wants to update the item with name "Updated Bug"
    When the user sends a PUT request to "/items/1" with the name "Updated Bug"
    Then the response status code should be 200
    And the response body should contain the updated name "Updated Bug"
    And the response body should contain the item ID 1

  Scenario: Update an item with missing name
    Given an item with ID 1 exists in the system
    And the user wants to update the item with an empty name
    When the user sends a PUT request to "/items/1" with an empty name
    Then the response status code should be 400
    And the response body should contain the error "Item name required"

  Scenario: Update a non-existing item
    Given no item exists with ID 999
    When the user sends a PUT request to "/items/999" with the name "Non-existing Item"
    Then the response status code should be 404
    And the response body should contain the error "Item not found"

  Scenario: Delete an existing item successfully
    Given an item with ID 1 exists in the system
    When the user sends a DELETE request to "/items/1"
    Then the response status code should be 200
    And the response body should contain the message "Item deleted"
    And the response body should contain the item ID 1

  Scenario: Delete a non-existing item
    Given no item exists with ID 999
    When the user sends a DELETE request to "/items/999"
    Then the response status code should be 404
    And the response body should contain the error "Item not found"
