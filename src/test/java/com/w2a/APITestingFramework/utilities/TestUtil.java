package com.w2a.APITestingFramework.utilities;

import com.w2a.APITestingFramework.listeners.ExtentListeners;
import org.json.JSONObject;

public class TestUtil {

    public static boolean jsonHasKey(String json, String key) {
        JSONObject jsonObject = new JSONObject(json);
        ExtentListeners.testReport.get().info("Validating the presence of key: " + key);
        return jsonObject.has(key);
    }

    public static String getJsonKeyValue(String json, String key) {
        JSONObject jsonObject = new JSONObject(json);
        ExtentListeners.testReport.get().info("Validating value of key: " + key);
        return jsonObject.get(key).toString();
    }

}
