package com.voidmain.progaurd.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class GetConnection {

	private static Connection con=null;
	
	public static Connection getConnection()
	{
		try {
			
			InputStream fis=GetConnection.class.getResourceAsStream("/dbdetails.properties");
			
			Properties prop=new Properties();
			
			prop.load(fis);
			
			String username=prop.getProperty("username");
			String  password=prop.getProperty("password");
			String url=prop.getProperty("url");
			String driver=prop.getProperty("driver");
			
			Class.forName(driver);
			
			con=DriverManager.getConnection(url, username,password);
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return con;
	}
}
