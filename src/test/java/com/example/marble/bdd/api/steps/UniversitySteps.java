package com.example.marble.bdd.api.steps;

import com.example.marble.controllers.UniversityApiController;
import com.example.marble.domain.University;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class UniversitySteps {

    private RequestSpecification requestSpecification;
    private Response response;
    private String universityName;

    @Given("^I am on the university end point$")
    public void i_am_on_the_university_end_point() throws Throwable {
        RestAssured.basePath = UniversityApiController.RESOURCE_PATH;
        requestSpecification = given().log().all();
    }

    @Given("^I set the request header \"([^\"]*)\" as \"([^\"]*)\"$")
    public void i_set_the_request_header_as(String header, String value) throws Throwable {
        requestSpecification.header(header,value);
    }

    @Given("^I have a university name of \"([^\"]*)\"$")
    public void i_have_a_university_name_of(String name) throws Throwable {
        universityName = name;
    }

    @When("^I make a request to post the user login details$")
    public void i_make_a_request_to_post_the_user_login_details() throws Throwable {
       response = requestSpecification.body(setAndReturnBody()).post();
    }

    @When("^I set the university path param as (\\d+)$")
    public void i_set_the_university_path_param_as(int universityId) throws Throwable {
       requestSpecification.pathParam("universityId", universityId);
    }

    @When("^I make a request to get a university$")
    public void i_make_a_request_to_get_a_university() throws Throwable {
        response = requestSpecification.
                            when().
                                get("{universityId}");
    }

    @Then("^I should see the response code is (\\d+)$")
    public void i_should_see_the_response_code_is(int statusCode) throws Throwable {
        response.
                then().log().all().statusCode(statusCode);
    }

    @Then("^I should see the university id as (\\d+) in the response$")
    public void i_should_see_the_university_id_as_in_the_response(int arg1) throws Throwable {

        University university = response.as(University.class);
        assertEquals(1,university.getId().intValue());

    }

    @Then("^I should see the university name as \"([^\"]*)\" in the response$")
    public void i_should_see_the_university_name_as_in_the_response(String universityName) throws Throwable {
        University university = response.as(University.class);
        assertEquals(universityName,university.getName());
    }

    private Map<String,String> setAndReturnBody() {
        Map<String,String> body = new HashMap<>();
        body.put("name", universityName);
        requestSpecification.body(body);
        return body;
    }
}
