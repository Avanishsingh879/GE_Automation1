package com.automation.AutomationLibrary.ui;

import org.openqa.selenium.WebElement;

import com.automation.AutomationLibrary.ui.config.UIElement;

/**
 * @author sushilmore
 *
 */
public interface PageLibrary {

	public boolean isFieldPresent(WebElement element);
	public boolean isFieldPresent(UIElement uiElement);
	public boolean areFieldsPresent(UIElement uiElement);
	public boolean isDisabledOrEnabled(UIElement uiElement);
	public boolean isElementDisplayed(UIElement uiElement);
	public void waitTillElementDisplay(UIElement uiElement,String elementText);	
}
