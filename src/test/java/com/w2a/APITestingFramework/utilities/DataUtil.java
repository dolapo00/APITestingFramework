package com.w2a.APITestingFramework.utilities;

import com.w2a.APITestingFramework.setUp.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Hashtable;

public class DataUtil extends BaseTest {

    @Test(dataProvider = "data")
    public void validateCreateCustomerAPIWithInValidSecretKey(Hashtable<String, String> data) throws Exception {
        System.out.println(data.get("name") + "--" + data.get("email") + "--" + data.get("description"));

        if (data.get("description") == null){
            throw new Exception();
        }
    }


    @DataProvider(name = "data", parallel = true)
    public static Object[][] getData(Method method) {
//        String testDataSheet = config.getProperty("testDataSheet");

        int rows = excel.getRowCount(config.getProperty("testDataSheet"));
        System.out.println("Total rows are: " + rows);

        String testName = method.getName();
        System.out.println("Test name is: " + testName);

        int testCaseRowNum = 1;

        for (testCaseRowNum = 1;  testCaseRowNum <= rows; testCaseRowNum++) {
            String testCaseName = excel.getCellData(config.getProperty("testDataSheet"), 0, testCaseRowNum);
            if (testCaseName.equalsIgnoreCase(testName))
                break;
        }

        System.out.println("Test case starts from row num: " + testCaseRowNum);

        int dataStartRowNum = testCaseRowNum + 2;

        int testRows =  0;
        while (!excel.getCellData(config.getProperty("testDataSheet"), 0, dataStartRowNum + testRows).equals("")) {
            testRows++;
        }

        System.out.println("Total rows of data are: " + testRows);

        int columnStartColNum = testCaseRowNum + 1;
        int testColumn = 0;

        while (!excel.getCellData(config.getProperty("testDataSheet"), testColumn, columnStartColNum).equals("")) {
            testColumn++;
        }

        System.out.println("Total columns are: " + testColumn);

        Object[][] data = new Object[testRows][1];

        int i = 0;
        for (int rowNum = dataStartRowNum; rowNum < (dataStartRowNum+testRows); rowNum++) {
            Hashtable<String, String> table = new Hashtable<String, String>();

            for (int columnNum = 0; columnNum < testColumn; columnNum++) {
//                System.out.println(excel.getCellData(config.getProperty("testDataSheet"), columnNum, rowNum));
                String testData = excel.getCellData(config.getProperty("testDataSheet"), columnNum, rowNum);
                String columnName = excel.getCellData(config.getProperty("testDataSheet"), columnNum,columnStartColNum);

                table.put(columnName, testData);
            }
            data[i][0] = table;
            i++;
        }

        return data;

    }

}
