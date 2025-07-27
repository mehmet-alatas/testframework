@apilogin
Feature: User Login

  Scenario: Successful login with valid credentials
    Given the user has the username "admin" and password "123456"
    When the user sends a POST request to "/login" with the username and password
    Then the response status code should be 200
    And the response body should contain the message "Login successful"
    And the response body should contain the username "admin"

  Scenario: Login attempt with invalid credentials
    Given the user has the username "admin" and password "wrongpassword"
    When the user sends a POST request to "/login" with the username and password
    Then the response status code should be 401
    And the response body should contain the error "Invalid username or password"
