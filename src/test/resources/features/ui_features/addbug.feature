Feature:


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
