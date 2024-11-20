package com.genpact.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.genpact.db.DBSource;
import com.genpact.db.Vendor;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class EnvironmentConfig {
	private static String JSON_FILE_NAME="Environment.json";

	public static String getURL() throws IOException
	{
		String environment = System.getProperty("environment");
		System.out.println("environment:"+environment);
		File jsonFile = new File(JSON_FILE_NAME);
		String jsonString = FileUtils.readFileToString(jsonFile);
		JsonElement jelement = new JsonParser().parse(jsonString);
		JsonObject jobject = jelement.getAsJsonObject();
		JsonObject jobject2 = jobject.getAsJsonObject(environment);
		return jobject2.get("URL").getAsString();
	}
	
	public static DBSource getDBSource() throws IOException
	{
		String environment = System.getProperty("environment");
		File jsonFile = new File(JSON_FILE_NAME);
		String jsonString = FileUtils.readFileToString(jsonFile);
		JsonElement jelement = new JsonParser().parse(jsonString);
		JsonObject jobject = jelement.getAsJsonObject();
		JsonObject jobject2 = jobject.getAsJsonObject(environment);
		JsonObject dbConfig = jobject2.getAsJsonObject("DBConfig");
		String vendor = dbConfig.get("vendor").getAsString();
		String hostName = dbConfig.get("hostName").getAsString();
		int portNumber = dbConfig.get("portNumber").getAsInt();
		String dbName = dbConfig.get("dbName").getAsString();
		String userName = dbConfig.get("userName").getAsString();
		String password = dbConfig.get("password").getAsString();
		
		DBSource  dbSOurce = new DBSource();
		dbSOurce.setPortNumber(portNumber);
		dbSOurce.setVendor(Vendor.valueOf(vendor));
		dbSOurce.setHostName(hostName);
		dbSOurce.setDbName(dbName);
		dbSOurce.setUserName(userName);
		dbSOurce.setPassword(password);
		
		return dbSOurce;
	}
	
	public static EmailSource getEmailSource() throws IOException
	{
		String environment = System.getProperty("environment");
		File jsonFile = new File(JSON_FILE_NAME);
		String jsonString = FileUtils.readFileToString(jsonFile);
		JsonElement jelement = new JsonParser().parse(jsonString);
		JsonObject jobject = jelement.getAsJsonObject();
		JsonObject jobject2 = jobject.getAsJsonObject(environment);
		JsonObject dbConfig = jobject2.getAsJsonObject("EmailConfig");
		
		String recipientEmail = dbConfig.get("recipientEmail").getAsString();
		String senderEmail = dbConfig.get("senderEmail").getAsString();
		String senderEmailPwd = dbConfig.get("senderEmailPwd").getAsString();
		String emailSMTPServerHost = dbConfig.get("emailSMTPServerHost").getAsString();
		int emailSMTPServerPort = dbConfig.get("emailSMTPServerPort").getAsInt();
		String emailPOPServerHost = dbConfig.get("emailPOPServerHost").getAsString();
		int emailPOPServerPort = dbConfig.get("emailPOPServerPort").getAsInt();
		
		EmailSource emailSource = new EmailSource(recipientEmail, senderEmail, senderEmailPwd, emailSMTPServerHost, emailSMTPServerPort, emailPOPServerHost, emailPOPServerPort);
		return emailSource;
	}

}
