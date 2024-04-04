package com.tutorialsninja.qa.test.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class Base {
	public WebDriver driver;
	public Properties prop;
	public Properties dataprop;

	public Base() {
		prop = new Properties();
		File propfile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");

		dataprop = new Properties();
		File datapropfile = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");

		try {
			FileInputStream fis2 = new FileInputStream(datapropfile);
			dataprop.load(fis2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileInputStream fis = new FileInputStream(propfile);
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebDriver initializeBrowserAndOpenApplication(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGELOAD_WAIT_TIME));
		driver.get(prop.getProperty("url"));
		return driver;
	}

}
