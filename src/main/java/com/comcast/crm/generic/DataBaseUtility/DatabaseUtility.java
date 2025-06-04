package com.comcast.crm.generic.DataBaseUtility;

import java.awt.Taskbar.State;
import java.beans.Statement;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
	Connection conn;//To close from other method that's why I am Declare Globally
	
	public void getDbConnection(String url,String username, String password) throws SQLException
	{
		try {
		Driver driver= new Driver();
		DriverManager.registerDriver(driver);
		
		conn=DriverManager.getConnection(url,username,password);
		}catch (Exception e) {
			
		}
	}
	public void getDbConnection() throws SQLException
	{
		try {
		Driver driver= new Driver();
		DriverManager.registerDriver(driver);
		
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		}catch (Exception e) {
			
		}
	}
	
	
	public void closeDbConnection() throws SQLException
	{	try {
		conn.close();
		}catch (Exception e) 
		{
		}
	}
	
	
	public ResultSet executeSelectQuery(String query) throws SQLException
	{
		ResultSet result=null;
		try {
	java.sql.Statement stat=conn.createStatement();
	 result= stat.executeQuery(query);
			}catch (Exception e)
				{	}
		return result;
	}
	public int executeNonSelectQuery(String query) throws SQLException
	{
		int result=0;
			try {
			java.sql.Statement stat=conn.createStatement();
			result= stat.executeUpdate(query);
			}catch (Exception e)
				{	}
		return result;
	}
	
	
	
	

}
