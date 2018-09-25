Feature: As a client
  I want to create courses
  So that students can take them



  Background:
    Given I am on the courses end point
    And I have 1 universities
    And I have 1 teachers
    And I have 1 students


  Scenario: A client can create a course
    Given I set the request header "Accept" as "application/json"
    And I set the request header "Content-Type" as "application/json"
    And I have a course name of "Chemistry"
    And I have a course degree type of "PhD"
    When I make a request to post the course
    Then I should see the response code is 201