package com.example.marble.bdd.api.steps;

import com.example.marble.utils.RequestData;
import com.example.marble.utils.ResponseData;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonSteps {

    @Autowired
    private RequestData requestData;

    @Autowired
    private ResponseData responseData;

    @Given("^I set the request header \"([^\"]*)\" as \"([^\"]*)\"$")
    public void i_set_the_request_header_as(String header, String value) throws Throwable {
        requestData.getRequest().header(header,value);
    }

    @Then("^I should see the response code is (\\d+)$")
    public void i_should_see_the_response_code_is(int statusCode) throws Throwable {
        responseData.getResponse().
                then().log().all().statusCode(statusCode);
    }
}
