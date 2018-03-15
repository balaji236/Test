package Calculator.Calculator;

import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

public class ExampleTest3  extends TestBase{
	 public static List<String> res = new ArrayList<String>();
	 public String location="E:\\Salenium_study\\Calculator\\src\\test\\java\\testData\\Login.xls";
	 int rowid=2;
	 public static String rest;
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
	/* public static Object[][] setData(String ExcelName ,String testcase) {
		  ExcelReader Data = new ExcelReader("E:\\Salenium_study\\Calculator\\src\\test\\java\\testData\\"+ExcelName);
		  //ExcelUtils Data= new ExcelUtils(DD);)
		  int rowNum=Data.getRowCount(testcase);
		  System.out.println(rowNum);
		  int colNum=Data.getColumnCount(testcase);
		  Object sampleData[][]=new Object[rowNum-1][colNum];
		  for (int i=2;i<=rowNum;i++){
			  for (int j=0;j<colNum;j++){
				  sampleData[i-2][j]= Data.setCellData(testcase, j, i, rest);
			  }
			  
		  }
		  return sampleData;
	  }
	
	  @DataProvider
	  public Object[][] setData() throws IOException{
			Object[][] data = setData("Login.xls", "Login");
			return data;
	}*/
	  @DataProvider
	  public Object[][] loginData(){
			Object[][] data = getData("Login.xls", "Login");
			return data;
	}
	/*  @DataProvider public Object[][] dp() throws IOException {
		  List<Object[]> result = Lists.newArrayList();
		  result.addAll(Arrays.asList(loginData()));
		  result.addAll(Arrays.asList(setData()));
		  return result.toArray(new Object[result.size()][]);
		}*/
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
		//	ExcelReader enter = new ExcelReader("Location");
			
			
			//enter.setCellData("Login", "EMI", rowid, res);
		
			
			
			
			
	  }
	 /* @DataProvider
	  public Object[][] results(){
		  

		  Object [][] objArray = new Object[res.size()][];

		  for(int i=0;i< res.size();i++){
		     objArray[i] = new Object[1];
		     objArray[i][0] = res.get(i);
		  } 

		  return objArray;
	  }*/
	 
	 @AfterTest
	 public void after() throws IOException{
		 System.out.println("Testcompleted");
		 
		 for(String s:res){
			 System.out.println(s);
		 }
		 
		 ExcelReader enter = new ExcelReader("E:\\Salenium_study\\Calculator\\src\\test\\java\\testData\\Login.xls");
		 int rowNum=enter.getRowCount("Login");
		
	//	 for(String s:res){
	//		 enter.setCellData("Login", colNum, RowNum, s);
	//	 }
		 try{
			 Iterator<String>a = res.iterator(); 
				//String man =  a.next();
		         
				 while (a.hasNext()){
					 
				 for (int i=1;i<=rowNum;i++){
					 
						  enter.setCellData("Login", i, 6,a.next());
						  
						  
					  }
				 
				 }
		 }
		 catch(Exception e){
			 System.out.println("Test completed");
		 }
		 
		 driver.close(); 
	 //}
	//enter.writeExcel("E:\\Salenium_study\\Calculator\\src\\test\\java\\testData\\Login.xls");
	 }
	 
}



