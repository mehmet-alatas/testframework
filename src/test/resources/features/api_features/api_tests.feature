@api
Feature: API Login and Manage Items

  Scenario: Successful login with valid credentials
    Given the user has the username "admin" and password "123456"
    When the user sends a POST request to "/login" with the username and password
    Then response status code should be 200
    And the response body should contain the message "Login successful"
    And the response body should contain the username "admin"

  Scenario: Login attempt with invalid credentials
    Given the user has the username "admin" and password "wrongpassword"
    When the user sends a POST request to "/login" with the username and password
    Then response status code should be 401
    And the response body should contain the error "Invalid username or password"


  Scenario: Create a new item successfully
    Given the user sends a POST request to "/items" with the name "New Bug"
    Then the response statuscode should be 201
    And the response body should contain the item name "New Bug"
    And the response body should contain the new item ID

  Scenario: Retrieve all items
    Given the user sends a GET request to "/items"
    Then the response statuscode should be 200
    And the response body should be a JSON array of items

  Scenario: Create a new item with missing name
    Given the user sends a POST request to "/items" with an empty name
    Then the response statuscode should be 400
    And the responsebody should contain the error "Item name required"

  Scenario: Update an existing item successfully
    Given the user sends a PUT request to "/items/" with the name "Updated Bug"
    Then the response statuscode should be 200
    And the response body should contain the updated name "Updated Bug"

  Scenario: Update an item with missing name
    Given the user sends a PUT request to "/items/" with an empty name
    Then the response statuscode should be 400
    And the responsebody should contain the error "Item name required"

  Scenario: Update a non-existing item
    Given the user sends a PUT request to "/items/999" with the name "Non-existing Item"
    Then the response statuscode should be 404
    And the responsebody should contain the error "Item not found"

  Scenario: Delete an existing item successfully
    Given the user sends a DELETE request to "/items/"
    Then the response statuscode should be 200
    And the responsebody should contain the message "Item deleted"

  Scenario: Delete a non-existing item
    Given the user sends a DELETE request to "/items/999"
    Then the response statuscode should be 404
    And the responsebody should contain the error "Item not found"
