@UniversityApi
Feature: As a client
  I want to create and retrieve universities
  So that I can be in the position to recruit teachers


  Background:
    Given I am on the university end point

    @High
  Scenario: A client can add a university to the database
    Given I set the request header "Accept" as "application/json"
    And I set the request header "Content-Type" as "application/json"
    And I have a university name of "Cambridge"
    When I make a request to post the user login details
    Then I should see the response code is 201
    And I should see the university name as "Cambridge" in the response


    @Medium
  Scenario: A client cannot add a duplicate university to the database
    Given I set the request header "Accept" as "application/json"
    And I set the request header "Content-Type" as "application/json"
    And I have a university name of "Cambridge"
    When I make a request to post the user login details
    Then I should see the response code is 201
    When I make a request to post the user login details
    Then I should see the response code is 400

      
   Scenario: A client cannot find a non existent university
     Given I set the request header "Accept" as "application/json"
     And I set the request header "Content-Type" as "application/json"
     And I have a university name of "Cambridge"
     When I make a request to post the user login details
     Given I set the university path param as 2344
     When I make a request to get a university
     Then I should see the response code is 404
     
     
     
     

