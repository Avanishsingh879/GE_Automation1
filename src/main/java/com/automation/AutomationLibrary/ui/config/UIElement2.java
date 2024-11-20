package com.automation.AutomationLibrary.ui.config;

import java.io.IOException;
import java.util.List;

import java.util.Map;

import org.json.simple.parser.ParseException;

import com.genpact.db.DBDataRetreival;
import com.genpact.db.DBSource;
import com.genpact.utils.DBDataSourcesConfig;

/**
 * @author Genpact Automation Team
 *
 */
public class UIElement2 {
	private String name;
	private IdentifyBy identifyBy;
	private String value;
	private String browserType;
	private String pageName;
	static DBDataRetreival retrieve;
	static {
		List<DBSource> dbSourceList;
		try {
			dbSourceList = DBDataSourcesConfig.getDBSources();
		    retrieve = new DBDataRetreival(dbSourceList.get(3));
		    
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public UIElement2(String browserType,String pageName,String name) {
		super();
		this.browserType=browserType;
		this.pageName=pageName;
		this.name=name;
		try {
			
			List<String[]> locatorTypeAndValue =retrieve.getQueryDataAsStringByIndex("select LocatorType,LocatorValue from BDDTableTest where (BrowserType='"+browserType+"'||BrowserType='all') and PageName='"+pageName+"' and ElementName='"+name+"'" );
			if(locatorTypeAndValue!=null)
				if(locatorTypeAndValue.isEmpty()) {
					throw new Exception("No Locators found exception");
				}
				if(locatorTypeAndValue.size()>1) {
					throw new Exception("More than one locators found exception");
				}
					
				
			
			for(String[] str:locatorTypeAndValue) {
				
				this.setIdentifyBy(IdentifyBy.valueOf(str[0]));// locator_type of current UIElement for example ID, XPATH, CSS and so on
				this.setValue(str[1]);// Locator_value of current UIElement Object
				
			}
		} catch ( Exception e) {
			e.printStackTrace();
			
			// TODO: handle exception
		}
		
	}
	
	public UIElement2(String name, IdentifyBy identifyBy, String value) {
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
	public String getBrowserType() {
		return browserType;
	}

	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public static DBDataRetreival getRetrieve() {
		return retrieve;
	}

	public static void setRetrieve(DBDataRetreival retrieve) {
		UIElement2.retrieve = retrieve;
	}

	
}
