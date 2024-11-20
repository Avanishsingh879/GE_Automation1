 package com.genpact.runner;

import com.genpact.runner.BaseRunner;

import cucumber.api.CucumberOptions;


//import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
//import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
//import com.github.mkolisnyk.cucumber.runner.ExtendedTestNGRunner;

//import cucumber.api.CucumberOptions;

//@ExtendedCucumberOptions(jsonReport = "target/cucumber-reports/cucumber.json",
//	retryCount = 2,
//	detailedReport = true,
//	detailedAggregatedReport = true,
//	overviewReport = true,
//	//coverageReport = true,
//	jsonUsageReport = "target/cucumber-usage.json",
//	usageReport = true,
//	toPDF = true,
//	//excludeCoverageTags = {"@flaky" },
//	//includeCoverageTags = {"@passed" },
//	outputFolder = "target"
//)

@CucumberOptions(
		strict = true,
		monochrome = true, 
		features = {"src/test/resources/features/"},
		tags={"@Regression"},
		glue = {"com.genpact.stepdefinition","com.genpact.runner"}, 	
		plugin = {
	                "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm",
	                "pretty",
	                "json:target/cucumber-reports/cucumber.json",
	                "html:target/cucumber-reports"
	        }
	
		 
	//	 plugin = {"json:target/cucumber-report/cucumber.json"}
		)

public class TestSuiteRunner extends BaseRunner{
	
	
}

