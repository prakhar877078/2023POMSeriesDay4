package com.qa.opencart.tests;

import org.testng.annotations.BeforeClass;

import com.qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest{
	
	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}

}
