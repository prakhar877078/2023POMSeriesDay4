package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsmanager;
	
	public static String highlight;
	
	/**
	 * this method is initializing the driver on the basis of given browser name
	 * @param browserName
	 * @return this returns the driver
	 */
	/*
	 * public WebDriver initDriver(String browserName) {
	 * System.out.println("Browser Name is :"+browserName);
	 * 
	 * if(browserName.trim().equalsIgnoreCase("chrome")) { driver = new
	 * ChromeDriver(); }else if(browserName.trim().equalsIgnoreCase("firefox")){
	 * driver = new FirefoxDriver(); }else {
	 * System.out.println("Please pass the right browser...."+browserName); }
	 * driver.manage().deleteAllCookies(); driver.manage().window().maximize();
	 * driver.get(
	 * "https://naveenautomationlabs.com/opencart/index.php?route=account/login");
	 * return driver; }
	 */
	
	public WebDriver initDriver(Properties prop) {
		
		optionsmanager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight").trim();
		
		String browserName = prop.getProperty("browser").toLowerCase().trim();
		System.out.println("Browser Name is :"+browserName);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver(optionsmanager.getChromeOptions());
		}else if(browserName.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver(optionsmanager.getFirefoxOptions());
		}else {
			System.out.println("Please pass the right browser...."+browserName);
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		return driver;
	}
	
	/**
	 * this method is reading the properties from the .properties file
	 * @return
	 */
	public Properties initProp() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(".\\src\\test\\resources\\config\\config.properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

}
