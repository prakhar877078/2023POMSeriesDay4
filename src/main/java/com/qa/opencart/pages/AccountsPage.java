package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	AccountsPage(WebDriver driver){
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	private By logoutLink = By.linkText("Logout");
	private By accHeaders = By.xpath("//div[@id='content']/h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("#search button");
	
	public String getAccPageTitle() {
		//String title = driver.getTitle();
		String title = eleUtil.waitForTitleContainsAndFetch(AppConstants.DEFAULT_SHORT_TIMEOUT, AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
		System.out.println("Acc page title :"+title);
		return title;
	}
	
	public String getAccPageURL() {
		//String url = driver.getCurrentUrl();
		String url = eleUtil.waitForURLContainsAndFetch(AppConstants.DEFAULT_SHORT_TIMEOUT, AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE);
		System.out.println("Acc page url :"+url);
		return url;
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
		//return driver.findElement(logoutLink).isDisplayed();
	}
	
	public boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, AppConstants.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
	}
	
	public List<String> getAccountsPageHeadersList() {
		List<WebElement>accHeadersList = eleUtil.waitForElementsVisible(accHeaders, AppConstants.DEFAULT_MEDIUM_TIMEOUT);
		//List<WebElement>accHeadersList = driver.findElements(accHeaders);
		List<String>accHeadersValList = new ArrayList<String>();
		for(WebElement e : accHeadersList) {
			String text = e.getText();
			accHeadersValList.add(text);
		}
		return accHeadersValList;
	}
	
	public SearchPage performSearch(String searchKey) {
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchPage(driver);
		}else {
			System.out.println("Search Field is not present on the page...");
			return null;
		}
	}

}
