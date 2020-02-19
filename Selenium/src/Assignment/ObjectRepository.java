package Assignment;

import org.openqa.selenium.By;

public class ObjectRepository {
	public static class MakeMyTrip{
		public static final By onewayLink = By.xpath("//li[@data-cy='oneWayTrip']");
		public static final By fromElement = By.xpath("//label[@for='fromCity']//span[contains(text(),'From')]");
		public static final By fromField = By.xpath("//input[@class='react-autosuggest__input react-autosuggest__input--open' and @type='text']");
		public static final By bangaloreOption = By.xpath("//p[contains(text(),'Bangalore')]");
		public static final By toElement = By.xpath("//label[@for='toCity']//span[contains(text(),'To')]");
		public static final By delhiOption = By.xpath("//p[contains(text(),'Delhi')]");
		
		public static final By searchBtn = By.xpath("//a[contains(text(),'Search')]");
		public static final By nonStopChkBox = By.xpath("//span[@class='checkbox-group  append_bottom5 fli-filter-items ']");
		public static final By timeFilter = By.xpath("//span[contains(text(),'6AM-12 Noon') and @class='labeltext']");
		public static final By dateFunction(String dateToday){
			return By.xpath("//div[contains(@aria-label,'"+dateToday+"')]");
		}
		public static final By mmtPrice = By.xpath("//span[@class='actual-price'][1]");
	}
	
	public static class Yatra{
		public static final By onewayLink = By.xpath("//a[@title='One Way']");
		public static final By fromElement = By.xpath("//label[@for='BE_flight_origin_city']");
		public static final By fromField = By.xpath("//input[@id='BE_flight_origin_city']");
		public static final By bangaloreOption = By.xpath("//li[@class='active ac_over']");
		public static final By toElement = By.xpath("//label[@for='BE_flight_arrival_city']");
		public static final By toField = By.xpath("//input[@id='BE_flight_arrival_city']");
		public static final By delhiOption = By.xpath("//li[@class='active ac_over']");
		public static final By departureSelect = By.xpath("//li[@class='datepicker flex1']");
		public static final By dateFunction(String dateToday){
			return By.xpath("//td[@id='"+dateToday+"']");
		}
		public static final By nonStop = By.xpath("//a[@for='BE_flight_non_stop']");
		public static final By searchBtn = By.xpath("//input[@id='BE_flight_flsearch_btn']");
		public static final By timeFilter = By.xpath("//span[@class='filter-label no-wrap' and contains(text(),'Depart Time ')]");
		public static final By morningTime = By.xpath("//p[@class='text-left mb-5']//following::label[2]");
		public static final By yatraPrice = By.xpath("//p[@class='i-b tipsy fare-summary-tooltip fs-18'][1]");
	}



}
