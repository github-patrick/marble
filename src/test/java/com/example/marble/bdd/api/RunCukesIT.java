package com.example.marble.bdd.api;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = "pretty",
        tags = {"@UniversityApi, @High, @Medium"},
        features = "src/test/resources/com/example/marble/bdd/api/features",
        glue = {"com.example.marble.bdd.api.steps", "com.example.marble.bdd.api.hooks"})

public class RunCukesIT {
}
