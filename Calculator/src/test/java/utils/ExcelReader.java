package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import testBase.TestBase;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelReader  {
	public String path;
	FileInputStream fis;
	HSSFWorkbook workbook;
	HSSFSheet sheet;
	HSSFRow row;
	HSSFCell cell;

	public ExcelReader(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new HSSFWorkbook(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			HSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equals(colName)) {
					col_Num = i;
				}
			}
			row = sheet.getRow(rowNum - 1);
			HSSFCell cell = row.getCell(col_Num);
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cell.getStringCellValue();
	}
	
	public String getCellData(String sheetName, int colName, int rowNum) {
		try {
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			HSSFRow row = sheet.getRow(0);
			row = sheet.getRow(rowNum - 1);
			HSSFCell cell = row.getCell(colName);
			
			if (cell == null)
			{
			   return"";

			}
			else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

		//return "";
	}

	public String setCellData(String sheetName, String colName, int rowNum, String value) {
		try {
			
			int col_Num = 0;
			int index = workbook.getSheetIndex(sheetName);
			sheet = workbook.getSheetAt(index);
			HSSFRow row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				if (row.getCell(i).getStringCellValue().equals(colName)) {
					col_Num = i;
				}
			}
			row = sheet.getRow(rowNum - 1);
			if(row==null){
				row = sheet.createRow(rowNum - 1);
			}

			HSSFCell cell = row.getCell(col_Num);
			if(cell==null){
				cell = row.createCell(index);
			}

			cell.setCellValue(value);
			//fis.close();
					

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	public void setCellData(String sheetName,  int rowNum,int colName, String value) throws IOException {
		//int col_Num = 0;
		int index = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(index);
		HSSFRow row = sheet.getRow(rowNum);
		//Row row = sheet.getRow(0);
	    sheet = workbook.getSheetAt(0);
		int number = sheet.getLastRowNum();
		row=sheet.getRow(rowNum);
    	Cell cell= row.createCell(colName);
    	
	    cell.setCellType(Cell.CELL_TYPE_STRING);
	    cell.setCellValue(value);
  fis.close();
	    
	    FileOutputStream fileOut = new FileOutputStream("E:\\Salenium_study\\Calculator\\src\\test\\java\\testData\\Login.xls");
 
	    workbook.write(fileOut);
	    
	    fileOut.close();
		
	}

	   
	
		
	

	
		
	
	public int getRowCount(String sheetName) {
		try {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = workbook.getSheetAt(index);
				int number = sheet.getLastRowNum() + 1;
				return number;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getColumnCount(String sheetName) {
		try {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = workbook.getSheet(sheetName);
				row = sheet.getRow(0);
				return row.getLastCellNum();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void main(String[] args) {
		String path = System.getProperty("user.dir") + "//src//test//resources//Login.xlsx";
		ExcelReader obj = new ExcelReader(path);
		// System.out.println(obj.getCellData("Login", "UserName", 4));

		System.out.println(obj.getRowCount("Login"));
		
		System.out.println(obj.getColumnCount("Login"));
		
		System.out.println(obj.getCellData("Login", 2, 3));
	}

}
