package com.amazon.tests;

import com.amazon.pages.SearchPage;

public class AmazonSearchTest {
	public static void main(String[] args) throws Exception {
		SearchPage searchPage = new SearchPage();
		
		//	Search products as per items in array
		String[] inputData = {"nvidia 3060", "nvidia 3070", "nvidia 3080"};
		searchPage.searchProductsAndExtractData(inputData);
	}
}
