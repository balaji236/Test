package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.poi.ss.usermodel.Cell;
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

public class ExcelReader2 {
	public String path;
	FileInputStream fis;
	HSSFWorkbook workbook;
	HSSFSheet sheet;
	HSSFRow row;
	HSSFCell cell;

	public ExcelReader2(String path) {
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
	public String setCellData(String sheetName, int colName, int rowNum, String value) throws IOException {
		//int col_Num = 0;
		int index = workbook.getSheetIndex(sheetName);
		sheet = workbook.getSheetAt(index);
		HSSFRow row = sheet.getRow(rowNum);
		if(row==null){
			row = workbook.getSheet(sheetName).createRow(rowNum);
		}
		HSSFCell cell = row.getCell(colName);
		if(cell==null){
			cell = row.createCell(colName);
		}
		cell.setCellValue(value);
		FileOutputStream outputStream = new FileOutputStream(path);

	    //write data in the excel file

		workbook.write(outputStream);

	    //close output stream

	    outputStream.close();
		return value;
		
	}

	public void writeExcel(String path) throws IOException{
		FileOutputStream outputStream = new FileOutputStream(path);

	    //write data in the excel file

		workbook.write(outputStream);

	    //close output stream

	    outputStream.close();
		
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
