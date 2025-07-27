#Feature: Bug Tracker Application
#
#  # This feature describes behaviours of a simple bug tracking application.
#  # Each scenario focuses on one specific behaviour and uses clear user‑centric language.
#  # A common background step opens the application before each scenario.
#  # Tags are used for traceability and filtering during test runs.
#
#  Background: Open the bug tracker
#    Given the bug tracker application is open at "http://localhost:3000"
#
#  # --- Login functionality ---
#
#  @login @positive
#  Scenario: Successful login with valid credentials
#    Given I am on the login page
#    When I login with username "admin" and password "123456"
#    Then I should be redirected to the bug tracking dashboard
#    And I should see a welcome message for the admin user
#
#  @login @negative
#  Scenario Outline: Unsuccessful login with invalid credentials
#    Given I am on the login page
#    When I login with username "<username>" and password "<password>"
#    Then I should see an authentication error message
#
#    Examples:
#      | username        | password        |
#      | wrong           | wrong           |
#      | admin           | wrong           |
#      | wrong           | 123456          |
#      # blank cases
#      |                 | 123456          |
#      | admin           |                 |
#      |                 |                 |
#
#
#  @login @security
#  Scenario: Password field should mask characters
#    Given I am on the login page
#    When I type my password into the password field
#    Then the password should be masked with bullet or asterisk characters
#
#  @login @ui
#  Scenario: Forgot password link is available
#    Given I am on the login page
#    Then I should see a "Forgot Password" link
#    And the link should navigate to the password recovery page when clicked
#
#  # --- Bug creation ---
#
#  @bug @create @positive
#  Scenario: Add a new bug with a valid name
#    Given I am logged in as "admin"
#    When I add a bug named "TestBug"
#    Then the bug list should contain a bug named "TestBug"
#    And the bug should have a default status of "Open"
#    And the creation timestamp should be recorded
#
#  @bug @create @negative
#  Scenario Outline: Add bug with invalid names
#    Given I am logged in as "admin"
#    And I note the current bug count
#    When I add a bug named "<bugName>"
#    Then the bug count should remain unchanged
#    And I should see a validation error message
#
#    Examples:
#      | bugName                                                                           |
#      |                                                                                   |
#      | TestBug                                                                           |
#      | ThisIsAnExcessivelyLongBugNameThatExceedsTheMaximumAllowedLengthOfFiftyCharacters |
#
#
#  @bug @create @security
#  Scenario: Prevent cross‑site scripting when adding a bug
#    Given I am logged in as "admin"
#    When I add a bug named "<b>alert('XSS')</b>"
#    Then the application should escape the HTML and display the text as literal characters
#    And the page should not execute any injected script
#
#  @bug @create @permission
#  Scenario: Add bug while not logged in
#    Given I am not logged in
#    When I attempt to add a bug named "UnauthorizedBug"
#    Then I should be redirected to the login page
#    And the bug should not be created
#
#  # --- Bug editing ---
#
#  @bug @edit @positive
#  Scenario: Edit an existing bug name to a new unique value
#    Given I am logged in as "admin"
#    And there is a bug named "TestBug"
#    When I rename the bug "TestBug" to "UpdatedBug"
#    Then the bug list should contain a bug named "UpdatedBug"
#    And the bug list should not contain a bug named "TestBug"
#
#  @bug @edit @negative
#  Scenario Outline: Edit bug with invalid new name
#    Given I am logged in as "admin"
#    And there is a bug named "EditableBug"
#    When I rename the bug "EditableBug" to "<newName>"
#    Then the bug name should remain "EditableBug"
#    And I should see a validation error message
#
#    Examples:
#      | newName                                                                           |
#      |                                                                                   |
#      | TestBug                                                                           |
#      | ThisIsAnExcessivelyLongBugNameThatExceedsTheMaximumAllowedLengthOfFiftyCharacters |
#      | <script>malicious()</script>                                                      |
#
#  @bug @edit @permission
#  Scenario: Edit bug while not logged in
#    Given I am not logged in
#    And there is a bug named "UnauthorizedEdit"
#    When I attempt to rename the bug "UnauthorizedEdit" to "ShouldNotWork"
#    Then I should be redirected to the login page
#    And the bug name should remain "UnauthorizedEdit"
#
#  # --- Bug deletion ---
#
#  @bug @delete @positive
#  Scenario: Delete an existing bug
#    Given I am logged in as "admin"
#    And there is a bug named "DeleteBug"
#    When I delete the bug "DeleteBug"
#    Then the bug list should not contain a bug named "DeleteBug"
#    And I should see a confirmation that the bug was deleted
#
#  @bug @delete @negative
#  Scenario: Delete a non‑existent bug
#    Given I am logged in as "admin"
#    And there is no bug named "GhostBug"
#    When I attempt to delete the bug "GhostBug"
#    Then I should see an error message indicating the bug does not exist
#
#  @bug @delete @cancel
#  Scenario: Cancel deletion of a bug
#    Given I am logged in as "admin"
#    And there is a bug named "CancelableBug"
#    When I initiate deletion of the bug "CancelableBug" but cancel the action
#    Then the bug list should still contain a bug named "CancelableBug"
#
#  @bug @delete @permission
#  Scenario: Delete bug while not logged in
#    Given I am not logged in
#    And there is a bug named "ProtectedBug"
#    When I attempt to delete the bug "ProtectedBug"
#    Then I should be redirected to the login page
#    And the bug list should still contain a bug named "ProtectedBug"
#
#  # --- Bug listing and count ---
#
#  @bug @list
#  Scenario: View the bug list
#    Given I am logged in as "admin"
#    When I open the bug list page
#    Then I should see a list of all bugs sorted by creation date descending
#    And each bug entry should display its name, status, and creation date
#
#  @bug @count
#  Scenario: Verify bug count after creation and deletion
#    Given I am logged in as "admin"
#    And I note the current bug count
#    When I add a bug named "CountTestBug"
#    Then the bug count should increase by 1
#    When I delete the bug "CountTestBug"
#    Then the bug count should return to the original count
#
#  # --- Security and robustness ---
#
#  @security @bruteForce
#  Scenario: Lock account after multiple failed login attempts
#    Given I am on the login page
#    When I attempt to log in with invalid credentials five times in a row
#    Then my account should be locked
#    And I should receive a notification about the lockout
#
#  @security @timeout
#  Scenario: Session timeout after inactivity
#    Given I am logged in as "admin"
#    When I remain inactive for 15 minutes
#    Then my session should expire
#    And I should be required to log in again when I try to use the application
#
#
#
