package com.comcast.crm.ListenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.BaseTest.BaseClass;
import com.comcast.crm.generic.WebdriverUtility.UtilityClassObject;
import com.google.common.io.Files;

public class ListImpleClass implements ITestListener, ISuiteListener {
	public static ExtentReports report;
	public ExtentSparkReporter spark;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		// spark report config by creating object of ExtentSparkReporter

		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		spark = new ExtentSparkReporter("./AdvanceReport/report_" + time + ".html");
		spark.config().setDocumentTitle("CRM TEST Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add Environment Information and Create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Win-10");
		report.setSystemInfo("BROWSER", "Chrome");
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {

		System.out.println("===" + result.getMethod().getMethodName() + "===");

		// Extent report createTest here
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName() + "===STARTED===");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("===" + result.getMethod().getMethodName() + "===");
		test.log(Status.PASS, result.getMethod().getMethodName() + "===COMPLETED===");
		//System.out.println(result.getMethod().getMethodName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();

		String time = new Date().toString().replace(" ", "_").replace(":", "_");

		// TakesScreenshot screenshot = (TakesScreenshot) BaseClass.sdriver;
		TakesScreenshot screenshot = (TakesScreenshot) UtilityClassObject.getDriver();
		String filepath = screenshot.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(filepath, testName + " " + time);

		/*
		 * File srcFile = screenshot.getScreenshotAs(OutputType.FILE); File destFile =
		 * new File("./screenshot/"+testName+"+"+time+".png"); try { Files.copy(srcFile,
		 * destFile); } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		test.log(Status.FAIL, result.getMethod().getMethodName() + "===FAILED===");

	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

}
