package com.amazon.pages;

import java.util.Date;

import com.amazon.util.CSVUtil;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;

public class SearchPage {
	private Browser browser;
	private Page page;
	private CSVUtil objCSV = new CSVUtil();

	//	Selectors
	private String searchInput = "id=twotabsearchtextbox";
	private String searchButton = "id=nav-search-submit-button";
	private String searchResults = "xpath=//div[contains(@class,'s-main-slot s-result-list s-search-results')]/div[@data-component-type='s-search-result']";

	
	public void searchProductsAndExtractData(String[] inputData) throws Exception {
		try (Playwright playwright = Playwright.create()) {
//		      browser = playwright.chromium().launch();
		      browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
		      page = browser.newPage();
		      
		      page.navigate("https://www.amazon.com/");
		      System.out.println("Title: " + page.title());
      
		      for(int i = 0; i < inputData.length; i++) {
		          page.waitForSelector(searchInput);
		          page.fill(searchInput, inputData[i]);
		    	  page.click(searchButton);
		    	  
		    	  page.waitForSelector(searchResults, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.ATTACHED));
		    	  
		    	  String attr = "", name = "", price = "";
		    	  
		    	  //	Capturing item details for first 3 search results
		    	  for(int j = 1; j<=3; j++) {
		    		  System.out.println("\nPrinting details of Item#: " + j + " for Item - " + inputData[i]);
		        	  attr = page.getAttribute("xpath=((//div[contains(@class,'s-main-slot s-result-list s-search-results')]/div[@data-component-type='s-search-result'])[" + j + "]//a[contains(@class,'a-link-normal a-text-normal')])[1]", "href");
		              System.out.println(attr);
		              
		              name = page.textContent("xpath=((//div[contains(@class,'s-main-slot s-result-list s-search-results')]/div[@data-component-type='s-search-result'])[" + j + "]//a[contains(@class,'a-link-normal a-text-normal')])[1]/span");
		              System.out.println(name);
		              
		              try {
		            	  price = page.textContent("xpath=((//div[contains(@class,'s-main-slot s-result-list s-search-results')]/div[@data-component-type='s-search-result'])[" + j + "]//a[contains(@class,'a-link-normal a-text-normal')])[2]//span[@class='a-price']/span[1]");
		              }catch(PlaywrightException e) {
		            	  price = "Price Not Found";
//		            	  e.printStackTrace();
		              }
		              System.out.println(price);
		              
		              objCSV.appendToCSV(inputData[i] + "," + name + "," + attr + "," + price.replace(",", "") + "," + (new Date()).toString());
		     	  }
		      }
		      browser.close();
	    }
	}
}
