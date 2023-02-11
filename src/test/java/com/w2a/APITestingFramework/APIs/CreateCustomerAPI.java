package com.w2a.APITestingFramework.APIs;

import com.w2a.APITestingFramework.setUp.BaseTest;
import io.restassured.response.Response;

import java.util.Hashtable;

import static io.restassured.RestAssured.*;

public class CreateCustomerAPI extends BaseTest {

    public static Response sendPostRequestToCreateCustomerAPIWithValidAuthKey(Hashtable<String, String> data) {
        Response response = given()
                .auth()
                .basic(config.getProperty("validSecretKey"), "")
                .formParam("email", data.get("email"))
                .formParam("description", data.get("description"))
                .post(config.getProperty("customerAPIEndpoint"));

        return response;
    }

    public static Response sendPostRequestToCreateCustomerAPIWithInValidAuthKey(Hashtable<String, String> data) {
        Response response = given()
                .auth()
                .basic(config.getProperty("inValidSecretKey"), "")
                .formParam("email", data.get("email"))
                .formParam("description", data.get("description"))
                .post(config.getProperty("customerAPIEndpoint"));

        return response;
    }

}
