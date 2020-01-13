package com.example.apple.appiumtesting;


import android.util.Log;

import org.apache.commons.codec.binary.Base64;
import org.aspectj.util.FileUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionType;
import br.eti.kinoshita.testlinkjavaapi.constants.TestCaseStepAction;
import br.eti.kinoshita.testlinkjavaapi.model.TestCaseStep;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import br.eti.kinoshita.testlinkjavaapi.model.TestProject;
import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIConst;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIHelper;

/**
 * Created by Apple on 4/6/2018.
 */

public class TestLinkApiTest {
    public static String DEV_KEY = "44c28a6d4dd1a6847769f5ddd4b1b501";
    public static String SERVER_URL = "http://192.168.0.181:81/testlink/lib/api/xmlrpc/v1/xmlrpc.php";

    TestLinkAPIClient apiClient = new TestLinkAPIClient(DEV_KEY, SERVER_URL);
    TestLinkAPI api = null;
    TestLinkAPI testLinkAPI = null;


    String projectName = "TEST";
    String projectid = "TESTAPI";
    String suiteName = "Test suite";
    String testCaseName = "Test case";
    String planName = "Test Plan";
    String buildName = "TestBuild";

    Integer project = null;

    File attachmentFile = new File("C:/Users/Apple/Desktop/device-2017-09-18-113220.png");
    String fileContent = null;

    @Test
    public void getCaseId(){
        try {
            String getCaseVisibleID = TestLinkAPIHelper.getCaseVisibleID(apiClient,"UNIMONI-Android","UNMA-255");
        } catch (TestLinkAPIException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void createProject() {
        try {


            testLinkAPI = new TestLinkAPI(new URL(SERVER_URL), DEV_KEY);

//            Check DEV key
            if (testLinkAPI.checkDevKey(DEV_KEY)) {
                Log.d("TEST Project", "DEV KEY PROPER");
            }

//            Create Project
            testLinkAPI.createTestProject(projectName, projectid, "Test Project note", true, true,
                    true, true, true, true);

//            get project Id

            TestProject[] projects = testLinkAPI.getProjects();

            Integer projectID = TestLinkAPIHelper.getProjectID(apiClient, projectName);




//            project = apiClient.createTestProject(projectName, "Test", "Test Note");
        } catch (TestLinkAPIException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createTestPlan() throws MalformedURLException, TestLinkAPIException {
        testLinkAPI = new TestLinkAPI(new URL(SERVER_URL), DEV_KEY);

//        cretae testPlan
        testLinkAPI.createTestPlan(planName, projectName, "First plan", true, true);
    }

    @Test
    public void createBuild() throws MalformedURLException {
        testLinkAPI = new TestLinkAPI(new URL(SERVER_URL), DEV_KEY);

//        create Build
        TestPlan testPlan = testLinkAPI.getTestPlanByName(planName, projectName);

        testLinkAPI.createBuild(testPlan.getId(), "FirstBuild", "Test first build");
    }

    @Test
    public void createTestSuite() throws MalformedURLException, TestLinkAPIException {
        testLinkAPI = new TestLinkAPI(new URL(SERVER_URL), DEV_KEY);

        // create Test Suite
        Integer projectID = TestLinkAPIHelper.getProjectID(apiClient, projectName);


        testLinkAPI.createTestSuite(projectID, suiteName, "Details for FirstTest suite",
                null, null, null, null);

    }

    @Test
    public void createTestCase() throws MalformedURLException, TestLinkAPIException {
        testLinkAPI = new TestLinkAPI(new URL(SERVER_URL), DEV_KEY);

//        create Test Case
//        testLinkAPI.getTestSuiteByID()
        Integer projectID = TestLinkAPIHelper.getProjectID(apiClient, projectName);
        Integer testSuiteId = TestLinkAPIHelper.getSuiteID(apiClient, projectName, suiteName);

        testLinkAPI.createTestCase(testCaseName, testSuiteId, projectID,
                "bhoomipadaliya", "First Test case summary", null, null, null,
                null, null, null, null, null, null);

    }

    @Test
    public void addTestCaseSteps() throws MalformedURLException, TestLinkAPIException {
        testLinkAPI = new TestLinkAPI(new URL(SERVER_URL), DEV_KEY);

//        add steps to Test Case

        List<TestCaseStep> steps = new ArrayList<TestCaseStep>();
        steps.add(new TestCaseStep(1, // ID
                1, // Version
                3, // Step number
                "Action 1", // Actions
                TestLinkAPIConst.TEST_FAILED, // Expected Results
                true, // Active?
                ExecutionType.AUTOMATED // Execution type
        ));
        steps.add(new TestCaseStep(1, // ID
                1, // Version
                4, // Step number
                "Action 2", // Actions
                TestLinkAPIConst.TEST_PASSED, // Expected Results
                true, // Active?
                ExecutionType.AUTOMATED // Execution type
        ));

        Integer projectID = TestLinkAPIHelper.getProjectID(apiClient, projectName);
        Integer testCaseId = TestLinkAPIHelper.getTestCaseID(apiClient, projectID, testCaseName);

        testLinkAPI.createTestCaseSteps(testCaseId, null, 1, TestCaseStepAction.CREATE, steps);
    }

    @Test
    public void addTestCaseToTestPlan() throws MalformedURLException, TestLinkAPIException {
        testLinkAPI = new TestLinkAPI(new URL(SERVER_URL), DEV_KEY);

        Integer projectID = TestLinkAPIHelper.getProjectID(apiClient, projectName);
        Integer planId = TestLinkAPIHelper.getPlanID(apiClient, projectID, planName);
        Integer buildId = TestLinkAPIHelper.getBuildID(apiClient, planId, "FirstBuild");
        Integer testCaseId = TestLinkAPIHelper.getTestCaseID(apiClient, projectID, testCaseName);


        testLinkAPI.addTestCaseToTestPlan(projectID, planId, testCaseId, 1, 1, null, null);
    }

    @Test
    public void addAttachmentToTestSuite() throws IOException, TestLinkAPIException {
        testLinkAPI = new TestLinkAPI(new URL(SERVER_URL), DEV_KEY);

        byte[] bytes = FileUtil.readAsByteArray(attachmentFile);
        fileContent = new String(Base64.encodeBase64(bytes));
        api = new TestLinkAPI(new URL(SERVER_URL), DEV_KEY);
        Integer testSuiteId = TestLinkAPIHelper.getSuiteID(apiClient, projectName, suiteName);

        testLinkAPI.uploadTestSuiteAttachment(testSuiteId, "First Attachment", "Attachment Description",
                "First", "png", fileContent);
    }

    @Test
    public void getReport() throws MalformedURLException {
        testLinkAPI = new TestLinkAPI(new URL(SERVER_URL), DEV_KEY);
    }
}
