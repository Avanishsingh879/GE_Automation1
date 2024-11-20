package com.automation.AutomationLibrary.ui;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.AutomationLibrary.ui.config.UIElement;

/**
 * @author sushilmore
 *
 */
public interface WebdriverService {

	//public WebDriver initialize(String browser);
	public WebDriver getWebDriver();
	public void shutdown();
	public By getBy(UIElement uiElement);
	public WebElement findElement(UIElement uiElement);
	public List<WebElement> findElements(UIElement uiElement);
	public File takeScreenshotAsFile();
	public byte[] takeScreenshot();
	public void ImplicitWaitSwitchON();
	public void ImplicitWaitSwitchOFF();
}
