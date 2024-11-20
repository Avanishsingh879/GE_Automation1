package com.genpact.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.genpact.utils.DBDataSourcesConfig;

public class DBConnection {

	private String driverClass;
	private String connectionURL;
	private DBSource dbSource;
	
	public DBConnection(DBSource dbSource)
	{
		this.dbSource = dbSource;
		setConnectionURL();
	}
	
//	public DBConnection(String dataSourceName) throws IOException, ParseException
//	{
//		DBSource dbSOurce = new DBSource(dataSourceName);
//		List<DBSource> dbSources = DBDataSourcesConfig.getDBSources();
//		DBSource expDBS = dbSources.get(dbSources.indexOf(dbSOurce));
//		this.dbSource = expDBS;
//		setConnectionURL();
//	}
	
	private void setConnectionURL()
	{
		switch (dbSource.getVendor()) {
		case ORACLE:
			driverClass = "oracle.jdbc.driver.OracleDriver";
			connectionURL = "jdbc:oracle:thin:@"+dbSource.getHostName()+":"+dbSource.getPortNumber()+":"+dbSource.getDbName();
			break;
		case SQL_SERVER:
			
			break;
		case MYSQL:
			driverClass = "com.mysql.jdbc.Driver";
			connectionURL = "jdbc:mysql://"+dbSource.getHostName()+":"+dbSource.getPortNumber()+"/"+dbSource.getDbName();
			System.out.println("Conccetion URL:"+connectionURL);
			break;
			
		case MARIADB:
			driverClass = "org.mariadb.jdbc.Driver";
			connectionURL="jdbc:mariadb://"+dbSource.getHostName()+":"+dbSource.getPortNumber()+"/"+dbSource.getDbName();

		default:
			break;
		}
	}

	
	public Connection getConnection()
	{
		Connection connection = null;
		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(connectionURL,dbSource.getUserName(),dbSource.getPassword());
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
