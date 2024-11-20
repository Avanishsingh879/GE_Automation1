package com.genpact.stepdefinition;

import java.io.IOException;

import java.util.Iterator;
import com.genpact.stepdefinition.*;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class LoginPageGenpact extends BaseRunner{
	
	

	/*-------------------------------------------Start of Login Elements-----------------------------------------------*/
	
	private UIElement userEmailID = new UIElement("userEmailID", IdentifyBy.XPATH, "//*[@id=\"i0116\"]");
	private UIElement userEmailIDButton = new UIElement("userEmailIDButton", IdentifyBy.XPATH, "//*[@id=\"idSIButton9\"]");
	
	private UIElement userName = new UIElement("userName", IdentifyBy.ID, "okta-signin-username");
	private UIElement passWord = new UIElement("passWord", IdentifyBy.ID, "okta-signin-password");
	private UIElement signInButton = new UIElement("signIn", IdentifyBy.ID, "okta-signin-submit");
	private UIElement search = new UIElement("search", IdentifyBy.NAME, "app-link");
	private UIElement genpacthomepage = new UIElement("genpacthomepage", IdentifyBy.XPATH, "//img[@alt='Genpact - Production']");
	
	private UIElement LogoutButton = new UIElement("LogoutButton", IdentifyBy.XPATH, "//*[@id=\"O365_MainLink_MePhoto\"]/div/div/div/div[2]/img");
	public UIElement logoutimg = new UIElement("logoutimg", IdentifyBy.XPATH, "//*[@id=\"meControlLinks\"]/div[3]");
	
	/*String browserName = System.getProperty("browser");
	private UIElement userName = new UIElement(browserName, "loginPage", "userName");
	private UIElement passWord = new UIElement(browserName, "loginPage","password");
	private UIElement signInButton = new UIElement(browserName, "loginpage", "signIn");
	private UIElement search = new UIElement(browserName,"loogedInPage", "search");
	private UIElement genpacthomepage = new UIElement(browserName,"loggedInPage","genpacthomepage");
	
	private UIElement LogoutButton = new UIElement(browserName,"loggedInPage","LogoutButton");
	private UIElement logoutimg = new UIElement(browserName,"loggedInPage","logoutimg");*/
	
	
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
	
	@Given("^I navigate to application URL$")
	public void navigateURL() throws IOException, InterruptedException
	{
		String browserName = System.getProperty("browser");
		System.out.println("Browser Name "+browserName);
		String implicitWait = System.getProperty("implicitWait");
		System.out.println("implicitWait "+implicitWait);
		
		
		String appURL = EnvironmentConfig.getURL();
		
		Assert.assertNotNull(appURL, "Application URL should not be null");
		getBrowserLibrary().navigate(appURL);
		log.info("Navigated to the URL: " +appURL);
		
		Thread.sleep(10000);
		System.out.println("Entering email ID");
		try {
		elementLibrary.enterTextWithoutClear(userEmailID, "prashant.saraogi@genpact.com");
		}
		catch (Exception e) {
			
			System.out.println(e);
		
		}
		Thread.sleep(2000);
		elementLibrary.click(userEmailIDButton);
		System.out.println("Entered email ID");
		
		byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png"); //stick it in the report       

		Thread.sleep(3000);
		
		
	}
	/*
	 * Function: 		loginPage
	 * Description:		To verify title of the login page	
	*/
		@Then("^I verified title \"(.*?)\" on Login Page after launched the URL$")
		public void loginPage(String text) throws Throwable {
				
			    Thread.sleep(5000);
				String title = browserLibrary.getTitle();
				System.out.println("Title is "+title);
				Assert.assertEquals(title, text);
				log.info("Opened login page with title " +text);
				Thread.sleep(1000);
				byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		        scenario.embed(screenshot, "image/png"); //stick it in the report   
		    	Thread.sleep(3000);
		    	
			
		}
	
	/*
	 * Function: 		userName
	 * Description:		To enter the username	
	*/
	@When("^I type username as \"(.*?)\" in username filed on Login page$") 
	public void userName(String username) throws Throwable {
		
		    Thread.sleep(5000);
			Assert.assertNotNull(username, "Username should not be null");
			if(username.isEmpty() || username.contains("define") || username.contains("config"))
			{
				username=System.getProperty("username");
			}
			
			List<WebElement> tmp = WebdriverServiceLibrary.findElements(userName);
			elementLibrary.click(tmp.get(0));
			
			elementLibrary.enterText(userName, username);
			log.info("I entered userName as  "+username+ " in username filed successfully");
			
			byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	        scenario.embed(screenshot, "image/png"); //stick it in the report   
		
	}
		
	/*
	 * Function: 		password
	 * Description:		To enter the password	
	*/
	@And("^I type password as \"(.*?)\" in password filed on Login page$")
	public void password(String password) throws Throwable {
	
		Assert.assertNotNull(password, "Password should not be null");
		if(password.isEmpty() || password.contains("define") || password.contains("config"))
		{
			password=System.getProperty("password");
		}
		//elementLibrary.wait(10);
		elementLibrary.waitForElementToDisplay(passWord);
		elementLibrary.clearText(passWord);
		//elementLibrary.waitForElementToDisplay(uiElement);
		elementLibrary.enterText(passWord, password);
	//	elementLibrary.wait(10);
		System.out.println("Entered passWord:"+password);
		log.info("I entered passWord as "+password+ " in password field successfully");
		
		byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png"); //stick it in the report   
		
	
	}
	
	
	
	/*
	 * Function: 		login_application
	 * Description:		Login into the application	
	*/
	@And("^I click Login button on Login page$")  
	public void login_application() throws Throwable {
	   
	    	
			elementLibrary.click(signInButton);
	    	log.info("I clicked on Login button successfully on Login page ");
	    	Thread.sleep(3000);
	    	elementLibrary.click(userEmailIDButton);
			Thread.sleep(2000);
			byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	        scenario.embed(screenshot, "image/png"); //stick it in the report   
	     
	    }
	
	

	/*
	 * Function: 		logout_application
	 * Description:		logout from the application	
	*/
	@And("^I click Logout button from the application$")  
	public void logout_application() throws Throwable {
	 
	    	Thread.sleep(2000);
	    	
	    	elementLibrary.click(LogoutButton);
	    	Thread.sleep(2000);
	    	elementLibrary.click(logoutimg);
	    	

	    
	    	log.info("I clicked on Logout button successfully on cvs Application ");
	    	System.out.println("I clicked on Logout button successfully on cvs Application ");
	    	byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	        scenario.embed(screenshot, "image/png"); //stick it in the report   
	    	
	  
	}
	
	@Then("^I verify title showing as \"(.*?)\" page after successful login$") 
	public void verifyTitle(String text) throws Throwable{
	  		
			WebElement genpacthomepageTitle = getWebDriverService().findElement(genpacthomepage);
			title = elementLibrary.getText(genpacthomepageTitle);
			System.out.println(" title : " +title);
		    Assert.assertEquals(title, text);
			System.out.println("I verified title showing successfully as " + text);
			log.info("I verified title showing successfully as " + text);
			byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	        scenario.embed(screenshot, "image/png"); //stick it in the report   
						
		}
	
	
		
}
