package com.comcast.crm.generic.FileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	
	///Here we are not Taking main methods because we won't run this methods without Test Scripts.
	///We are just Writing the methods to use/Call in Test Scripts.
	
		public String getDataFromPropertiesFile(String key) throws IOException
		{
			FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
			Properties pObj=new Properties();
			pObj.load(fis);
			String data=pObj.getProperty(key);
			
			return data;
			
		}
}
