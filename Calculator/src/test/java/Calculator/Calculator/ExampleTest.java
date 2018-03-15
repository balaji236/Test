package Calculator.Calculator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pagelibrary.SelectPage;
//import pagelibrary.SelectPage;
import testBase.TestBase;
import utils.ExcelReader;

public class ExampleTest  extends TestBase{
public	Map<String, String> newData = new HashMap<>();
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
			String res=driver.findElement(By.xpath(properties.getProperty("result"))).getText();
			driver.navigate().refresh();
			
			/*ExcelReader enter = new ExcelReader("E:\\Salenium_study\\Calculator\\src\\test\\java\\testData\\login.xls");
			enter.setCellData( "Login", "EMI", 1, res);
			enter.writeExcel(res);
			/*ExcelReader enter = new ExcelReader("E:\\Salenium_study\\Calculator\\src\\test\\java\\testData\\Login.xls");
			  //ExcelUtils Data= new ExcelUtils(DD);)
			  int rowNum=enter.getRowCount("Login");
			  System.out.println(rowNum);
			  int colNum=enter.getColumnCount("Login");
			
			for (int i=2;i<=rowNum;i++){
				  for (int j=0;j<colNum;j++){
            newData.put( new Integer(i).toString(), new String (res
                     ));
				  }
			}
			*/
			
	  }
	 
	 @AfterTest
	 public void after(){
		 System.out.println("Testcompleted");
		 Set keys = newData.keySet();
	        Iterator itr = keys.iterator();
	 
	        String key;
	        String value;
	        while(itr.hasNext())
	        {
	            key = (String)itr.next();
	            value = newData.get(key);
	            System.out.println(key + " - "+ value);
	        }
		
	 }
	
}
