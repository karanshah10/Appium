package com.example.apple.appiumtesting;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIConst;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIHelper;
import testlink.api.java.client.TestLinkAPIResults;


/**
 * Created by Apple on 4/3/2018.
 */

public class ExampleTestLinkTestCase {
    public static WebDriver driver;
    public static String DEV_KEY = "44c28a6d4dd1a6847769f5ddd4b1b501"; //Your API Key
    public static String SERVER_URL = "http://192.168.0.181:81/testlink/lib/api/xmlrpc/v1/xmlrpc.php"; //your testlink server url
    public static String PROJECT_NAME = "UNIMONI-Android";
    public static String PLAN_NAME = "UNIMONI";
    public static Integer testPlanID = 11;
    public static String BUILD_NAME = "UW Sprint 8";
    public static String result = "";
    public static String exception = null;
    public static String TestCases;

    @Test
    public void TestOne() throws Exception {
       // getTestLinktestSuite();
        String[] testCase = {"UNMA-255","UNMA-256","UNMA-257","UNMA-320","UNMA-321"};
        for (int i = 0; i <= testCase.length-1; i++) {
            result = TestLinkAPIResults.TEST_PASSED;
            updateTestLinkResult(testCase[i], result);
        }
    }

    private void getTestLinktestSuite() {
        TestLinkAPIClient apiClient = new TestLinkAPIClient(DEV_KEY, SERVER_URL);
        TestLinkAPIResults testLinkAPIResults = null;

        try {
            int project = apiClient.createTestProject("hello project", "PEaas", "hello project");


            TestLinkAPIResults projects = apiClient.getProjects();
            int projectID = TestLinkAPIHelper.getProjectID(apiClient, "hello project");
            testLinkAPIResults = apiClient.getTestSuitesForTestPlan(PROJECT_NAME, PLAN_NAME);
            for (int i = 0; i < testLinkAPIResults.size(); i++) {
                System.out.print(testLinkAPIResults.getData(i));
            }

            apiClient.createTestSuite("hello project", "First Suite", "FIRST TEst Suite from TestLink Api Client");


            List<HashMap<String, Object>> steps = new ArrayList<HashMap<String, Object>>();
            HashMap<String, Object> step = new HashMap<String, Object>();
            step.put("step_number", 1);
            step.put("actions", "<p>step 1 : see a person</p>");
            step.put("expected_results", "A person");
            step.put("execution_type", TestLinkAPIConst.TESTCASE_EXECUTION_TYPE_AUTO);
            steps.add(step);

            apiClient.createTestCase("bhoomipadaliya", "hello project", "First Suite",
                    "Test Plan Name1", "summary",
                    String.valueOf(steps), "Passed", TestLinkAPIConst.MEDIUM);

            callCreatePlan(DEV_KEY, "hello project", "test plan auto", "api test");

            apiClient.createBuild("hello project", "test plan auto", "First Build", "no notes");

            apiClient.reportTestCaseResult("hello project",
                    "test plan auto",
                    projectID + "-1",
                    "First Build",
                    "test api",
                    TestLinkAPIConst.TEST_PASSED);

        } catch (TestLinkAPIException e) {
            e.printStackTrace();
        }
    }

    public static Object[] callCreatePlan(String DEV_KEY, String Projectname,
                                          String testplanname, String notes) {
        try {
            XmlRpcClient rpcClient;
            XmlRpcClientConfigImpl config;
            config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL(SERVER_URL));
            rpcClient = new XmlRpcClient();
            rpcClient.setConfig(config);

            ArrayList<Object> params = new ArrayList<Object>();
            Hashtable<String, Object> methodData = new Hashtable<String, Object>();
            methodData.put("devKey", DEV_KEY);
            methodData.put("testprojectname", Projectname);
            methodData.put("testplanname", testplanname);
            methodData.put("notes", notes);
            params.add(methodData);
            Object[] result = (Object[]) rpcClient.execute("tl.createTestPlan",
                    params);
            return result;
        } catch (Exception e) {
            System.out.println("erreur : " + e.getMessage());
            System.out.println(e.getStackTrace()[0]);
            return null;
        }
    }

    private void updateTestLinkResult(String TestCases, String result) throws TestLinkAPIException {
        TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEV_KEY, SERVER_URL);
        testlinkAPIClient.reportTestCaseResult(PROJECT_NAME, PLAN_NAME, TestCases, BUILD_NAME, exception, result);
    }
}
