@e2e
Feature: Login and Bug Management for Bug Tracker Application

  Scenario: Add a bug with valid name
    Given the bug tracker application is open at "http://localhost:3000"
    And I am logged in as admin and on the bug list page
    When I enter "TestBug" as the name
    Then I should see "TestBug" in the bug list
    And close the browser


  Scenario: Retrieve all items
    Given the user sends a GET request to "/items"
    Then the response statuscode should be 200
    And the response body should be a JSON array of items
    And I should see "TestBug" in the response bug list


