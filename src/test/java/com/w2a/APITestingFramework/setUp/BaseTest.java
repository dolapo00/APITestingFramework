package com.w2a.APITestingFramework.setUp;

import com.w2a.APITestingFramework.utilities.ExcelReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    public static Properties config = new Properties();
    private FileInputStream fis;
    public static ExcelReader excel = new ExcelReader("./src/test/resources/excel/testdata.xlsx");

    @BeforeSuite
    public void setUp() {

        try {
            fis = new FileInputStream("./src/test/resources/properties/config.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            config.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        RestAssured.baseURI = config.getProperty("baseURI");
        RestAssured.basePath = config.getProperty("basePath");
    }

}
