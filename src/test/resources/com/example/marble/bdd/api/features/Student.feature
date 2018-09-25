Feature: As a client
  I want to be able to add a student
  So that they can be taught by a teacher


  Background:
    Given I am on the students end point
    And I have 1 universities
    And I have 1 teachers


    Scenario: A client can add a student to the database
      Given I set the request header "Accept" as "application/json"
      And I set the request header "Content-Type" as "application/json"
      And I have a student first name of "Matthew"
      And I have a student last name of "Shaw"
      And I have a student email of "matthew.shaw@sentiapps.com"
      When I make a request to post the student
      Then I should see the response code is 201
      And I should see the student first name as "Matthew" in the response
      And I should see the student last name as "Shaw" in the response


     Scenario: A client can get a student from the database
       Given I set the request header "Accept" as "application/json"
       And I set the request header "Content-Type" as "application/json"
       And I have 1 students
       When I make a request to get a student under a teacher
       Then I should see the response code is 200
       And I should see the student first name as "Erica" in the response


  Scenario: A client can retrieve all student under a teacher
      Given I have 1 students
      And I set the request header "Accept" as "application/json"
      And I set the request header "Content-Type" as "application/json"
      When I make a request to get all students
      Then I should see the response code is 200
      And I should have 1 students in the response

  Scenario: A client can update a student
    Given I have 1 students
    And I set the request header "Accept" as "application/json"
    And I set the request header "Content-Type" as "application/json"
    And I change the student last name to "Taylor"
    And I change the student email to "erica.taylor@sentiapps.com"
    When I make a request to update the student
    Then I should see the response code is 204







