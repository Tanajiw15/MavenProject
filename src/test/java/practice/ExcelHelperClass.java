package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelperClass {
	
	String path=null;
	public FileInputStream fis;
	public XSSFWorkbook wb;
	public XSSFSheet sh;
	public XSSFRow row;
	public XSSFCell cell;
	
	public ExcelHelperClass(String path)
	{
		this.path=path;
	}
 public int rowCount(String sheetName) throws IOException
{
    fis=new FileInputStream(path);
	wb=new XSSFWorkbook(fis);
	sh=wb.getSheet(sheetName);
	int rowcount = sh.getLastRowNum();
	wb.close();
	fis.close();
    return rowcount;
	}
 public int cellCount(String sheetName,int rowNo) throws IOException
 {
    fis=new FileInputStream(path);
 	wb=new XSSFWorkbook(fis);
    sh=wb.getSheet(sheetName);
 	row=sh.getRow(rowNo);
 	int cellcount = row.getLastCellNum();
 	wb.close();
 	fis.close();
    return cellcount;
 	}
 public String cellData(String sheetName,int rowNo,int colNo) throws IOException
 {
    fis=new FileInputStream(path);
 	wb=new XSSFWorkbook(fis);
 	sh=wb.getSheet(sheetName);
 	row=sh.getRow(rowNo);
 	cell=row.getCell(colNo);
 	String dat;
 	DataFormatter formatter=new DataFormatter();
 	try {
 	dat = formatter.formatCellValue(cell);
 	}
 	catch(Exception e) 
 	{
 		dat=" ";
 	}
 	wb.close();
 	fis.close();
    return dat;
 	}	
}
