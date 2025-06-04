package com.comcast.crm.generic.FileUtility;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	
	///Here we are not Taking main methods because we won't run this methods without Test Scripts.
	///We are just Writing the methods to use/Call in Test Scripts.
	
		public String getDataFromJsonFile(String key) throws IOException, ParseException
		{
			FileReader fileR=new FileReader("./configAppData/appCommonData.jason");
			JSONParser parse=new JSONParser();
			Object obj=parse.parse(fileR);
			JSONObject map=(JSONObject)obj;
			String data=(String) map.get(key);

			return data;
			
		}
}
