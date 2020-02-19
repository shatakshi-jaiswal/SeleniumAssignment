package Assignment;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Assignment.ObjectRepository.MakeMyTrip;
import Assignment.ObjectRepository.Yatra;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class AssignClass {
	public boolean isElementVisible(By locator,int waitTime,WebDriver driver){
		boolean flag = false;
		try{
			WebDriverWait wait = new WebDriverWait(driver,waitTime);
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			if(driver.findElement(locator).isDisplayed())
				flag= true;
			}
		catch (Exception e){
			System.out.println("Some Exception occured"+e.getMessage());
			flag = false;
		}
		return flag;
	}
	
	public boolean isElementPresent(By locator,int waitTime,WebDriver driver){
		boolean flag = false;
		try{
			WebDriverWait wait = new WebDriverWait(driver,waitTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			if(driver.findElement(locator).isDisplayed())
				flag= true;
			}
		catch (Exception e){
			System.out.println("Some Exception occured"+e.getMessage());
			flag = false;
		}
		return flag;
	}
	
	public WebElement getElement(By locator,int waitTime,WebDriver driver){
		WebElement we = null;
		AssignClass obj = new AssignClass();
		try{
			if(obj.isElementVisible(locator, waitTime, driver))
				we = driver.findElement(locator);
		}
		catch(Exception e){
			System.out.println("Element not found"+locator+"Exception"+e.getMessage());
		}
		return we;
	}
	public void safeClick(By locator,int waitTime,WebDriver driver){
		AssignClass obj = new AssignClass();
		try{
			WebElement we = obj.getElement(locator, waitTime, driver);
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			we.click();
		}
		catch(Exception e){
			System.out.println("Element not clickable"+locator+"Exception"+e.getMessage());
		}
	}
	public void sendKeysToField(By locator,int waitTime,WebDriver driver,String city){
		AssignClass obj = new AssignClass();
		try{
			WebElement we = obj.getElement(locator, waitTime, driver);
			Thread.sleep(2000);
			we.clear();
			Thread.sleep(2000);
			CharSequence[] keyToEnter = new String[] {city};
			we.sendKeys(keyToEnter);
		}
		catch(Exception e){
			System.out.println("Some exception occured"+locator+"Exception"+e.getMessage());
		}
	}
	public String getText(By locator,int waitTime,WebDriver driver){
		String text= "";
		AssignClass obj = new AssignClass();
		try{
			text = obj.getElement(locator, waitTime, driver).getText().trim();
		}
		catch (Exception e){
			System.out.println("Some Exception occurred"+e.getMessage());
		}
		return text;
	}
	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
		AssignClass obj = new AssignClass();		
		WebDriver driver = new ChromeDriver();
		String mmtPrice = "",yatraPrice="";
		
		DateFormat df = new SimpleDateFormat("MMM dd yyyy");
		Calendar calobj = Calendar.getInstance();
		String dateToday = df.format(calobj.getTime());
		
		//launch Make My trip
		driver.get("http://www.makemytrip.com/");
		Thread.sleep(2000);
		//Call click method to select one way 
		obj.safeClick(MakeMyTrip.onewayLink, 10, driver);
		//click on the from Text field
		obj.safeClick(MakeMyTrip.fromElement, 10, driver);
		//select city as Bangalore
		obj.safeClick(MakeMyTrip.bangaloreOption, 10, driver);
		//click on the from Text field
		obj.safeClick(MakeMyTrip.toElement, 10, driver);
		//select city as Bangalore
		obj.safeClick(MakeMyTrip.delhiOption, 10, driver);
		//click on departure arrow
		
		By date1 = MakeMyTrip.dateFunction(dateToday);
		obj.safeClick(date1, 10, driver);
		//click on search button
		obj.safeClick(MakeMyTrip.searchBtn, 10, driver);
		Thread.sleep(2000);
		//non stop
		obj.safeClick(MakeMyTrip.nonStopChkBox, 10, driver);
		//morning time
		if(obj.isElementPresent(MakeMyTrip.timeFilter, 10, driver)){
			obj.safeClick(MakeMyTrip.timeFilter, 10, driver);
			mmtPrice= obj.getText(MakeMyTrip.mmtPrice, 10, driver).replaceAll("[^0-9]", "");
			System.out.println("Least price for mmt flight: "+mmtPrice);
		}
		else{
			System.out.println("Morning flights are not available now");
			
		}
		
		
		//yatra site
		driver.get("https://www.yatra.com/");
		//Call click method to select one way 
		obj.safeClick(Yatra.onewayLink, 10, driver);
		//click on the from Text field
		obj.safeClick(Yatra.fromElement, 10, driver);
		//send Bangalore keys
		obj.safeClick(Yatra.fromField, 10, driver);
		obj.sendKeysToField(Yatra.fromField, 10, driver, "Bangalore");
		Thread.sleep(2000);
		obj.safeClick(Yatra.bangaloreOption, 10, driver);
		//send Delhi keys
		obj.safeClick(Yatra.toField, 10, driver);
		obj.sendKeysToField(Yatra.toField, 10, driver, "Delhi");
		Thread.sleep(2000);
		obj.safeClick(Yatra.delhiOption, 10, driver);
		//departure date
		obj.safeClick(Yatra.departureSelect, 10, driver);
		DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calobj1 = Calendar.getInstance();
		String dateToday1= df1.format(calobj1.getTime());
		By date2 = Yatra.dateFunction(dateToday1);
		obj.safeClick(date2, 10, driver);
		
		//non stop
		obj.safeClick(Yatra.nonStop, 10, driver);
		
		//search button
		obj.safeClick(Yatra.searchBtn, 10, driver);
		//click on depart time
		obj.safeClick(Yatra.timeFilter, 10, driver);
		//morning time
		String attributeVal=obj.getElement(Yatra.morningTime, 10, driver).getAttribute("class");
		if(!(attributeVal.contains("disabled"))){
			obj.safeClick(Yatra.morningTime, 10, driver);
			yatraPrice= obj.getText(Yatra.yatraPrice, 10, driver).replaceAll("[^0-9]", "");
			System.out.println("Least price for yatra flight: "+yatraPrice);
		}
		else{
			System.out.println("Morning flights are not available now");

		}
		driver.quit();
		if(!(mmtPrice.isEmpty() && yatraPrice.isEmpty())){
			if(Integer.parseInt(mmtPrice)>Integer.parseInt(yatraPrice))
				System.out.println("Choose Yatra "+yatraPrice);
			else if(Integer.parseInt(mmtPrice)<Integer.parseInt(yatraPrice))
				System.out.println("Choose MakeMyTrip "+mmtPrice);
			else
				System.out.println("Prices are equal");
		}
		
	}

}

