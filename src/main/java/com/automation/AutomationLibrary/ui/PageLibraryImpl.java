package com.automation.AutomationLibrary.ui;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Value;

import com.automation.AutomationLibrary.ui.config.UIElement;

/**
 * @author Genpact Automation Team
 *
 */
public class PageLibraryImpl implements PageLibrary{

	private WebdriverService webDriverService;
	private WebDriver driver;
	@Value("${implicitWait}")
	private int implicitWait;
	
	
	public PageLibraryImpl(WebdriverService WebDriverService)
	{
		this.webDriverService = WebDriverService;
		this.driver = WebDriverService.getWebDriver();
	}
	
	public boolean isFieldPresent(WebElement element) {
		try {
			if((element)!=null)
				return true;
			else
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean areFieldsPresent(UIElement uiElement){
		try {
			driver.manage().timeouts().implicitlyWait(implicitWait,TimeUnit.SECONDS);
			List<WebElement> elements = webDriverService.findElements(uiElement);
			if (elements.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean isDisabledOrEnabled(UIElement uiElement){
		try {
			driver.manage().timeouts().implicitlyWait(implicitWait,
	                TimeUnit.SECONDS);
			return webDriverService.findElement(uiElement).isEnabled();
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public boolean isElementDisplayed(UIElement uiElement){
		try {
			driver.manage().timeouts().implicitlyWait(implicitWait,
	                TimeUnit.SECONDS);
			return webDriverService.findElement(uiElement).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}


	public boolean isFieldPresent(UIElement uiElement) {
		WebElement element = webDriverService.findElement(uiElement);
		try {
			if((element)!=null)
				return true;
			else
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void waitTillElementDisplay(UIElement uiElement, String elementText) {
	        WebElement element = webDriverService.findElement(uiElement);
            if(element!=null)
            {
            	String actualElementText = element.getText();
            	while(elementText.equals(actualElementText))
            		{
            			webDriverService.ImplicitWaitSwitchOFF();
            			WebElement we = webDriverService.findElement(uiElement);
            			webDriverService.ImplicitWaitSwitchON();
            			if(we==null)
            			{
            				break;
            			}
            			else
            			{
            				try{
            				actualElementText = we.getText();
            				}catch(Exception e)
            				{
            					break;
            				}
            			}
            		}
            }
	}

}
