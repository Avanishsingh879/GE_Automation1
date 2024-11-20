package com.genpact.stepdefinition;


import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.automation.AutomationLibrary.ui.BrowserLibrary;
import com.automation.AutomationLibrary.ui.ElementLibrary;
import com.automation.AutomationLibrary.ui.PageLibrary;
import com.automation.AutomationLibrary.ui.WebdriverService;
import com.automation.AutomationLibrary.ui.config.IdentifyBy;
import com.automation.AutomationLibrary.ui.config.UIElement;
import com.genpact.runner.BaseRunner;
import com.genpact.util.EnvironmentConfig;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchPage extends BaseRunner{

	/*-------------------------------------------Start of Login Elements-----------------------------------------------*/
	
	private UIElement search = new UIElement("search", IdentifyBy.NAME, "topearch");
	
	private UIElement searchclick = new UIElement("searchclick", IdentifyBy.XPATH, "//*[@id=\"button-holder\"]/img");
	
	
	private UIElement listofItemsinsearch = new UIElement("listofItemsinsearch", IdentifyBy.ID, "ui-id-1");
//	private UIElement listofItemsinsearch = new UIElement("listofItemsinsearch", IdentifyBy.XPATH, "ui-id-1");
	
//	
//	private UIElement LogoutButton = new UIElement("LogoutButton", IdentifyBy.ID, "header-logout-id");
//	public UIElement logo = new UIElement("logo", IdentifyBy.XPATH, "//img[@class='eip-cvs-logo']");
//	public UIElement errorMessage = new UIElement("errorMessage", IdentifyBy.XPATH, "//p[@class='eip-login-failed ng-binding']");
	
	/*-------------------------------------------End of Login Elements-------------------------------------------------*/
	
	public static String title = null;
	
	/*-------------------------------------------Start of other important declarations---------------------------------*/
	private BrowserLibrary browserLibrary = getBrowserLibrary();
	private PageLibrary pageLibrary = getPageLibrary();
	private ElementLibrary elementLibrary = getElementLibrary();
	private WebdriverService WebdriverServiceLibrary = getWebDriverService();
	WebDriver driver=getWebDriverService().getWebDriver();
	/*-------------------------------------------End of other important declarations-----------------------------------*/
	
	private final static Logger log = Logger.getLogger(LoginPageGenpact.class.getName());
	
	
	
	
	/*
	 * Function: 		i_enter_something_in_search_text_box_on_homepage
	 * Description:		To enter the text in search text box	
	*/
	@And("^I enter \"([^\"]*)\" in search text box on Homepage$")
	public void i_enter_something_in_search_text_box_on_homepage(String searchtext) throws Throwable {
		elementLibrary.enterText(search, searchtext);
		log.info("I entered text as "+searchtext+ " in search field successfully");	
		
		//elementLibrary.click(searchclick);
		
	}
	
	/*
	 * Function: 		i_enter_something_in_search_text_box_on_homepage
	 * Description:		To enter the text in search text box	
	*/
	@And("^I select \"([^\"]*)\" from list box on Homepage$")
	public void i_select_something_from_list_box_on_homepage(String searchtext) throws Throwable {
		WebElement options = getWebDriverService().findElement(listofItemsinsearch);
		List<WebElement> links = options.findElements(By.tagName("li"));
		
		for (int i = 0; i < links.size(); i++)
		{
			System.out.println(links.get(i).getText());
			int j = i+1;
			WebElement webele = driver.findElement(By.xpath("//*[@id='ui-id-1']/li["+ j +"]"));
		    String sActOption = links.get(i).getText();
		    if (sActOption.equalsIgnoreCase(searchtext))
		    {
		    	elementLibrary.click(webele);
		    	break;
		    }
		}
		
		
//		
//		elementLibrary.click(listofItemsinsearch);
//		elementLibrary.selectDropdownBasedOnText(listofItemsinsearch, searchtext);
//		log.info("I selected  "+searchtext+ " from list box successfully");	
	}
	
	
}

