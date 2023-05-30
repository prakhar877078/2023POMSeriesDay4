package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest{
	
	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	@DataProvider
	public Object[][] getProductImagesTestData(){
		return new Object[][] {
			{"Macbook", "MacBook Pro", 4},
			{"iMac", "iMac", 3},
			{"Apple", "Apple Cinema 30\"", 6},
			{"Samsung", "Samsung SyncMaster 941BW", 1},
		};
	}
	
	@Test(priority = 1, dataProvider = "getProductImagesTestData")
	public void productImagesCountTest(String searchKey, String productName, int imagesCount) {
		searchPage = accPage.performSearch(searchKey);
		productInfoPage = searchPage.selectProduct(productName);
		int actImagesCount = productInfoPage.getProductImagesCount();
		Assert.assertEquals(actImagesCount, imagesCount);
	}

}
