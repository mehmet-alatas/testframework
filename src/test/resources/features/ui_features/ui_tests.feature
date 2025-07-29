@ui
Feature: Login and Bug Management for Bug Tracker Application

  @login @positive
  Scenario: Successful login with valid credentials
    Given the bug tracker application is open at "http://localhost:3000"
    When I enter "admin" as the username and "123456" as the password
    And I click the Login button
    Then I should see a Bug Tracking System
    And close the browser


  @login @negative
  Scenario: Unsuccessful login with invalid username and password
    Given the bug tracker application is open at "http://localhost:3000"
    When I enter "wrong" as the username and "wrong" as the password
    And I click the Login button
    Then I should see an authentication error message
    And I should stay on the login page
    And close the browser

  @login @negative
  Scenario: Successful login with valid username and invalid password
    Given the bug tracker application is open at "http://localhost:3000"
    When I enter "admin" as the username and "wrong" as the password
    And I click the Login button
    Then I should see an authentication error message
    And I should stay on the login page
    And close the browser

  @login @negative
  Scenario: Successful login with invalid username and valid password
    Given the bug tracker application is open at "http://localhost:3000"
    When I enter "wrong" as the username and "123456" as the password
    And I click the Login button
    Then I should see an authentication error message
    And I should stay on the login page
    And close the browser

  @login @negative
  Scenario: Login with empty username
    Given the bug tracker application is open at "http://localhost:3000"
    When I leave the username field empty and enter "123456" as the password
    And I click the Login button
    Then I should see a validation message saying "Username and password required"
    And I should stay on the login page
    And close the browser

  @login @negative
  Scenario: Login with empty password
    Given the bug tracker application is open at "http://localhost:3000"
    When I enter "admin" as the username and leave the password field empty
    And I click the Login button
    Then I should see a validation message saying "Username and password required"
    And I should stay on the login page
    And close the browser

  @login @negative
  Scenario: Login with empty username and password
    Given the bug tracker application is open at "http://localhost:3000"
    When I leave both the username and password fields empty
    And I click the Login button
    Then I should see a validation message saying "Username and password required"
    And I should stay on the login page
    And close the browser

  @login
  Scenario: Password field masking
    Given the bug tracker application is open at "http://localhost:3000"
    When I enter a password in the password field
    Then the characters in the password field should be masked (shown as dots or asterisks)
    And close the browser

  @bug_management
  Scenario: Add a bug with valid name
    Given the bug tracker application is open at "http://localhost:3000"
    And I am logged in as admin and on the bug list page
    When I enter "TestBug" as the name
    Then I should see "TestBug" in the bug list
    And close the browser


  @bug_management @negative
  Scenario: Add a bug with empty name
    Given the bug tracker application is open at "http://localhost:3000"
    And I am logged in as admin and on the bug list page
    And I enter the name field space
    Then I should not see " " in the bug list
    And close the browser


  @bug_management @debug
  Scenario: Edit a bug with a new name
    Given the bug tracker application is open at "http://localhost:3000"
    And I am logged in as admin and on the bug list page
    When I enter "TestBug" as the name
    And I click the Edit button
    And I change the name to "UpdatedBug"
    Then I should see "UpdatedBug" in the bug list
    And close the browser

  @bug_management @deleteall
  Scenario: Edit a bug with a new name
    Given the bug tracker application is open at "http://localhost:3000"
    And I am logged in as admin and on the bug list page
    When I delete all the bugs
    Then I should see empty the bug list
    And close the browser
