package com.automation.AutomationLibrary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import com.automation.AutomationLibrary.ui.BrowserLibrary;
import com.automation.AutomationLibrary.ui.BrowserLibraryImpl;
import com.automation.AutomationLibrary.ui.ElementLibrary;
import com.automation.AutomationLibrary.ui.ElementLibraryImpl;
import com.automation.AutomationLibrary.ui.PageLibrary;
import com.automation.AutomationLibrary.ui.PageLibraryImpl;
import com.automation.AutomationLibrary.ui.WebDriverServiceImp;
import com.automation.AutomationLibrary.ui.WebdriverService;

/**
 * @author sushilmore
 *
 */
@Configuration
@PropertySource("file:config.properties")
public class UIAutomationConfig {

	@Value("${browser}")
	private String browser;
	
	
	@Bean
	public WebdriverService webDriverService()
	
	{
		
		return new WebDriverServiceImp(browser);
	}
	@Bean
	public BrowserLibrary browserLibrary()
	{
		//System.out.println("Hello"+browser);
		return new BrowserLibraryImpl(webDriverService());
	}
	@Bean
	public PageLibrary pageLibrary()
	{
		return new PageLibraryImpl(webDriverService());
	}
	@Bean

	public ElementLibrary elementLibrary()
	{
		return new ElementLibraryImpl(webDriverService());
	}
}
