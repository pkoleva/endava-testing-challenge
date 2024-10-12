package org.pkoleva.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

import java.io.PrintStream;
import java.util.Map;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;

public class RequestHandler {
    private static final String SEPARATOR = "________________________________________________________________________";
    public static final Logger logger = LogManager.getRootLogger();
    public static final RequestSpecification spec = setRequestSpecification();

    public static RequestSpecification setRequestSpecification(){
        PrintStream logStream = IoBuilder.forLogger(logger).buildPrintStream();
        RestAssuredConfig restAssuredConfig = new RestAssuredConfig();
        LogConfig logConfig = restAssuredConfig.getLogConfig();
        logConfig
                .defaultStream(logStream)
                .enablePrettyPrinting(true);
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(ResponseLoggingFilter.logResponseTo(logStream))
                .addFilter(RequestLoggingFilter.logRequestTo(logStream))
                .setConfig(restAssuredConfig)
                .build();
    }

    public Response sendPostRequest(String endpoint, Object body, int statusCode){
        logger.info("");
        logger.info("Sending request POST {}.", endpoint);
        logger.info(SEPARATOR);
        return given().log().ifValidationFails().spec(spec).
                contentType(ContentType.JSON)
                .body(body)
                .when().post(endpoint)
                .then()
                .assertThat().statusCode(statusCode)
                .contentType(ContentType.JSON).extract().response();
    }

    public Response sendGetRequest(String endpoint, Map<String, String> parameters, int statusCode){
        logger.info("");
        logger.info("Sending request GET {}.", endpoint);
        logger.info(SEPARATOR);
        return given().spec(spec)
                .log().ifValidationFails()
                .queryParams(parameters)
                .when().get(endpoint)
                .then()
                .assertThat().statusCode(statusCode).log().everything()
                .contentType(ContentType.JSON).extract().response();
    }

    public void sendDeleteRequest(String endpoint, int statusCode){
        logger.info("");
        logger.info("Sending request DELETE {}.", endpoint);
        logger.info(SEPARATOR);
        given().spec(spec).log().everything()
                .when().delete(endpoint)
                .then().log().everything()
                .assertThat().statusCode(statusCode)
                .extract().response();
    }

}
