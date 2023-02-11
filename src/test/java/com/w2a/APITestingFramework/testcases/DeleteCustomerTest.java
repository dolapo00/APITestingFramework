package com.w2a.APITestingFramework.testcases;

import com.w2a.APITestingFramework.APIs.DeleteCustomerAPI;
import com.w2a.APITestingFramework.listeners.ExtentListeners;
import com.w2a.APITestingFramework.setUp.BaseTest;
import com.w2a.APITestingFramework.utilities.DataUtil;
import com.w2a.APITestingFramework.utilities.TestUtil;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class DeleteCustomerTest extends BaseTest {

    @Test(dataProviderClass = DataUtil.class, dataProvider = "data")
    public void deleteCustomer(Hashtable<String, String> data) {
        Response response = DeleteCustomerAPI.sendDeleteRequestToDeleteCustomerAPIWithValidAuthID(data);

        ExtentListeners.testReport.get().info(data.toString());

        response.prettyPrint();
        System.out.println(response.statusCode());

//        String actual_id = response.jsonPath().getString("id").toString();
//        System.out.println("Getting id from Json path: " + actual_id);
//        Assert.assertEquals(actual_id, data.get("id"), "ID not matching");

//        JSONObject jsonObject = new JSONObject(response.asString());
//        System.out.println(jsonObject.has("id"));
//        Assert.assertTrue(jsonObject.has("id"), "ID key is not present in the JSON response");

        System.out.println("Presence check for Object Key: " + TestUtil.jsonHasKey(response.asString(), "object"));
        System.out.println("Presence check for Deleted Key: " + TestUtil.jsonHasKey(response.asString(), "deleted"));

        boolean result = TestUtil.jsonHasKey(response.asString(), "id");
        Assert.assertTrue(result, "ID key is not present th the JSON response");

        String actual_id = TestUtil.getJsonKeyValue(response.asString(), "id");
        System.out.println(actual_id);
        Assert.assertEquals(actual_id, data.get("id"), "ID not matching");

        System.out.println("Object key value is: " + TestUtil.getJsonKeyValue(response.asString(), "object"));
        System.out.println("Deleted key value is: " + TestUtil.getJsonKeyValue(response.asString(), "deleted"));

        Assert.assertEquals(response.statusCode(), 200);
    }

}
