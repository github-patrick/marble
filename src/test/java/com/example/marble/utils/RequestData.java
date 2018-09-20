package com.example.marble.utils;

import io.restassured.specification.RequestSpecification;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RequestData {
    private RequestSpecification request;
}
