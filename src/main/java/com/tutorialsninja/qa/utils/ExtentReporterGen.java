package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterGen {
	public static ExtentReports generateExtentReport() throws Exception {
		ExtentReports extentReports = new ExtentReports();
		File extentReportsFile = new File(
				System.getProperty("user.dir") + "/test-output/ExtentReports/extentReport.html");
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(extentReportsFile);

		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setReportName("Tutorials Ninja Automation Result");
		sparkreporter.config().setDocumentTitle("TN Automation Report");
		sparkreporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

		extentReports.attachReporter(sparkreporter);
		Properties configPorp = new Properties();
		File propFile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		FileInputStream fis = new FileInputStream(propFile);
		configPorp.load(fis);
		
		extentReports.setSystemInfo("Application URl", configPorp.getProperty("url"));
		extentReports.setSystemInfo("Browser Name", configPorp.getProperty("browserName"));
		extentReports.setSystemInfo("Email", configPorp.getProperty("validEmail"));
		extentReports.setSystemInfo("Password", configPorp.getProperty("validPassword"));

		extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReports.setSystemInfo("User Name", System.getProperty("user.name"));

		return extentReports;
	}
}
