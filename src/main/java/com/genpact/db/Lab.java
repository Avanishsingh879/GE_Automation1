package com.genpact.db;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.genpact.utils.DBDataSourcesConfig;

public class Lab {
	
	public static void main(String args[]) {
		try {
			List<DBSource> list=DBDataSourcesConfig.getDBSources();
			//System.out.print(list.size());
			//DBConnection db=new DBConnection(list.get(3));
			
			DBDataRetreival retrieve = new DBDataRetreival(list.get(3));
			
			List<String[]> list2 =retrieve.getQueryDataAsStringByIndex("select * from BDDTableTest" );
			for(String[] obj:list2) {
				System.out.println(obj[0]+""+obj[1]);
			}
			
//			for(Map<String, Object> map:list2) {
//				System.out.println(map.get("locator_value"));
//				
//			}
//			
			
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
