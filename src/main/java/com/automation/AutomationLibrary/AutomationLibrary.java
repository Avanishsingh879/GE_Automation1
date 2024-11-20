package com.automation.AutomationLibrary;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.automation.AutomationLibrary.ui.BrowserLibrary;
import com.automation.AutomationLibrary.ui.ElementLibrary;
import com.automation.AutomationLibrary.ui.PageLibrary;
import com.automation.AutomationLibrary.ui.WebdriverService;


/**
 * @author Genpact Automation Team
 *
 */
public class AutomationLibrary {

	private AnnotationConfigApplicationContext appContext;
	private WebdriverService webDriverService;
	private BrowserLibrary browserLibrary;
	private PageLibrary pageLibrary;
	private ElementLibrary elementLibrary;
	
	public void initializeUILribray()
	{
		appContext = new AnnotationConfigApplicationContext(UIAutomationConfig.class);
		webDriverService = appContext.getBean("webDriverService", WebdriverService.class);
		browserLibrary = appContext.getBean("browserLibrary", BrowserLibrary.class);
		pageLibrary = appContext.getBean("pageLibrary", PageLibrary.class);
		elementLibrary = appContext.getBean("elementLibrary", ElementLibrary.class);	
	}
	
	public WebdriverService getWebDriverService() {
		return this.webDriverService;
	}

	public BrowserLibrary getBrowserLibrary() {
		System.out.println("Calling Browser librarry...........");
		return this.browserLibrary; 
	}

	public ElementLibrary getElementLibrary() { 
		return this.elementLibrary;
	}

	public PageLibrary getPageLibrary() {
		return this.pageLibrary;
	}
	
	public void destroyUILibrary()
	{
		webDriverService.shutdown();
		appContext.close();
	}
}
