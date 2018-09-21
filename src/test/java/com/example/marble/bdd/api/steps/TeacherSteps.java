package com.example.marble.bdd.api.steps;

import com.example.marble.domain.Teacher;
import com.example.marble.domain.dtos.TeacherDto;
import com.example.marble.domain.dtos.UniversityDto;
import com.example.marble.mappers.TeacherMapper;
import com.example.marble.utils.helper.TeacherHelper;
import com.example.marble.utils.helper.TestUtils;
import com.example.marble.utils.helper.UniversityHelper;
import com.example.marble.controllers.TeacherApiController;
import com.example.marble.controllers.UniversityApiController;
import com.example.marble.domain.University;
import com.example.marble.utils.RequestData;
import com.example.marble.utils.ResponseData;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class TeacherSteps {

    @Autowired
    private RequestData requestData;

    @Autowired
    private ResponseData responseData;

    @Autowired
    private UniversityHelper universityHelper;

    @Autowired
    private TeacherHelper teacherHelper;

    @Autowired
    private TeacherMapper teacherMapper;

    private String firstName;
    private String lastName;
    private Date startDate;

    @Given("^I am on the teachers end point$")
    public void i_am_on_the_teachers_end_point() throws Throwable {
        RestAssured.basePath = TeacherApiController.RESOURCE_PATH;
        requestData.setRequest(given().log().all());
    }

    @Given("^I have (\\d+) universities$")
    public void i_have_universities(int times) throws Throwable {

        List<String> universityNames = Arrays.asList("Newcastle University","Manchester University", "Leeds University",
                "Warwick University", "Dorchester University", "Queen Mary");

        UniversityDto universityDto = TestUtils.getDefaultUniversityDto();

        for (int i=0 ; i<times; i++) {

            universityDto.setName(universityNames.get(i));
            given().log().all()
                    .basePath(UniversityApiController.RESOURCE_PATH)
                    .body(universityDto)
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
            .when()
                    .post()
            .then()
                    .log().all()
                    .statusCode(201);
        }
    }

    @Given("^I have (\\d+) teachers$")
    public void i_have_teachers(int times) throws Throwable {

        TeacherDto teacherDto = TestUtils.getDefaultTeacherDto();

        for (int i=0 ; i<times ; i++) {
            given().log().all()
                    .basePath(TeacherApiController.RESOURCE_PATH)
                    .body(teacherDto)
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
            .when()
                    .post("", universityHelper.getFirstUniversity().getId())
            .then()
                    .log().all()
                    .statusCode(201);
        }
    }

    @Given("^I have a teacher first name of \"([^\"]*)\"$")
    public void i_have_a_teacher_first_name_of(String firstName) throws Throwable {
        this.firstName = firstName;
    }

    @Given("^I have a teacher last name of \"([^\"]*)\"$")
    public void i_have_a_teacher_last_name_of(String lastName) throws Throwable {
        this.lastName = lastName;

    }

    @Given("^I have a teacher start date$")
    public void i_have_a_teacher_start_date() throws Throwable {
        startDate = new Date();
    }


    @Given("^I set the teacher path param as (\\d+)$")
    public void i_set_the_teacher_path_param_as(int teacherId) throws Throwable {
        requestData.getRequest().pathParam("teacherId", teacherId);

    }

    @Given("^I change the teacher last name to \"([^\"]*)\"$")
    public void i_change_the_teacher_last_name_to(String lastName) throws Throwable {
        this.lastName = lastName;
    }

    @When("^I make a request to update the teacher$")
    public void i_make_a_request_to_update_the_teacher() throws Throwable {
        University university = universityHelper.getFirstUniversity();
        Teacher teacher = teacherHelper.getFirstTeacherByUniversity(university);
        TeacherDto teacherDto = teacherMapper.map(teacher);

        teacherDto.setLastName(lastName);
        responseData.setResponse(requestData.getRequest().
                given().
                    body(teacherDto).
                when().
                    put("{teacherId}",university.getId(), teacherDto.getId()));
    }


    @When("^I make a request to post the teacher$")
    public void i_make_a_request_to_post_the_teacher() throws Throwable {

        University university = universityHelper.getFirstUniversity();

        responseData.setResponse(requestData.getRequest().body(setAndReturnBody())
                .post("",university.getId()));
    }

    @When("^I make a request to get a teacher under a university$")
    public void i_make_a_request_to_get_a_teacher_under_a_university() throws Throwable {
        University university = universityHelper.getFirstUniversity();
        Teacher teacher = teacherHelper.getFirstTeacherByUniversity(university);
        responseData.setResponse(requestData.getRequest()
                .get("{teacherId}",university.getId(), teacher.getId()));
    }


    @When("^I make a request to get a teacher$")
    public void i_make_a_request_to_get_a_teacher() throws Throwable {
        University university = universityHelper.getFirstUniversity();

        responseData.setResponse(requestData.getRequest()
                .get("{teacherId} ", university.getId()));
    }

    @When("^I make a request to get all teachers$")
    public void i_make_a_request_to_get_all_teachers() throws Throwable {
        University university = universityHelper.getFirstUniversity();

        responseData.setResponse(requestData.getRequest()
                .get("", university.getId()));
    }

    @Then("^I should have (\\d+) teachers in the response$")
    public void i_should_have_teachers_in_the_response(int size) throws Throwable {
        List<TeacherDto> teacherDtoList = responseData.getResponse().as(List.class);
        assertEquals(size, teacherDtoList.size());
    }

    @Then("^I should see the teacher last name as \"([^\"]*)\" in the response$")
    public void i_should_see_the_teacher_last_name_as_in_the_response(String lastName) throws Throwable {
        Teacher teacher = teacherHelper.getFirstTeacherByUniversity(universityHelper.getFirstUniversity());
        assertEquals(lastName, teacher.getLastName());
    }


    private Map<String,String> setAndReturnBody() {
        Map<String,String> body = new HashMap<>();
        body.put("firstName", firstName);
        body.put("lastName", lastName);
        body.put("startDate", startDate.toInstant().toString());
        requestData.getRequest().body(body);
        return body;
    }
}
