package com.w2a.APITestingFramework.APIs;

import com.w2a.APITestingFramework.setUp.BaseTest;
import io.restassured.response.Response;

import java.util.Hashtable;

import static io.restassured.RestAssured.*;

public class DeleteCustomerAPI extends BaseTest {

    public static Response sendDeleteRequestToDeleteCustomerAPIWithValidAuthID(Hashtable<String, String> data) {
        Response response = given()
                .auth()
                .basic(config.getProperty("validSecretKey"), "")
                .delete(config.getProperty("customerAPIEndpoint") + "/" + data.get("id"));
        return response;
    }

}
