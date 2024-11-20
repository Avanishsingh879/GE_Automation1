package com.genpact.stepdefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.When;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.automation.AutomationLibrary.AutomationLibrary;
import com.genpact.runner.BaseRunner;

/**
 * @author sushilmore
 *
 */
public class BaseStepDef extends BaseRunner{

	//private Scenario scenario;
	private AutomationLibrary automationLibrary = getAutomationLibrary();
	private final static Logger log = Logger.getLogger(BaseStepDef.class.getName());
	
	@When("^I navigate to the URL as \"(.*?)\"$")
	public void navigate_to_respective_url(String url) throws InterruptedException {
		try
		{
			System.out.println("Inside navigate");
			Assert.assertNotNull(url, "Application URL should not be null");
			if(url.isEmpty() || url.contains("define") || url.contains("config"))
			{
				url=System.getProperty("url");
			}
			System.out.println("URL:"+url);
			automationLibrary.getBrowserLibrary().navigate(url);
			System.out.println("Navigated to URL:"+url);
			log.info("Navigated to the URL: " +url);
		}
		catch (Exception e)
		{
			log.info("Unable to navigate to the URL " +url+ " showing the error message as " +e.getMessage() );
			log.debug(e.getMessage());

		}
	}
	
	@When("^I refresh page \"(.*?)\"$")
	public void refresh_respective_page(String page) throws InterruptedException {
		automationLibrary.getBrowserLibrary().refreshBrowser();
		System.out.println("The page " + page + " is refreshed!!!");
		log.info("The page " + page + " is refreshed!!!");
	}
	
		
	@When("^I navigate back on browser$")
	public void navigate_back_on_browser() throws InterruptedException{
		automationLibrary.getBrowserLibrary().navigateBack();
		System.out.println("Clicked on browser back");
		log.info("Clicked on browser back");
	}
	
	
	@When("^I wait for (\\d+) seconds$")
	public void wait_for_given_secs(int secs) throws InterruptedException{
		Thread.sleep(secs*1000);
		System.out.println("waited for "+secs+" seconds");
		log.info("waited for "+secs+" seconds");
	}
	
	@Before
	public void executeBefore(Scenario scenario){
		this.scenario = scenario;
		System.out.println("Scenaro "+scenario.getName()+" started");
		log.info("Scenaro "+scenario.getName()+" started");
	}
	
	@After
	public void executeAfter()
	{
		System.out.println("Scenario ended");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] screenshot = automationLibrary.getWebDriverService().takeScreenshot();
		//scenario.embed(screenshot, "image/png"); //stick it in the report
	}

}
