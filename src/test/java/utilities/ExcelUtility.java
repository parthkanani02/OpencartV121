package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
    
    public XSSFWorkbook workbook; //Excel WorkBook
    public XSSFSheet worksheet; //Excel Sheet
    public XSSFCell cell; //Excel cell
    public XSSFRow row; //Excel row
    public  FileInputStream fi; 
    public  FileOutputStream fo;
    String path;
    
    public ExcelUtility(String path) {
    	this.path = path;
    }
    
    public int getRowCount(String sheetname) throws IOException{
    	fi =  new FileInputStream(path);
    	workbook = new XSSFWorkbook(fi);
    	worksheet = workbook.getSheet(sheetname);
    	int rowcount = worksheet.getLastRowNum();
    	workbook.close();
    	fi.close();
    	return rowcount;
    }
    
    public int getCellCount(String sheetname, int rownum) throws IOException {
		
    	fi = new FileInputStream(path);
    	workbook =  new XSSFWorkbook(fi);
    	worksheet = workbook.getSheet(sheetname);
    	row = worksheet.getRow(rownum);
    	int cellcount = row.getLastCellNum();
    	workbook.close();
    	fi.close();
    	return cellcount;
	}
    
    public String getCellData(String sheetname,int rownum,int colnum) throws IOException{
    	fi =  new FileInputStream(path);
    	workbook = new XSSFWorkbook(fi);
    	worksheet = workbook.getSheet(sheetname);
    	row=worksheet.getRow(rownum);
    	cell= row.getCell(colnum);
    	
    	String data;
    	try {
    		//data=cell.toString();
    		DataFormatter formatter= new DataFormatter();
    		data = formatter.formatCellValue(cell);
    	}
    	catch (Exception e) {
    		data="";
    	}
    	workbook.close();
    	fi.close();
    	return data;
    }
    
    public void setCellData(String sheetname,int rownum,int colnum,String data) throws IOException{
    	// IF file not exists then create new one
    	File xlfile = new File(path);
    	if(!xlfile.exists())
    	{
    		workbook = new XSSFWorkbook();
    		fo= new FileOutputStream(path);
    		workbook.write(fo);
    	}
    	
    	fi = new FileInputStream(path);
    	workbook = new XSSFWorkbook(fi);
    	
    	// If Sheet is not exists then create new one
    	if(workbook.getSheetIndex(sheetname)==-1)
    		workbook.createSheet(sheetname);
    	worksheet = workbook.getSheet(sheetname);
    	
    	// If row is not exists then create new one
    	if(worksheet.getRow(rownum)==null)
    		worksheet.createRow(rownum);
    	row = worksheet.getRow(rownum);
    	
    	cell = row.createCell(colnum);
    	cell.setCellValue(data);
    	fo = new FileOutputStream(path);
    	workbook.write(fo);
    	workbook.close();
    	fi.close();
    	fo.close();

    }
}
