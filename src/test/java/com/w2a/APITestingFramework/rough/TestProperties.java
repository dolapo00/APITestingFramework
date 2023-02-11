package com.w2a.APITestingFramework.rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

    public static void main(String[] args) throws IOException {
        Properties config = new Properties();
        FileInputStream fis = new FileInputStream("./src/test/resources/properties/config.properties");
        config.load(fis);

        System.out.println(config.getProperty("baseURI"));
        System.out.println(config.getProperty("testDataSheet"));
    }

}
