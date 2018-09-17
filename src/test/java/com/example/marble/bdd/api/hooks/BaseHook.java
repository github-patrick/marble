package com.example.marble.bdd.api.hooks;


import com.example.marble.MarbleApplication;
import com.example.marble.repository.UniversityRepository;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = MarbleApplication.class)
@TestPropertySource("classpath:marble-domain-test.properties")
@ActiveProfiles("test")
public class BaseHook {

    @Value("${com.example.marble.bdd.api.baseuri}")
    public String baseUri;

    @LocalServerPort
    public int port;

    @Autowired
    private UniversityRepository universityRepository;


    @Before
    public void setUp() {

        universityRepository.deleteAll();

        RestAssured.port = port;
        RestAssured.baseURI = baseUri;
    }

    @After
    public void tearDown() {

    }
}
