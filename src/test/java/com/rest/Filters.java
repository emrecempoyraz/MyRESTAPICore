package com.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.given;

public class Filters {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;


    // Tüm testlerde kullanabilmek için
    @BeforeClass
    public void beforeClass() throws FileNotFoundException {
        PrintStream fileOutPutStream = new PrintStream(new File("restAssured.log"));

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                addFilter(new RequestLoggingFilter(fileOutPutStream)).
                addFilter(new ResponseLoggingFilter(fileOutPutStream));
        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void loggingFilter() throws FileNotFoundException {
        given(requestSpecification).
                baseUri("https://postman-echo.com").
                log().all().
        when().
                get("/get").
        then().spec(responseSpecification).
                log().all().
                assertThat().
                statusCode(200);
    }
}
