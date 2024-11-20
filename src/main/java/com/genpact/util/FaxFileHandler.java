package com.genpact.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.genpact.db.DBDataRetreival;
//import com.ciox.stepdefinition.NonUIStepDefinitions;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class FaxFileHandler {
	
	private static String JSON_FILE_NAME = "EREquests.json";
	private final static Logger log = Logger.getLogger(FaxFileHandler.class.getName());
	
	public static String[] placeFaxFilesInNLPLocation(String sampleFile,int numberOfFiles) throws IOException
	{
		String[] fileNames = new String[numberOfFiles];
		
		String nlp_sharedLocation_parentPath = System.getProperty("NLP_SharedLocation_ParentPath");
		String nlp_sharedLocation_trailingName = System.getProperty("NLP_SharedLocation_Trailing_FolderName");
		String faxNumber = System.getProperty("FaxNumber");
		String siteID = System.getProperty("SiteID");
		
		File sourceFile=new File(System.getProperty("user.dir")+ "\\src\\test\\resources\\testdata\\" + sampleFile);
		log.debug("Source File:"+sourceFile.getAbsolutePath());
		//System.out.println("Source File:"+sourceFile.getAbsolutePath());
//		ClassLoader classLoader = getClass().getClassLoader();
//		File sourceFile = new File(classLoader.getResource(sampleFile).getFile());
		
		for(int i=0;i<numberOfFiles;i++)
		{
			TimeZone tz = TimeZone.getTimeZone("America/New_York");
			Calendar calender = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			sdf.setTimeZone(tz);
			String timeStamp = sdf.format(calender.getTime());
			String fileName = "fax-"+timeStamp+"-"+faxNumber+".pdf";
			fileNames[i] = fileName;
			File filetobefaxed = new File(nlp_sharedLocation_parentPath+"\\"+faxNumber+"-"+siteID+"_"+nlp_sharedLocation_trailingName+"\\"+ fileName);
			log.debug("Target File:"+filetobefaxed.getAbsolutePath());
			//System.out.println("Target File:"+filetobefaxed.getAbsolutePath());
			FileUtils.copyFile(sourceFile, filetobefaxed);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log.info("Files has been placed into NLP Location");
		return fileNames;
	}
	
	public static void updateRequestID(String requestType,String taskName,long requestID) throws IOException
	{
		File jsonFile = new File(System.getProperty("user.dir")+ "\\src\\test\\resources\\testdata\\"+JSON_FILE_NAME);
		String jsonString = FileUtils.readFileToString(jsonFile);
		JsonElement jelement = new JsonParser().parse(jsonString);
		JsonObject jobject = jelement.getAsJsonObject();
		JsonObject jobject2 = jobject.getAsJsonObject(requestType);
		jobject2.addProperty(taskName, requestID);
		Gson gson = new Gson();
		String resultingJson = gson.toJson(jelement);
		FileUtils.writeStringToFile(jsonFile, resultingJson);
	}
	
	public static List<String> getTaskNames(String requestType) throws IOException
	{
		List<String> taskNames = new ArrayList<String>();
		File jsonFile = new File(System.getProperty("user.dir")+ "\\src\\test\\resources\\testdata\\"+JSON_FILE_NAME);
		String jsonString = FileUtils.readFileToString(jsonFile);
		JsonElement jelement = new JsonParser().parse(jsonString);
		JsonObject jobject = jelement.getAsJsonObject();
		JsonObject jobject2 = jobject.getAsJsonObject(requestType);
		Iterator<Entry<String,JsonElement>> vals = jobject2.entrySet().iterator();
		while(vals.hasNext())
		{
			Entry<String,JsonElement> val = vals.next();
			taskNames.add(val.getKey());
		}
		return taskNames;
	}
	
	public static long getRequestID(String requestType,String taskName) throws IOException
	{
		File jsonFile = new File(System.getProperty("user.dir")+ "\\src\\test\\resources\\testdata\\"+JSON_FILE_NAME);
		String jsonString = FileUtils.readFileToString(jsonFile);
		JsonElement jelement = new JsonParser().parse(jsonString);
		JsonObject jobject = jelement.getAsJsonObject();
		JsonObject jobject2 = jobject.getAsJsonObject(requestType);
		JsonPrimitive jobject3 =jobject2.getAsJsonPrimitive(taskName);
		return jobject3.getAsLong();
	}

}
