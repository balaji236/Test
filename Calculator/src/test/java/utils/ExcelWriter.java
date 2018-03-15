package utils;



import java.io.FileInputStream;
import java.io.FileOutputStream;
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

public class ExcelWriter {
	public String path;
	FileInputStream inputStream;
	HSSFWorkbook workbook;
	HSSFSheet sheet;
	HSSFRow row;
	HSSFCell cell;

	public ExcelWriter(String path) {
		this.path = path;
		try {
			inputStream = new FileInputStream(path);
			workbook = new HSSFWorkbook(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public void setCellData(String sheetName, String colName, int rowNum, String value) {
		try {
			inputStream = new FileInputStream(path);
			workbook = new HSSFWorkbook(inputStream);
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
			cell.setCellValue(value);
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(path);

		    //write data in the excel file

			workbook.write(outputStream);

		    //close output stream

		    outputStream.close();

			

		} catch (Exception e) {
			e.printStackTrace();
		}

		
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
