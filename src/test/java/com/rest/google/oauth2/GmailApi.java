package com.rest.google.oauth2;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Base64;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GmailApi {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;
    String access_token = "ya29.a0Ad52N39TAfW0gDp-sX-mBwW_miUL-VCnLOcsNhLID3KT4ufs1Qcg8DKXhb7XU6O6-zYjk7lSSeB5_-bU1rcnWeEwOfITz2EHw-sIWqm1tp99K5tjXYAEon0eaSf-SY-wFuTRJuR0n0wt9dvpRNgaFAXPCN50KGkAuNNX3QaCgYKAYgSARMSFQHGX2MiOvZ6qYRgtixVnRt9NqVjRg0173";

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://gmail.googleapis.com").
                addHeader("Authorization", "Bearer " + access_token).
                //        setConfig(config.encoderConfig(EncoderConfig.encoderConfig()
                //                .appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                        setContentType(ContentType.JSON).
                        log(LogDetail.ALL);

        requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void getUserProfile(){
        given(requestSpecification).
                basePath("/gmail/v1").
                pathParam("userid", "restapiemre@gmail.com").
        when().
                get("/users/{userid}/profile").
        then().spec(responseSpecification);
    }

    @Test
    public void sendMessage(){
        String msg = "From: askomdch2@gmail.com\n" +
                "To: askomdch@gmail.com\n" +
                "Subject: Rest Assured Test Email\n" +
                "\n" +
                "Sending from Rest Assured";

        String base64UrlEncodedMsg = Base64.getUrlEncoder().encodeToString(msg.getBytes());

        HashMap<String, String> payload = new HashMap<>();
        payload.put("raw", base64UrlEncodedMsg);

        given(requestSpecification).
                basePath("/gmail/v1").
                pathParam("userid", "askomdch2@gmail.com").
                body(payload).
        when().
                post("/users/{userid}/messages/send").
        then().spec(responseSpecification);
    }
}
