package com.example.marble.bdd.api.steps;

import com.example.marble.controllers.UniversityApiController;
import com.example.marble.domain.University;
import com.example.marble.domain.dtos.UniversityDto;
import com.example.marble.mappers.UniversityMapper;
import com.example.marble.utils.RequestData;
import com.example.marble.utils.ResponseData;
import com.example.marble.utils.helper.UniversityHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class UniversitySteps {


    @Autowired
    private RequestData requestData;

    @Autowired
    private ResponseData responseData;

    @Autowired
    private UniversityHelper universityHelper;

    @Autowired
    private UniversityMapper universityMapper;

    private String universityName;


    @Given("^I am on the university end point$")
    public void i_am_on_the_university_end_point() throws Throwable {
        RestAssured.basePath = UniversityApiController.RESOURCE_PATH;
        requestData.setRequest(given().log().all());
    }

    @Given("^I have a university name of \"([^\"]*)\"$")
    public void i_have_a_university_name_of(String name) throws Throwable {
        universityName = name;
    }

    @Given("^I change the university name to \"([^\"]*)\"$")
    public void i_change_the_university_name_to(String name) throws Throwable {
        universityName = name;
    }

    @When("^I set the university path param as (\\d+)$")
    public void i_set_the_university_path_param_as(int universityId) throws Throwable {
        requestData.getRequest().pathParam("universityId", universityId);
    }

    @When("^I make a request to post the university$")
    public void i_make_a_request_to_post_the_university() throws Throwable {
       responseData.setResponse(requestData.getRequest().body(setAndReturnBody()).post());
    }



    @When("^I make a request to get a university$")
    public void i_make_a_request_to_get_a_university() throws Throwable {
        responseData.setResponse(requestData.getRequest().
                            when().
                                get("{universityId}"));
    }

    @When("^I make a request to get all universities$")
    public void i_make_a_request_to_get_all_universities() throws Throwable {
        responseData.setResponse(requestData.getRequest().
                when().
                    get());
    }


    @When("^I make a request to update the university$")
    public void i_make_a_request_to_update_the_university() throws Throwable {
        University university = universityHelper.getFirstUniversity();
        UniversityDto universityDto = universityMapper.map(university);

        universityDto.setName(universityName);
        responseData.setResponse(requestData.getRequest().
                given().
                    body(universityDto).
                when().
                    put("{universityId}",university.getId()));
    }

    @Then("^I should see the university id as (\\d+) in the response$")
    public void i_should_see_the_university_id_as_in_the_response(int arg1) throws Throwable {

        University university = responseData.getResponse().as(University.class);
        assertEquals(1, university.getId().intValue());
    }


    @Then("^I should see the university name as \"([^\"]*)\" in the response$")
    public void i_should_see_the_university_name_as_in_the_response(String universityName) throws Throwable {
        University university = responseData.getResponse().as(University.class);
        assertEquals(universityName,university.getName());
    }

    @Then("^I should have (\\d+) universities in the response$")
    public void i_should_have_universities_in_the_response(int size) throws Throwable {
        List<University> universityList = responseData.getResponse().as(List.class);
        assertEquals(size, universityList.size() );
    }

    private Map<String,String> setAndReturnBody() {
        Map<String,String> body = new HashMap<>();
        body.put("name", universityName);
        requestData.getRequest().body(body);
        return body;
    }
}
