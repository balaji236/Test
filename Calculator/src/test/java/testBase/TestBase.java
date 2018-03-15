package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ExcelReader;
import utils.ExcelWriter;

public class TestBase {
	public static Properties properties;
	static FileInputStream obj;
	public static WebDriver driver;
	public static void loadData() throws IOException{
		properties=new Properties();
		//File f=new File("E:\\Salenium_study\\src\\test\\java\\Calculator\\properties\\Test1.properties");
		File f=new File("E:\\Salenium_study\\Calculator\\src\\test\\java\\Calculator\\properties\\Test1.properties");
		obj=new FileInputStream(f);
		properties.load(obj);
	}
	public static String getobj(String Data) throws IOException{
		loadData();
		String data = properties.getProperty(Data);
		return data;
	}
	public static void setup() throws IOException{
		loadData();
		
		selectBrowser(properties.getProperty("browser"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(properties.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
	}
	public static void selectBrowser(String browser){
	
			if(browser.equalsIgnoreCase("firefox")){
				//https://github.com/mozilla/geckodriver/releases
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver",  "C:/Users/balaji236/Downloads/geckodriver-v0.19.0-win64/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("chrome")){
				//https://chromedriver.storage.googleapis.com/index.html
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
				driver = new ChromeDriver();
			}
			
			

}
	//public static void main(String [] args) throws IOException{
	//	TestBase.setup();
		
	//}
}
