package rest;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.pkoleva.rest.DataHelper;
import org.pkoleva.rest.RequestHandler;

public class BaseTest{

    protected static final RequestHandler request = new RequestHandler();
    protected static final DataHelper dataHelper = new DataHelper();

    @BeforeAll
    public static void setup(){
        String baseUrl = System.getProperty("baseUrl");
        RequestHandler.spec.baseUri(baseUrl);
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.registerParser("text/html", Parser.JSON);
    }

    @BeforeEach
    public void beforeTest(TestInfo testInfo){
        RequestHandler.logger.info(" ");
        RequestHandler.logger.info("################################################################################");
        RequestHandler.logger.info(testInfo.getTestClass().get().getName());
        RequestHandler.logger.info(testInfo.getDisplayName());
        RequestHandler.logger.info("#################################################################################");
    }
}
