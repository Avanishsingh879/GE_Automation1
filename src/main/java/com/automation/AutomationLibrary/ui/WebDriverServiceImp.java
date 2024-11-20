package com.automation.AutomationLibrary.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.factory.annotation.Value;

import com.automation.AutomationLibrary.ui.config.IdentifyBy;
import com.automation.AutomationLibrary.ui.config.UIElement;

/**
 * @author Genpact Automation Team
 *
 */
public class WebDriverServiceImp implements WebdriverService {

	private  WebDriver driver;
	@Value("${implicitWait}")
	private int implicitWait;
	
	public WebDriverServiceImp(String browser)
	{
		//this.browser = "Chrome";
		initialize(browser);
	}
	private WebDriver initialize(String browser) {
		String osName = System.getProperty("os.name");
		switch (browser){
		case "Firefox": 
			FirefoxProfile fProfile = new FirefoxProfile();
			fProfile.setAcceptUntrustedCertificates(true);
			fProfile.setPreference("browser.download.dir", 
                                               System.getProperty("user.dir") +
                                               System.getProperty("file.separator") + 
                                               "target" + 
                                               System.getProperty("file.separator") + 
                                               "Downloads");
			fProfile.setPreference("browser.download.folderList", 2);
			fProfile.setPreference("browser.download.manager.showWhenStarting", false);
			fProfile.setPreference("browser.helperApps.alwaysAsk.force", false);
			fProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/plain");
			
			driver = new FirefoxDriver(); 
			break;
		case "InternetExplorer": 
		//	String service = "C:\\Automation_CoE\\HealthSource\\IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver","IEDriverServer.exe");
		/*	// Create the DesiredCapability object of InternetExplorer
			DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
	//		dc.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			dc.setCapability("requireWindowFocus", true);
			// Settings to Accept the SSL Certificate in the Capability object
		//	 dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			WebDriver driver = new InternetExplorerDriver(dc);*/
			 
	 
			 
			driver = new InternetExplorerDriver();
			
			
			break;
		case "chrome":
			if ( osName.contains("Mac") || osName.contains("mac") || 
                             osName.contains("linux") || osName.contains("Linux") 
                           )
			{
				System.setProperty("webdriver.chrome.driver","chromedriver");
			}
			else
			{
				System.setProperty("webdriver.chrome.driver","chromedriver.exe");
			}
			
			//making default download folder as target
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities = DesiredCapabilities.chrome();
			
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("profile.content_settings.pattern_pairs.*.multiple-automatic-downloads", 1);
			chromePrefs.put("credentials_enable_service", "false");
			chromePrefs.put("profile.password_manager_enabled", "false");
			chromePrefs.put("download.prompt_for_download", "false");
			chromePrefs.put("download.default_directory", System.getProperty("user.dir")+System.getProperty("file.separator") + "target"+System.getProperty("file.separator") + "Downloads");
			
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("test-type", "start-maximized","no-default-browser-check");
			chromeOptions.setExperimentalOption("prefs", chromePrefs);
			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			//chromeOptions.addArguments("--headless");
            //chromeOptions.addArguments("window-size=1980,960");

			driver = new ChromeDriver(chromeOptions);
            driver.manage().deleteAllCookies();
			break;
		case "Safari":
			driver = new SafariDriver();
			break;
		/*case "HTMLUnit":
			driver = new HtmlUnitDriver(); 
			break;*/
		case "Phantom":
			System.setProperty("webdriver.ie.driver","headless_ie_selenium.exe");
			System.setProperty("HEADLESS_UNIQUE","1");
			driver = new InternetExplorerDriver();
			break;
		case "MicrosoftEdge": 
			System.setProperty("webdriver.edge.driver","MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			break;
		default:
			driver = new FirefoxDriver(); 
		}
		return driver;
	}


	public WebDriver getWebDriver() {
		// TODO Auto-generated method stub
		return this.driver;
	}


	public void shutdown() {
		driver.close();
		driver.quit();

	}


	public By getBy(UIElement uiElement) {
		By by = null;
		try {
			IdentifyBy identifiedBy = uiElement.getIdentifyBy();
			
			String value = uiElement.getValue();
			
			switch (identifiedBy) {
			case ID:
				by = By.id(value);
				break;
			case NAME:
				by = By.name(value);
				break;
			case XPATH:
				by = By.xpath(value);
				break;
			case TAGNAME:
				by = By.tagName(value);
				break;
			case LINKTEXT:
				by = By.linkText(value);
				break;
			case PARTIALLINKTEXT:
				by = By.partialLinkText(value);
				break;
			case CSSSELECTOR:
				by = By.cssSelector(value);
				break;
			case CLASSNAME:
				by = By.className(value);
				break;

			default:
				by = By.id(value);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return by;
	}


	public WebElement findElement(UIElement uiElement) {
		WebElement myElement = null;
		try{
		myElement = driver.findElement(getBy(uiElement));
		}catch(NoSuchElementException ex){
			ex.getMessage();
		}
		return myElement;
	}


	public List<WebElement> findElements(UIElement uiElement) {
		List<WebElement> myElement = new ArrayList<WebElement>();
		myElement = driver.findElements(getBy(uiElement));
		return myElement;
	}

	public File takeScreenshotAsFile(){
		final File file = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
		return file;
	}
	
	public byte[] takeScreenshot(){
		final byte[] file = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
		return file;
	}
	public void ImplicitWaitSwitchON() {
		driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
	}
	public void ImplicitWaitSwitchOFF() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
	}
	
}
