package com.genpact.db;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

import com.genpact.utils.DBDataSourcesConfig;

import org.apache.commons.lang3.StringUtils;

/**
 * @author sushilmore
 *
 */

public class DBDataRetreival {

	private DBSource dataSource;
	
	public DBDataRetreival(DBSource dbSOurce) throws IOException, ParseException
	{
		this.dataSource = dbSOurce;
	}
	
	public List<String[]> getQueryDataAsStringByIndex(String query)
	{
		List<String[]> data = new ArrayList<String[]>();
		
		DBConnection dbConnection = new DBConnection(this.dataSource);
		Connection conn = dbConnection.getConnection();
		Statement stmt =null;
		try {
			stmt = conn.createStatement();
			ResultSet rslt = stmt.executeQuery(query);
			ResultSetMetaData rsltMtDt = rslt.getMetaData();
			int colCount = rsltMtDt.getColumnCount();
			while(rslt.next())
			{
				String[] val = new String[colCount];
				for(int i=1;i<=colCount;i++)
				{
					byte[] bytesDt = null;
					String byteArrStr = null; 
					int sqlType = rsltMtDt.getColumnType(i);
					switch (sqlType) {
					
					 case Types.CHAR:
						 val[i-1] = rslt.getString(i);
						 break;
					 case Types.VARCHAR:
							val[i-1] = rslt.getString(i);
							break;
					 case Types.NVARCHAR:
						 val[i-1] = rslt.getString(i);
							break;
					 case Types.LONGVARCHAR:
						 val[i-1] = rslt.getString(i);
						 break;
					 case Types.BINARY:
						 bytesDt = rslt.getBytes(i);
						 byteArrStr = new String(bytesDt, StandardCharsets.UTF_8);
						 val[i-1] = byteArrStr;
						 break;
					 case Types.VARBINARY:
						 bytesDt = rslt.getBytes(i);
						 byteArrStr = new String(bytesDt, StandardCharsets.UTF_8);
						 val[i-1] = byteArrStr;
						 break;
					 case Types.LONGVARBINARY:
						 bytesDt = rslt.getBytes(i);
						 byteArrStr = new String(bytesDt, StandardCharsets.UTF_8);
						 val[i-1] = byteArrStr;
						 break;
						 
					 case Types.BIT:
						 boolean bitVal = rslt.getBoolean(i);
						 String bitValStr = new Boolean(bitVal).toString();
						 val[i-1] = bitValStr;
						 break;
					 case Types.TINYINT:
						 short shortVal = rslt.getShort(i);
						 val[i-1] = new Short(shortVal).toString();
						 break;
					 case Types.SMALLINT:
						 short smallIntVal = rslt.getShort(i);
						 val[i-1] = new Short(smallIntVal).toString();
						 break;
					 case Types.INTEGER:
						 int intVal = rslt.getInt(i);
						 val[i-1] = new Integer(intVal).toString();
						 break;
					 case Types.BIGINT:
						 long longVal = rslt.getLong(i);
						 val[i-1] = new Long(longVal).toString();
						 break;
					 case Types.REAL:
						 float realVal = rslt.getFloat(i);
						 val[i-1] = new Float(realVal).toString();
						 break;
					 case Types.DOUBLE:
	                     double dblVal = rslt.getDouble(i);
						 val[i-1] = new Double(dblVal).toString();
	                    break;
					 case Types.FLOAT:
	                     double dblVal2 = rslt.getDouble(i);
						 val[i-1] = new Double(dblVal2).toString();
	                    break;
	                    
					 case Types.DECIMAL:
							 BigDecimal bigDecimalVal = rslt.getBigDecimal(i);
							 if(bigDecimalVal==null)
							 {
								 val[i-1] = null;
							 }
							 else
							 {
							 val[i-1] = bigDecimalVal.toString();
							 }
							 break;
					 case Types.NUMERIC:
						 BigDecimal bigDecimalVal2 = rslt.getBigDecimal(i);
						 if(bigDecimalVal2==null)
						 {
							 val[i-1]=null;
						 }
						 else
						 {
							 val[i-1] = bigDecimalVal2.toString();
						 }
						 break;
					 case Types.DATE:
						 Date dt = rslt.getDate(i);
						 if(dt==null)
						 {
							 val[i-1] = null;
						 }
						 else
						 {
						 val[i-1] = dt.toString();
						 }
						 break;
					 case Types.TIME:
						 Date dt2 = rslt.getTime(i);
						 if(dt2==null)
						 {
							 val[i-1] = null;
						 }
						 else
						 {
						 val[i-1] = dt2.toString();
						 }
						 break;
					 case Types.TIMESTAMP:
						 Timestamp timeStmp = rslt.getTimestamp(i);
						 if(timeStmp!=null)
						 {
						 Date dt3 = new Date(timeStmp.getTime() + (timeStmp.getNanos() / 1000000));
						 val[i-1] = dt3.toString();
						 }
						 else
						 {
							 val[i-1] = null;
						 }
						 break;
	                    
					/* case Types.TIMESTAMP:
						 Timestamp tmStmp = rslt.getTimestamp(i);
						 Calendar start = Calendar.getInstance();
						 String timStmpVal = null;
						 if(tmStmp!=null)
						 {
						 start.setTimeInMillis( tmStmp.getTime() );
						// System.out.println("Start:"+start);
						 SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						 timStmpVal = f.format(tmStmp);
						 }
						
						 val[i-1] = timStmpVal;
						 break;*/
					default:
						val[i-1] = null;
						break;
					}
					
					
				}
				
				data.add(val);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return data;
	}
	
	public List<Object[]> getQueryDataAsObjectByIndex(String query)
	{
		List<Object[]> data = new ArrayList<Object[]>();
		
		DBConnection dbConnection = new DBConnection(this.dataSource);
		Connection conn = dbConnection.getConnection();
		Statement stmt =null;
		try {
			stmt = conn.createStatement();
			ResultSet rslt = stmt.executeQuery(query);
			ResultSetMetaData rsltMtDt = rslt.getMetaData();
			int colCount = rsltMtDt.getColumnCount();
			while(rslt.next())
			{
				Object[] val = new String[colCount];
				for(int i=1;i<=colCount;i++)
				{
					byte[] bytesDt = null;
					String byteArrStr = null; 
					int sqlType = rsltMtDt.getColumnType(i);
					switch (sqlType) {
					
					 case Types.CHAR:
						 val[i-1] = rslt.getString(i);
						 break;
					 case Types.VARCHAR:
							val[i-1] = rslt.getString(i);
							break;
					 case Types.NVARCHAR:
							val[i-1] = rslt.getString(i);
							break;
					 case Types.LONGVARCHAR:
						 val[i-1] = rslt.getString(i);
						 break;
					 case Types.BINARY:
						 bytesDt = rslt.getBytes(i);
						 //byteArrStr = new String(bytesDt, StandardCharsets.UTF_8);
						 val[i-1] = bytesDt;
						 break;
					 case Types.VARBINARY:
						 bytesDt = rslt.getBytes(i);
						 //byteArrStr = new String(bytesDt, StandardCharsets.UTF_8);
						 val[i-1] = bytesDt;
						 break;
					 case Types.LONGVARBINARY:
						 bytesDt = rslt.getBytes(i);
						// byteArrStr = new String(bytesDt, StandardCharsets.UTF_8);
						 val[i-1] = bytesDt;
						 break;
						 
					 case Types.BIT:
						 boolean bitVal = rslt.getBoolean(i);
						 //String bitValStr = new Boolean(bitVal).toString();
						 val[i-1] = bitVal;
						 break;
					 case Types.TINYINT:
						 short shortVal = rslt.getShort(i);
						 val[i-1] = new Short(shortVal);
						 break;
					 case Types.SMALLINT:
						 short smallIntVal = rslt.getShort(i);
						 val[i-1] = new Short(smallIntVal);
						 break;
					 case Types.INTEGER:
						 int intVal = rslt.getInt(i);
						 val[i-1] = new Integer(intVal);
						 break;
					 case Types.BIGINT:
						 long longVal = rslt.getLong(i);
						 val[i-1] = new Long(longVal);
						 break;
					 case Types.REAL:
						 float realVal = rslt.getFloat(i);
						 val[i-1] = new Float(realVal);
						 break;
					 case Types.DOUBLE:
	                     double dblVal = rslt.getDouble(i);
						 val[i-1] = new Double(dblVal);
	                    break;
					 case Types.FLOAT:
	                     double dblVal2 = rslt.getDouble(i);
						 val[i-1] = new Double(dblVal2);
	                    break;
	                    
					 case Types.DECIMAL:
							 BigDecimal bigDecimalVal = rslt.getBigDecimal(i);
							 val[i-1] = bigDecimalVal;
							 break;
					 case Types.NUMERIC:
						 BigDecimal bigDecimalVal2 = rslt.getBigDecimal(i);
						 val[i-1] = bigDecimalVal2;
						 break;
					 case Types.DATE:
						 Date dt = rslt.getDate(i);
						 val[i-1] = dt;
						 break;
					 case Types.TIME:
						 Date dt2 = rslt.getTime(i);
						 val[i-1] = dt2;
						 break;
					 case Types.TIMESTAMP:
						 Timestamp timeStmp = rslt.getTimestamp(i);
						 val[i-1] = timeStmp;
						 break;
	                    
					/* case Types.TIMESTAMP:
						 Timestamp tmStmp = rslt.getTimestamp(i);
						 Calendar start = Calendar.getInstance();
						 String timStmpVal = null;
						 if(tmStmp!=null)
						 {
						 start.setTimeInMillis( tmStmp.getTime() );
						// System.out.println("Start:"+start);
						 SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						 timStmpVal = f.format(tmStmp);
						 }
						
						 val[i-1] = timStmpVal;
						 break;*/
					default:
						val[i-1] = null;
						break;
					}
					
					
				}
				
				data.add(val);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return data;
	}
	
	public List<Map<String,String>> getQueryDataAsStringByColumnName(String query)
	{
		List<Map<String,String>> data = new ArrayList<Map<String,String>>();
		
		DBConnection dbConnection = new DBConnection(this.dataSource);
		Connection conn = dbConnection.getConnection();
		Statement stmt =null;
		try {
			stmt = conn.createStatement();
			ResultSet rslt = stmt.executeQuery(query);
			ResultSetMetaData rsltMtDt = rslt.getMetaData();
			int colCount = rsltMtDt.getColumnCount();
			while(rslt.next())
			{
				Map<String,String> colData = new HashMap<String,String>();
				for(int i=1;i<=colCount;i++)
				{
					byte[] bytesDt = null;
					String byteArrStr = null; 
					String columnName = rsltMtDt.getColumnLabel(i);
					int sqlType = rsltMtDt.getColumnType(i);
				
					switch (sqlType) {
					
					 case Types.CHAR:
						 String charvalue= rslt.getString(i);
						 if (charvalue== null)
						 {
							 colData.put(columnName, "");
						 }
						 else
						 {
							 colData.put(columnName, rslt.getString(i));
						 }
						 break;
					 case Types.VARCHAR:
						colData.put(columnName, rslt.getString(i));
						break;
					 case Types.NVARCHAR:
						String strvalue= rslt.getString(i);
						if (strvalue== null)
						{
							colData.put(columnName, "");
						}	
						else
						{
							colData.put(columnName, rslt.getString(i).trim());
						}	
												
						break;
					 case Types.LONGVARCHAR:
						 colData.put(columnName, rslt.getString(i));
						 break;
					 case Types.BINARY:
						 bytesDt = rslt.getBytes(i);
						 byteArrStr = new String(bytesDt, StandardCharsets.UTF_8);
						 colData.put(columnName, byteArrStr);
						 break;
					 case Types.VARBINARY:
						 bytesDt = rslt.getBytes(i);
						 byteArrStr = new String(bytesDt, StandardCharsets.UTF_8);
						 colData.put(columnName, byteArrStr);
						 break;
					 case Types.LONGVARBINARY:
						 bytesDt = rslt.getBytes(i);
						 byteArrStr = new String(bytesDt, StandardCharsets.UTF_8);
						 colData.put(columnName, byteArrStr);
						 break;
						 
					 case Types.BIT:
						 boolean bitVal = rslt.getBoolean(i);
						 String bitValStr = new Boolean(bitVal).toString();
						 colData.put(columnName, bitValStr);
						 break;
					 case Types.TINYINT:
						 short shortVal = rslt.getShort(i);
						 String shortValStr = new Short(shortVal).toString();
						 colData.put(columnName, shortValStr);
						 break;
					 case Types.SMALLINT:
						 short smallIntVal = rslt.getShort(i);
						 String shortValStr2 = new Short(smallIntVal).toString();
						 colData.put(columnName, shortValStr2);
						 break;
					 case Types.INTEGER:
						 int intVal = rslt.getInt(i);
						 //val[i-1] = new Integer(intVal).toString();
						 colData.put(columnName, new Integer(intVal).toString());
						 break;
					 case Types.BIGINT:
						 long longVal = rslt.getLong(i);
						// val[i-1] = new Long(longVal).toString();
						 colData.put(columnName, new Long(longVal).toString());
						 break;
					 case Types.REAL:
						 float realVal = rslt.getFloat(i);
						// val[i-1] = new Float(realVal).toString();
						 colData.put(columnName,new Float(realVal).toString());
						 break;
					 case Types.DOUBLE:
	                     double dblVal = rslt.getDouble(i);
						// val[i-1] = new Double(dblVal).toString();
						 colData.put(columnName,new Double(dblVal).toString());
	                    break;
					 case Types.FLOAT:
	                     double dblVal2 = rslt.getDouble(i);
						// val[i-1] = new Double(dblVal2).toString();
						 colData.put(columnName,new Double(dblVal2).toString());
	                    break;
	                    
					 case Types.DECIMAL:
							 BigDecimal bigDecimalVal = rslt.getBigDecimal(i);
							 String biDecimalValStr = null;
							 if(bigDecimalVal!=null)
							 {
								 biDecimalValStr = bigDecimalVal.toString();
							 }
							 colData.put(columnName,biDecimalValStr);
							 break;
					 case Types.NUMERIC:
						 BigDecimal bigDecimalVal2 = rslt.getBigDecimal(i);
						 String biDecimalValStr2 = null;
						 if(bigDecimalVal2!=null)
						 {
							 biDecimalValStr2 = bigDecimalVal2.toString();
						 }
						 colData.put(columnName,biDecimalValStr2);
						 break;
						 
					 case Types.DATE:
						 Date dt = rslt.getDate(i);
						 String dtValStr  = null;
						 if(dt!=null)
						 {
							 dtValStr = dt.toString();
							
						 }
						 colData.put(columnName,dtValStr);
						 break;
					 case Types.TIME:
						 Date dt2 = rslt.getTime(i);
						 String dtValStr2  = null;
						 if(dt2!=null)
						 {
							 dtValStr = dt2.toString();
							
						 }
						 colData.put(columnName,dtValStr2);
						 break;
					 case Types.TIMESTAMP:
						 Timestamp timeStmp = rslt.getTimestamp(i);
						 String timeStampValStr = null;
						 
						 if(timeStmp!=null)
						 {
						 Date dt3 = new Date(timeStmp.getTime() + (timeStmp.getNanos() / 1000000));
						 timeStampValStr = dt3.toString();
						 }
						 colData.put(columnName,timeStampValStr);
						 break;
	                    
					/* case Types.TIMESTAMP:
						 Timestamp tmStmp = rslt.getTimestamp(i);
						 Calendar start = Calendar.getInstance();
						 String timStmpVal = null;
						 if(tmStmp!=null)
						 {
						 start.setTimeInMillis( tmStmp.getTime() );
						// System.out.println("Start:"+start);
						 SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						 timStmpVal = f.format(tmStmp);
						 }
						
						 val[i-1] = timStmpVal;
						 break;*/
					default:
						colData.put(columnName,null);
						break;
					}
					
					
				}
				
				data.add(colData);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return data;
	}
	
	public List<Map<String,Object>> getQueryDataAsObjectByColumnName(String query)
	{
		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		
		DBConnection dbConnection = new DBConnection(this.dataSource);
		Connection conn = dbConnection.getConnection();
		Statement stmt =null;
		try {
			stmt = conn.createStatement();
			ResultSet rslt = stmt.executeQuery(query);
			ResultSetMetaData rsltMtDt = rslt.getMetaData();
			int colCount = rsltMtDt.getColumnCount();
			while(rslt.next())
			{
				Map<String,Object> colData = new HashMap<String,Object>();
				for(int i=1;i<=colCount;i++)
				{
					byte[] bytesDt = null;
					String columnName = rsltMtDt.getColumnLabel(i);
					int sqlType = rsltMtDt.getColumnType(i);
				
					switch (sqlType) {
					
					 case Types.CHAR:
						 colData.put(columnName, rslt.getString(i));
						 break;
					 case Types.VARCHAR:
						//	val[i-1] = rslt.getString(i);
							colData.put(columnName, rslt.getString(i));
							break;
					 case Types.NVARCHAR:
						 colData.put(columnName, rslt.getString(i));
							break;
					 case Types.LONGVARCHAR:
						 //val[i-1] = rslt.getString(i);
						 colData.put(columnName, rslt.getString(i));
						 break;
					 case Types.BINARY:
						 bytesDt = rslt.getBytes(i);
						// byteArrStr = new String(bytesDt, StandardCharsets.UTF_8);
						 colData.put(columnName, bytesDt);
						 break;
					 case Types.VARBINARY:
						 bytesDt = rslt.getBytes(i);
						 //byteArrStr = new String(bytesDt, StandardCharsets.UTF_8);
						 colData.put(columnName, bytesDt);
						 break;
					 case Types.LONGVARBINARY:
						 bytesDt = rslt.getBytes(i);
						 //byteArrStr = new String(bytesDt, StandardCharsets.UTF_8);
						 colData.put(columnName, bytesDt);
						 break;
						 
					 case Types.BIT:
						 boolean bitVal = rslt.getBoolean(i);
						 //String bitValStr = new Boolean(bitVal).toString();
						 colData.put(columnName, bitVal);
						 break;
					 case Types.TINYINT:
						 short shortVal = rslt.getShort(i);
						 //String shortValStr = new Short(shortVal).toString();
						 colData.put(columnName, shortVal);
						 break;
					 case Types.SMALLINT:
						 short smallIntVal = rslt.getShort(i);
						// String shortValStr2 = new Short(smallIntVal).toString();
						 colData.put(columnName, smallIntVal);
						 break;
					 case Types.INTEGER:
						 int intVal = rslt.getInt(i);
						 //val[i-1] = new Integer(intVal).toString();
						 colData.put(columnName, new Integer(intVal));
						 break;
					 case Types.BIGINT:
						 long longVal = rslt.getLong(i);
						// val[i-1] = new Long(longVal).toString();
						 colData.put(columnName, new Long(longVal));
						 break;
					 case Types.REAL:
						 float realVal = rslt.getFloat(i);
						// val[i-1] = new Float(realVal).toString();
						 colData.put(columnName,new Float(realVal));
						 break;
					 case Types.DOUBLE:
	                     double dblVal = rslt.getDouble(i);
						// val[i-1] = new Double(dblVal).toString();
						 colData.put(columnName,new Double(dblVal));
	                    break;
					 case Types.FLOAT:
	                     double dblVal2 = rslt.getDouble(i);
						// val[i-1] = new Double(dblVal2).toString();
						 colData.put(columnName,new Double(dblVal2));
	                    break;
	                    
					 case Types.DECIMAL:
							 BigDecimal bigDecimalVal = rslt.getBigDecimal(i);
							 colData.put(columnName,bigDecimalVal);
							 break;
					 case Types.NUMERIC:
						 BigDecimal bigDecimalVal2 = rslt.getBigDecimal(i);
						 colData.put(columnName,bigDecimalVal2);
						 break;
					 case Types.DATE:
						 Date dt = rslt.getDate(i);
						 colData.put(columnName,dt);
						 break;
					 case Types.TIME:
						 Date dt2 = rslt.getTime(i);
						 colData.put(columnName,dt2);
						 break;
					 case Types.TIMESTAMP:
						 Timestamp timeStmp = rslt.getTimestamp(i);
						 colData.put(columnName,timeStmp);
						 break;
	                    
					/* case Types.TIMESTAMP:
						 Timestamp tmStmp = rslt.getTimestamp(i);
						 Calendar start = Calendar.getInstance();
						 String timStmpVal = null;
						 if(tmStmp!=null)
						 {
						 start.setTimeInMillis( tmStmp.getTime() );
						// System.out.println("Start:"+start);
						 SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						 timStmpVal = f.format(tmStmp);
						 }
						
						 val[i-1] = timStmpVal;
						 break;*/
					default:
						colData.put(columnName,null);
						break;
					}
					
					
				}
				
				data.add(colData);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return data;
	}
	
	public boolean executeInsertUpdateStatements(String query) {
		
		DBConnection dbConnection = new DBConnection(this.dataSource);
		Connection conn = dbConnection.getConnection();
		Statement stmt = null;
		boolean stmtRun = false;
		try {
			
			stmt = conn.createStatement();
			int i = stmt.executeUpdate(query);
			if (i > 0) {
				stmtRun = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmtRun;
	}
	
	public void executeStoredProcedureWithoutParam(String storedProc) {
		
		DBConnection dbConnection = new DBConnection(this.dataSource);
		Connection conn = dbConnection.getConnection();
		CallableStatement callStmt = null;
		try {
			
			callStmt = conn.prepareCall(storedProc);
			callStmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Object> executeProcedureWithOutputParam(String storedProc, String requestorName,
            String requestorAddress, String requestorZip) {
        
        DBConnection dbConnection = new DBConnection(this.dataSource);
        Connection connection = dbConnection.getConnection();
        CallableStatement callStmt = null;
        Boolean callStmtRun = new Boolean(false);
        
        Map<String, Object> output = new HashMap<>();
        
        try {
            
            System.out.println("Stored procedure :" +storedProc);
            callStmt = connection.prepareCall(storedProc);
            System.out.println("Stored procedure :" +callStmt);
            callStmt.setString(1, (StringUtils.isEmpty(requestorName)) ? null : requestorName);            // IN requestorName
            callStmt.setString(2, (StringUtils.isEmpty(requestorAddress)) ? null : requestorAddress);        // IN requestorAddress
            callStmt.setString(3, null);                                                                        // IN requestorCity
            callStmt.setString(4, null);                                                                        // IN requestorState
            callStmt.setString(5, (StringUtils.isEmpty(requestorZip)) ? null : requestorZip);                // IN requestorZip
            callStmt.setString(6, null);                                                                        // IN requestorPhone
            callStmt.setString(7, null);                                                                        // IN requestorUser
            callStmt.registerOutParameter(8, Types.LONGVARCHAR);                                                 // OUT lookUpId
            callStmt.registerOutParameter(9, Types.INTEGER);                                                    // OUT activeCustomersCount
            
            callStmtRun = callStmt.execute();
            
            output.put("callStmtRun", callStmtRun);
            output.put("lookUpId", callStmt.getLong(8));
            output.put("activeCustomersCount", callStmt.getInt(9));

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return output;
    }

}
