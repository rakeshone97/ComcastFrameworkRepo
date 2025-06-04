package com.comcast.crm.generic.FileUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	
	///Here we are not Taking main methods because we won't run this methods without Test Scripts.
	///We are just Writing the methods to use/Call in Test Scripts.
	///
	///Now File is in the Framework so write the path for this,
		///Here (.) -->means navigate/Go to parent directories/project and (/)--> go and find
		///the folder that given.
	
	public String getDataFromExcel(String sheetName,int rowNum,int cellNum) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./testScriptData/Book1.xlsx");
				Workbook wb= WorkbookFactory.create(fis);
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
				
		return data;
	}
	
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./testScriptData/Book1.xlsx");
		Workbook wb= WorkbookFactory.create(fis);	
		int rowCount= wb.getSheet(sheetName).getLastRowNum();
		return rowCount;
	}
	
	public void setDataIntoExcel(String sheetName,int rowNum,int cellNum, String data) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis=new FileInputStream("./testScriptData/Book1.xlsx");
		Workbook wb= WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		
		FileOutputStream fos = new FileOutputStream("./testScriptData/Book1.xlsx");
		wb.write(fos);
		wb.close();
				
	}
	

}
