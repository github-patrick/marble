package com.example.marble.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CourseApiController.RESOURCE_PATH)
public class CourseApiController {

    public static final String RESOURCE_PATH = "/courses";
}
