package com.w2a.APITestingFramework.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.Date;

public class ExtentListeners implements ITestListener {

    static Date d = new Date();
    static String fileName = "Extent_" + d.toString().replace(":", "_") + ".html";

    private static ExtentReports extentReports = ExtentManager.createInstance(System.getProperty("user.dir")+"/reports/"+fileName);
    public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<>();


    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest(result.getTestClass().getName() + "      @TestCase: " + result.getMethod().getMethodName());
        testReport.set(test);

        ITestListener.super.onTestStart(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "TEST CASE: - " + methodName.toUpperCase() + "  SKIPPED" + "</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        testReport.get().pass(markup);

        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testReport.get().fail(result.getThrowable().getMessage().toString());

        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        testReport.get().fail("<details>" + "<summary>" + "<font color=" + "red>" + "Exception Occurred:Click to see" + "</font>" + "</b>" + "</summary>"
                + exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");
        String failureLog = "TEST CASE FAILED";
        Markup markup = MarkupHelper.createLabel(failureLog, ExtentColor.RED);
        testReport.get().log(Status.FAIL, markup);

        ITestListener.super.onTestFailure(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>" + "TEST CASE: - " + methodName.toUpperCase() + "  PASSED" + "</b>";
        Markup markup = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
        testReport.get().skip(markup);

        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extentReports != null){
            extentReports.flush();
        }

        ITestListener.super.onFinish(context);
    }
}
