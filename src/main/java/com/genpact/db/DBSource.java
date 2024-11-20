package com.genpact.db;

public class DBSource {

	private Vendor vendor;
	private String hostName;
	private int portNumber;
	private String dbName;
	private String userName;
	private String password;
	
	public DBSource()
	{
		
	}
	public DBSource(Vendor vendor,String hostName,int portNumber,String dbName,String userName,String password)
	{
		this.vendor = vendor;
		this.hostName = hostName;
		this.portNumber = portNumber;
		this.dbName = dbName;
		this.userName = userName;
		this.password = password;
	}
	
	public Vendor getVendor() {
		return vendor;
	}
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public int getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
