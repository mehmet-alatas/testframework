#Feature: Bug Tracker Application
#
#  # Base URL is assumed to be http://localhost:3000
#
#  Background:
#    Given I open the application
#
#  Scenario: Successful login
#    When I login with username "admin" and password "123456"
#    Then I should see the bug tracking system page
#
#  Scenario: Unsuccessful login
#    When I login with username "wrong" and password "wrong"
#    Then I should see a login error message
#
#  Scenario: Add a new bug
#    Given I login with username "admin" and password "123456"
#    When I add a bug named "TestBug"
#    Then I should see a bug named "TestBug" in the list
#
#  Scenario: Edit an existing bug
#    Given I login with username "admin" and password "123456"
#    And there is a bug named "TestBug"
#    When I edit the bug "TestBug" to "UpdatedBug"
#    Then I should see a bug named "UpdatedBug" in the list
#
#  Scenario: Delete a bug
#    Given I login with username "admin" and password "123456"
#    And there is a bug named "DeleteBug"
#    When I delete the bug "DeleteBug"
#    Then I should not see a bug named "DeleteBug" in the list
#
#  Scenario: Add bug with blank name
#    Given I login with username "admin" and password "123456"
#    And I get the bug count
#    When I add a bug named ""
#    Then the bug count should remain unchanged
#
#  Scenario: Edit bug with blank name
#    Given I login with username "admin" and password "123456"
#    And there is a bug named "BlankEdit"
#    When I edit the bug "BlankEdit" to ""
#    Then the bug name should remain "BlankEdit"