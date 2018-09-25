package com.example.marble.bdd.api.steps;

import com.example.marble.controllers.CourseApiController;
import com.example.marble.domain.enums.DegreeType;
import com.example.marble.utils.RequestData;
import com.example.marble.utils.ResponseData;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CourseSteps {

    @Autowired
    private RequestData requestData;

    @Autowired
    private ResponseData responseData;

    private String name;
    private DegreeType degreeType;


    @Given("^I am on the courses end point$")
    public void i_am_on_the_courses_end_point() throws Throwable {
        RestAssured.basePath = CourseApiController.RESOURCE_PATH;
        requestData.setRequest(given().log().all());
    }

    @Given("^I have a course name of \"([^\"]*)\"$")
    public void i_have_a_course_name_of(String name) throws Throwable {
        this.name = name;
    }

    @Given("^I have a course degree type of \"([^\"]*)\"$")
    public void i_have_a_course_degree_type_of(String degreeType) throws Throwable {
        this.degreeType = DegreeType.valueOf(degreeType);
    }

    @When("^I make a request to post the course$")
    public void i_make_a_request_to_post_the_course() throws Throwable {
        responseData.setResponse(requestData.getRequest().body(setAndReturnBody())
                .when()
                    .post());
    }

    private Map<String,String> setAndReturnBody() {
        Map<String,String> body = new HashMap<>();
        body.put("name", name);
        body.put("degreeType", degreeType.toString());
        requestData.getRequest().body(body);
        return body;
    }
}
