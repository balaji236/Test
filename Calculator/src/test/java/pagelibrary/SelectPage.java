package pagelibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import testBase.TestBase;

public class SelectPage extends TestBase{
	
	public void principle(String amount){
		
		driver.findElement(By.name(properties.getProperty("principle"))).sendKeys(amount);;
	
	}
	public void interest(String interes){
		driver.findElement(By.name(properties.getProperty("Interest"))).sendKeys(interes);
	}
	
	public void tenure(String tenur){
		driver.findElement(By.name(properties.getProperty("Tenure"))).sendKeys(tenur);
	}
	public void Duration(String durat){
		Select element=new Select(driver.findElement(By.name(properties.getProperty("SelectA"))));
		WebDriverWait wait= new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name(properties.getProperty("SelectA"))));
		element.selectByVisibleText(durat);
	}
	public void submit(){
		
		driver.findElement(By.xpath(properties.getProperty("Submit"))).click();
	}
	
	

}
