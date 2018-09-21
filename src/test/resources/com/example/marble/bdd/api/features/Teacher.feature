@TeacherApi
Feature: As a client
  I want to create and retrieve teachers
  So that I can be in the position to teach students


  Background:
    Given I am on the teachers end point
    And I have 2 universities

    @High
    Scenario: A client can create a teacher under a university
      Given I set the request header "Accept" as "application/json"
      And I set the request header "Content-Type" as "application/json"
      And I have a teacher first name of "Deborah"
      And I have a teacher last name of "Smith"
      And I have a teacher start date
      When I make a request to post the teacher
      Then I should see the response code is 201

     @High
    Scenario: A client can retrieve a teacher under a university
       Given I have 1 teachers
       And I set the request header "Accept" as "application/json"
      And I set the request header "Content-Type" as "application/json"
      When I make a request to get a teacher under a university
      Then I should see the response code is 200

    Scenario: A client cannot retrieve a teacher under a university that it is not associated with
      Given I have 1 teachers
      And I set the request header "Accept" as "application/json"
      And I set the request header "Content-Type" as "application/json"
      And I set the teacher path param as 2344
      When I make a request to get a teacher
      Then I should see the response code is 404


    Scenario: A client can retrieve all teachers under a university
      Given I have 3 teachers
      And I set the request header "Accept" as "application/json"
      And I set the request header "Content-Type" as "application/json"
      When I make a request to get all teachers
      Then I should see the response code is 200
      And I should have 3 teachers in the response


  @Medium
  Scenario: A client can update a teacher
    Given I have 1 teachers
    And I set the request header "Accept" as "application/json"
    And I set the request header "Content-Type" as "application/json"
    And I change the teacher last name to "Taylor"
    When I make a request to update the teacher
    Then I should see the response code is 204
   And I should see the teacher last name as "Taylor" in the response













