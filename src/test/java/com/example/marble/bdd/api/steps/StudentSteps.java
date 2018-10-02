package com.example.marble.bdd.api.steps;

import com.example.marble.controllers.StudentApiController;
import com.example.marble.domain.Address;
import com.example.marble.domain.Course;
import com.example.marble.domain.Student;
import com.example.marble.domain.Teacher;
import com.example.marble.domain.dtos.StudentDto;
import com.example.marble.mappers.StudentMapper;
import com.example.marble.utils.RequestData;
import com.example.marble.utils.ResponseData;
import com.example.marble.utils.helper.StudentHelper;
import com.example.marble.utils.helper.TeacherHelper;
import com.example.marble.utils.helper.TestUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StudentSteps {

    @Autowired
    private RequestData requestData;

    @Autowired
    private ResponseData responseData;

    @Autowired
    private TeacherHelper teacherHelper;

    @Autowired
    private StudentHelper studentHelper;

    @Autowired
    private StudentMapper studentMapper;

    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private Course course;


    @Given("^I am on the students end point$")
    public void i_am_on_the_students_end_point() throws Throwable {
        RestAssured.basePath = StudentApiController.RESOURCE_PATH;
        requestData.setRequest(given().log().all());
    }

    @Given("^I have a student first name of \"([^\"]*)\"$")
    public void i_have_a_student_first_name_of(String firstName) throws Throwable {
        this.firstName = firstName;
    }

    @Given("^I have a student last name of \"([^\"]*)\"$")
    public void i_have_a_student_last_name_of(String lastName) throws Throwable {
        this.lastName = lastName;
    }

    @Given("^I have a student email of \"([^\"]*)\"$")
    public void i_have_a_student_email_of(String email) throws Throwable {
        this.email = email;
    }

    @When("^I make a request to post the student$")
    public void i_make_a_request_to_post_the_student() throws Throwable {

        Teacher teacher = teacherHelper.getFirstTeacher();

        responseData.setResponse(requestData.getRequest().body(setAndReturnBody())

            .when()
                .post("",teacher.getId()));
    }

    @Given("^I have (\\d+) students$")
    public void i_have_students(int times) throws Throwable {
        StudentDto studentDto = null;

        for (int i=0 ; i<times ; i++) {

            studentDto = TestUtils.getDefaultStudentDto();

            given().log().all()
                    .basePath(StudentApiController.RESOURCE_PATH)
                    .body(studentDto)
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .when()
                    .post("", teacherHelper.getFirstTeacher().getId())
                    .then()
                    .log().all()
                    .statusCode(201);
        }
    }

    @Given("^I change the student last name to \"([^\"]*)\"$")
    public void i_change_the_student_last_name_to(String lastName) throws Throwable {
        this.lastName = lastName;
    }

    @Given("^I change the student email to \"([^\"]*)\"$")
    public void i_change_the_student_email_to(String email) throws Throwable {
        this.email = email;
    }

    @Given("^I change the student first name to \"([^\"]*)\"$")
    public void i_change_the_student_first_name_to(String firstName) throws Throwable {
        this.firstName = firstName;
    }

    @Given("^I change the student address to default$")
    public void i_change_the_student_address_to_default() throws Throwable {
        address = TestUtils.getDefaultAddress();
    }

    @Given("^I change the student course to default$")
    public void i_change_the_student_course_to_default() throws Throwable {
        this.course = TestUtils.getDefaultCourse();
    }

    @When("^I make a request to patch the student$")
    public void i_make_a_request_to_patch_the_student() throws Throwable {
        Teacher teacher = teacherHelper.getFirstTeacher();
        Student student = studentHelper.getFirstStudentByTeacher(teacher);

        responseData.setResponse(requestData.getRequest().
                given().
                body(setAndReturnBody()).
                when().
                patch("{teacherId}",teacher.getId(), student.getId()));

    }


    @When("^I make a request to update the student$")
    public void i_make_a_request_to_update_the_student() throws Throwable {
        Teacher teacher = teacherHelper.getFirstTeacher();
        Student student = studentHelper.getFirstStudentByTeacher(teacher);
        StudentDto studentDto = studentMapper.map(student);

        studentDto.setLastName(lastName);
        studentDto.setEmail(email);
        responseData.setResponse(requestData.getRequest().
                given().
                body(studentDto).
                when().
                put("{teacherId}",teacher.getId(), studentDto.getId()));
    }


    @When("^I make a request to get a student under a teacher$")
    public void i_make_a_request_to_get_a_student_under_a_teacher() throws Throwable {
        Teacher teacher = teacherHelper.getFirstTeacher();
        Student student = studentHelper.getFirstStudentByTeacher(teacher);
        responseData.setResponse(requestData.getRequest()
                .get("{studentId}", teacher.getId(), student.getId()));
    }

    @When("^I make a request to get all students$")
    public void i_make_a_request_to_get_all_students() throws Throwable {
        Teacher teacher = teacherHelper.getFirstTeacher();
        responseData.setResponse(requestData.getRequest()
                .get("",teacher.getId()));

    }

    @Then("^I should have (\\d+) students in the response$")
    public void i_should_have_students_in_the_response(int size) throws Throwable {
        List<StudentDto> studentDtoList = responseData.getResponse().as(List.class);
        assertEquals(size,studentDtoList.size());

    }

    @Then("^I should see the student first name as \"([^\"]*)\" in the response$")
    public void i_should_see_the_student_first_name_as_in_the_response(String firstName) throws Throwable {
        StudentDto studentDto = responseData.getResponse().as(StudentDto.class);
        assertEquals(firstName,studentDto.getFirstName());

    }

    @Then("^I should see the student last name as \"([^\"]*)\" in the response$")
    public void i_should_see_the_student_last_name_as_in_the_response(String lastName) throws Throwable {
        StudentDto studentDto = responseData.getResponse().as(StudentDto.class);
        assertEquals(lastName,studentDto.getLastName());
    }


    private Map<String,Object> setAndReturnBody() {
        Map<String,Object> body = new HashMap<>();
        body.put("firstName", firstName);
        body.put("lastName", lastName);
        body.put("email", email);
        body.put("address", address);
        body.put("course", course);
        requestData.getRequest().body(body);
        return body;
    }
}
