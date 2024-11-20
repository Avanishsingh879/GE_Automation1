package com.automation.AutomationLibrary.ui;

import java.io.IOException;
import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;

import com.automation.AutomationLibrary.ui.config.UIElement;

/**
 * @author Genpact Automation Team
 *
 */
public class ElementLibraryImpl implements ElementLibrary{

	private WebdriverService webDriverService;
	private WebDriver webDriver;
	private WebDriverWait wait = null;
	@Value("${webDriverWait}")
	private long webDriverWait;
	@Value("${implicitWait}")
	private int implicitWait;

	public ElementLibraryImpl(WebdriverService webDriverService)
	{
		this.webDriverService = webDriverService;
		this.webDriver = webDriverService.getWebDriver();
		this.wait =new WebDriverWait(this.webDriver, webDriverWait);
	}

	public void click(UIElement uiElement) throws IOException
	{
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(webDriverService.getBy(uiElement)));
		element.click();

	}

	public void click(WebElement element) throws IOException
	{
		// added by Lakshmi
		wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}

	public void clickForcefully(UIElement uiElement) throws IOException
	{
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(webDriverService.getBy(uiElement)));
		JavascriptExecutor executor = (JavascriptExecutor)webDriver;
		executor.executeScript("arguments[0].click();",element);
	}

	public void doubleClick(UIElement uiElement) throws IOException
	{
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(webDriverService.getBy(uiElement)));
		Actions action = new Actions(webDriver);
		action.moveToElement(element).doubleClick().perform();
	}

	public void clickOnSelectedElementInList(UIElement uiElement, int index) {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(webDriverService.getBy(uiElement)));
		List<WebElement> elements = webDriverService.findElements(uiElement);
		elements.get(index).click();
	}

	public String getColorOfElement(UIElement uiElement){
		return webDriverService.findElement(uiElement).getCssValue("color");
	}

	public String getText(WebElement ele) throws IOException {
		String text = ele.getText();
		System.out.println(text);

		return text;		
	}

	public String getTextBasedOnIndex(UIElement uiElement, int index) {
		return webDriverService.findElements(uiElement).get(index).getText();
	}


	public String getDisabledText(WebElement ele) {
		// added by Lakshmi
		wait.until(ExpectedConditions.visibilityOf(ele));
		String text = ele.getAttribute("value");
		System.out.println(text);

		return text;		
	}

	public void enterText(UIElement uiElement,String text) throws IOException {
		wait.until(ExpectedConditions.presenceOfElementLocated(webDriverService.getBy(uiElement)));
		webDriverService.findElement(uiElement).clear();
		webDriverService.findElement(uiElement).sendKeys(text);
	}

	public void enterTextBasedOnIndex(UIElement uiElement, String text, int index) throws IOException {
		webDriverService.findElements(uiElement).get(index).clear();
		webDriverService.findElements(uiElement).get(index).sendKeys(text);
	}

	public void enterText(WebElement ele,String text) throws IOException {
		// added by Lakshmi
		wait.until(ExpectedConditions.visibilityOf(ele));
		ele.clear();
		ele.sendKeys(text);
	}

	public void enterTextWithoutClear(UIElement uiElement,String text) throws IOException {
		wait.until(ExpectedConditions.presenceOfElementLocated(webDriverService.getBy(uiElement)));
		webDriverService.findElement(uiElement).sendKeys(text);
		//		JavascriptExecutor executor = (JavascriptExecutor)webDriver;
		//		executor.executeScript("arguments[0].setAttribute('value', '" + text +"')", uiElement);
	}

	public void clearText(UIElement uiElement) throws IOException {
		wait.until(ExpectedConditions.presenceOfElementLocated(webDriverService.getBy(uiElement)));
		webDriverService.findElement(uiElement).clear();
	}

	public void selectDropdownBasedOnText(UIElement uiElement,String text) throws IOException {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(webDriverService.getBy(uiElement)));
		Select drpDwn =  new Select(webDriverService.findElement(uiElement));
		drpDwn.selectByVisibleText(text);
	}

	public void selectDropdownBasedOnValue(UIElement uiElement,String value) throws IOException {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(webDriverService.getBy(uiElement)));
		Select drpDwn =  new Select(webDriverService.findElement(uiElement));
		drpDwn.selectByValue(value);
	}

	public void selectDropdownBasedOnIndex(UIElement uiElement,int index) throws IOException {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(webDriverService.getBy(uiElement)));
		Select drpDwn =  new Select(webDriverService.findElement(uiElement));
		drpDwn.selectByIndex(index);
	}

	public List<String> getDropdownTexts(UIElement uiElement) throws IOException {
		List<String> texts= new ArrayList<String>();
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(webDriverService.getBy(uiElement)));
		Select drpDwn =  new Select(webDriverService.findElement(uiElement));
		List<WebElement>drpDwnElems = drpDwn.getOptions();
		for(WebElement drpDwnEle:drpDwnElems)
			texts.add(drpDwnEle.getText());
		return texts;
	}

	public List<String> getSelectedDropdownBasedOnValue(UIElement uiElement) throws IOException {
		List<WebElement> myElems = null;
		List<String> texts= new ArrayList<String>();
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(webDriverService.getBy(uiElement)));
		Select drpDwn =  new Select(webDriverService.findElement(uiElement));
		myElems = drpDwn.getAllSelectedOptions();
		for(WebElement elem: myElems)
			texts.add(elem.getAttribute("value"));
		return texts;
	}

	public List<String> getSelectedDropdownBasedOnText(UIElement uiElement) throws IOException {
		List<WebElement> myElems = null;
		List<String> texts= new ArrayList<String>();
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(webDriverService.getBy(uiElement)));
		Select drpDwn =  new Select(webDriverService.findElement(uiElement));
		myElems = drpDwn.getAllSelectedOptions();
		for(WebElement elem: myElems)
			texts.add(elem.getText());
		return texts;
	}

	public String getCssValue(UIElement uiElement, String attribute) {
		return webDriverService.findElement(uiElement).getCssValue(attribute);
	}

	public boolean isRadioButtonSelected(UIElement uiElement) {
		return webDriverService.findElement(uiElement).isSelected();
	}

	public int getCount(UIElement uiElement) {
		List<WebElement> xpath= webDriverService.findElements(uiElement);
		return xpath.size();
	}

	public boolean iselementDisplayed(UIElement uiElement) {
		webDriverService.ImplicitWaitSwitchOFF();
		List<WebElement> elements= webDriverService.findElements(uiElement);
		webDriverService.ImplicitWaitSwitchON();
		boolean status = elements.size()>0?true:false;
		return status;
	}

	public boolean isElementDisplayedByIndex(UIElement uiElement, int index) {
		return webDriverService.findElements(uiElement).get(index).isDisplayed();
	}

	@Override
	public String getText(UIElement ele) throws IOException {
		WebElement we = webDriverService.findElement(ele);
		if(we==null)
		{
			return null;
		}
		else
		{ 
			return we.getText();
		}
	}

	@Override
	public void waitForElementToDisplay(UIElement uiElement) {
		webDriverService.findElement(uiElement);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void clickOnParent(UIElement uiElement) throws IOException {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(webDriverService.getBy(uiElement)));

		WebElement addNewRequestParent = (WebElement) ((JavascriptExecutor) this.webDriver)
				.executeScript("return arguments[0].parentNode;", element);

		addNewRequestParent.click();
	}


	// please cross verify this function
	public  void mouseOver(UIElement menuItem, UIElement subMenuItem) throws Exception {
		Actions action = new Actions(webDriver);
		WebElement mainMenu = webDriverService.findElement(menuItem);
		WebElement subMenuItems = webDriverService.findElement(subMenuItem);
		action.moveToElement(mainMenu).moveToElement(subMenuItems).click().build().perform();
	}

	public  boolean switchToChildWindow(String title) throws IOException {

		String currentWindow = webDriver.getWindowHandle();
		Set<String> availableWindows = webDriver.getWindowHandles();
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				if (webDriver.switchTo().window(windowId).getTitle().equals(title)) {
					return true;
				} else {
					//	webDriver.switchTo().window(webDriver.WindowHandles.Last());
				}}}

		return false;   
	}


	public  boolean switchToMainWindow() throws IOException {

		String currentWindow = webDriver.getWindowHandle();
		String lastWindow = null;
		Set<String> handles = webDriver.getWindowHandles();
		for (String aux : handles) {
			lastWindow = aux;
		}
		webDriver.switchTo().window(lastWindow); 
		return true;
	}

	public  boolean selectFramePopUp() throws IOException {

		Set <String>handles = webDriver.getWindowHandles();//To handle multiple windows
		String firstWinHandle = webDriver.getWindowHandle(); //To get your main window
		handles.remove(firstWinHandle);
		String winHandle=handles.iterator().next();  //To find popup window
		if (winHandle!=firstWinHandle){
			String secondWinHandle=winHandle;
			webDriver.switchTo().window(secondWinHandle); //To switch to popup window
			return true;
		}
		else {
			return false;
		}

	}

	// by Index
	public void switchToFrame(int frame) {
		webDriver.switchTo().frame(frame);
	}

	//by name
	public void switchToFrame(String frameName) {
		webDriver.switchTo().frame(frameName);
	}

	// using webelement
	public  void switchToFrame(WebElement webelement) throws IOException {

		webDriver.switchTo().frame(webelement);  
	}

	// switch from parent to child
	public void switchToFrame(String ParentFrame, String ChildFrame) throws IOException {
		webDriver.switchTo().frame(ParentFrame).switchTo().frame(ChildFrame);	
	}	

	public void switchtoDefaultFrame()  throws IOException {
		webDriver.switchTo().defaultContent();
	}

	public void switchToWindow(String windowName)  throws IOException {
		webDriver.switchTo().window(windowName);
	}

	public void holdMoveAndRelease(WebElement fromElement, WebElement toElement)  throws IOException {
		Actions action = new Actions(webDriver);
		Action dragdrop = action.clickAndHold(fromElement).moveToElement(toElement).release(toElement).build();
		dragdrop.perform();
	}

	public void click_JavaScript(WebElement element)  throws IOException {
		JavascriptExecutor executor = (JavascriptExecutor)webDriver;
		executor.executeScript("arguments[0].click();", element);
	}	

	public void refreshBrowser_JavaScript()  throws IOException {
		JavascriptExecutor executor = (JavascriptExecutor)webDriver;
		executor.executeScript("history.go(0)");
	}	

	public String getInnerText_JavaScript()  throws IOException {
		JavascriptExecutor executor = (JavascriptExecutor)webDriver;				
		String sText =  executor.executeScript("return document.documentElement.innerText;").toString();
		return sText;
	}	

	public String getTitle_JavaScript()  throws IOException {
		JavascriptExecutor executor = (JavascriptExecutor)webDriver;				
		String sText =  executor.executeScript("return document.title;").toString();
		return sText;
	}

	public String scrollPage_JavaScript()  throws IOException {
		JavascriptExecutor executor = (JavascriptExecutor)webDriver;
		//Vertical scroll - down by 150  pixels
		String sText = executor.executeScript("window.scrollBy(0,150)").toString();;
		return sText;

	}

	public void clear_JavaScript(WebElement element)  throws IOException {
		JavascriptExecutor executor = (JavascriptExecutor)webDriver;
		executor.executeScript("arguments[0].value = '';", element);
	}	
	public void enterText_JavaScript(WebElement element,String text)  throws IOException {
		JavascriptExecutor executor = (JavascriptExecutor)webDriver;
		executor.executeScript("arguments[0].value='"+text+"';",element);

	}

	public void setAttribute_JavaScript(WebElement element,String text)  throws IOException {

		JavascriptExecutor executor = (JavascriptExecutor)webDriver;
		executor.executeScript("arguments[0].setAttribute('attr', '"+text+"')",element);
	}

	public void moveElement_JavaScript(WebElement element)  throws IOException {
		JavascriptExecutor executor = (JavascriptExecutor)webDriver;
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		executor.executeScript(mouseOverScript,element);
	}

	public boolean validateText(WebElement element, String expText)  throws IOException {
		String actText = getText(element);
		if (actText.equals(expText)) {
			return true;
		}
		return false;
	}

	public boolean validateListBoxValues(UIElement uielement, List<String> expValues) throws IOException
	{
		List<String> actValues = getDropdownTexts(uielement);
		for(int i=0;i<expValues.size();i++) {
			if(actValues.get(i).contains(expValues.get(i))) {
				return true;
			}else {
				return false;
			}				
		}
		return false;
	}	
	
	 
	public String getAttributeValue(UIElement uiElement, String attributeName) {
	                wait.until(ExpectedConditions.presenceOfElementLocated(webDriverService.getBy(uiElement)));
	                return webDriverService.findElement(uiElement).getAttribute(attributeName);
	 }
	 



}
