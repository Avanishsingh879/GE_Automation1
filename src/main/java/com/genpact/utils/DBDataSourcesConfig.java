package com.genpact.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.genpact.db.DBSource;
import com.genpact.db.Vendor;

public class DBDataSourcesConfig {

	public static List<DBSource> getDBSources() throws IOException, ParseException
	{
		List<DBSource> dbSources = new ArrayList<DBSource>();
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader("DBSources.json");
		JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);
		for(int i=0;i<jsonArray.size();i++)
			{
				JSONObject jsnObj = (JSONObject)jsonArray.get(i);
				String name = (String)jsnObj.get("name");
				int portNumber = Integer.parseInt((String)jsnObj.get("portNumber"));
				String vendor = (String)jsnObj.get("vendor");
				String hostName = (String)jsnObj.get("hostName");
				String dbName = (String)jsnObj.get("dbName");
				String userName = (String)jsnObj.get("userName");
				String password = (String)jsnObj.get("password");
				
				DBSource  dbSOurce = new DBSource();
				dbSOurce.setPortNumber(portNumber);
				dbSOurce.setVendor(Vendor.valueOf(vendor));
				dbSOurce.setHostName(hostName);
				dbSOurce.setDbName(dbName);
				dbSOurce.setUserName(userName);
				dbSOurce.setPassword(password);
				dbSources.add(dbSOurce);
			}
		return dbSources;
	}
}
