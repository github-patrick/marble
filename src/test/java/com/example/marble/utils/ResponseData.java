package com.example.marble.utils;

import io.restassured.response.Response;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ResponseData {
    private Response response;
}
