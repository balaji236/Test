package Calculator.Calculator;

import org.testng.annotations.Test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
//import pagelibrary.SelectPage;
import testBase.TestBase;
import utils.ExcelReader;

public class ExampleTest2  extends TestBase{
	 public List<String> res = new ArrayList<String>();
	 public String location="E:\\Salenium_study\\Calculator\\src\\test\\java\\testData\\Login.xls";
	 int rowid=2;
	  @BeforeClass
	  public void init() throws IOException {
		 setup();
	
	  }
	  public static Object[][] getData(String ExcelName ,String testcase) {
		  ExcelReader Data = new ExcelReader("E:\\Salenium_study\\Calculator\\src\\test\\java\\testData\\"+ExcelName);
		  //ExcelUtils Data= new ExcelUtils(DD);)
		  int rowNum=Data.getRowCount(testcase);
		  System.out.println(rowNum);
		  int colNum=Data.getColumnCount(testcase);
		  Object sampleData[][]=new Object[rowNum-1][colNum];
		  for (int i=2;i<=rowNum;i++){
			  for (int j=0;j<colNum;j++){
				  sampleData[i-2][j]=Data.getCellData(testcase, j, i);
			  }
			  
		  }
		  return sampleData;
	  }
	

	  @DataProvider
	  public Object[][] loginData(){
			Object[][] data = getData("Login.xls", "Login");
			return data;
	}
	  @Test(dataProvider = "loginData")
	  public void f(String TestCaseNo, String Module,String amount, String interest,String tenure, String Duration, String EMI) throws InterruptedException, IOException {

			driver.findElement(By.name(properties.getProperty("Principle"))).sendKeys(amount);
			driver.findElement(By.name(properties.getProperty("Interest"))).sendKeys(interest);
			driver.findElement(By.name(properties.getProperty("Tenure"))).sendKeys(tenure);
			//driver.findElement(By.name("i")).sendKeys();
			Select element=new Select(driver.findElement(By.name(properties.getProperty("SelectA"))));
				
			WebDriverWait wait= new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.name(properties.getProperty("SelectA"))));
			element.selectByVisibleText(Duration);
			Thread.sleep(2000);
			driver.findElement(By.xpath(properties.getProperty("Submit"))).click();
			Thread.sleep(2000);
			res.add(driver.findElement(By.xpath(properties.getProperty("result"))).getText());
			String rest=driver.findElement(By.xpath(properties.getProperty("result"))).getText();
			driver.navigate().refresh();
			ExcelReader enter = new ExcelReader("Location");
			
			//enter.setCellData("Login", "EMI", rowid, res);
			enter.setCellData("Login", 7, rowid, rest);
			//enter.writeExcel("Location");
			
			rowid++;
			
			
	  }
	  @DataProvider
	  public Object[][] results(){
		  

		  Object [][] objArray = new Object[res.size()][];

		  for(int i=0;i< res.size();i++){
		     objArray[i] = new Object[1];
		     objArray[i][0] = res.get(i);
		  } 

		  return objArray;
	  }
	 
	 @AfterTest
	 public void after(){
		 System.out.println("Testcompleted");
		 for(String s:res){
			 System.out.println(s);
		 }
		 
		/* ExcelReader enter = new ExcelReader("E:\\Salenium_study\\Calculator\\src\\test\\java\\testData\\Login.xls");
		 int RowNum=enter.getRowCount("Login");
		 int colNum=6;
	//	 for(String s:res){
	//		 enter.setCellData("Login", colNum, RowNum, s);
	//	 }*/
		
		
		 
		 
		 
		 
	 }
	
}



