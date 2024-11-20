package com.automation.AutomationLibrary.ui.config;



/**
 * @author sushilmore
 *
 */
public class UIElement {
	private String name;
	private IdentifyBy identifyBy;
	private String value;
	

	
	public UIElement(String name, IdentifyBy identifyBy, String value) {
		super();
		this.name = name;
		this.identifyBy = identifyBy;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IdentifyBy getIdentifyBy() {
		return identifyBy;
	}
	public void setIdentifyBy(IdentifyBy identifyBy) {
		this.identifyBy = identifyBy;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
