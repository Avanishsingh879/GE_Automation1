package com.genpact.db;

public enum Vendor {
	
ORACLE("ORACLE"),SQL_SERVER("SQL_SERVER"),MYSQL("MYSQL"),MARIADB("MARIADB");

private String value;
	
	Vendor(String value) {
		this.value = value;
	}
	
	public String getValue()
	{
		return this.value;
	}

}
