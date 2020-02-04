package servusTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TestClass {
	
	WebDriver driver;
	ReadPropertyFile prop;
	WebElement homeButton;
	
	
	public TestClass() throws IOException{
		
		File currentDir = new File("");
		String projectPath = currentDir.getAbsolutePath();
		
		System.setProperty("webdriver.chrome.driver", projectPath+"//Driver//chromedriver.exe"); 
		driver = new ChromeDriver();
		prop = new ReadPropertyFile();
		
		driver.manage().window().maximize(); 
		//driver.manage().deleteAllCookies();
		driver.get(projectPath+"//Data//index.html");
		driver.findElement(By.xpath("//a[@class='nav-link' and contains(text(), 'Home')]")).click();
		
		homeButton = driver.findElement(By.xpath("//a[contains(text(), 'Home')]"));
		
	}
	
	public void clickHome() {
		System.out.println("Navigating to Home Page");
		homeButton.click();
		System.out.println("Navigated to Home Page");
	}

	@Test
	public void Test1() {
		System.out.println("");
		System.out.println("Test 1 Initiated ---> Input Fields and Button");
		
		clickHome();
		
		WebElement username = driver.findElement(By.xpath("//input[@id='inputEmail']"));
		WebElement password = driver.findElement(By.xpath("//input[@id='inputPassword']"));
		WebElement button = driver.findElement(By.xpath("//button[@type='submit']"));
		
		if(username.isDisplayed()) {
			System.out.println("Username Field is Present");
		}
		else {
			System.out.println("Username Field is not Present");
		}
		
		if(password.isDisplayed()) {
			System.out.println("Password Field is present");
		}
		else {
			System.out.println("Password Field is not Present");
		}
		
		if(button.isDisplayed()) {
			System.out.println("Button is Present");
		}
		else {
			System.out.println("Button is not Present");
		}
		username.clear();
		username.sendKeys(prop.GetUsername());
		password.clear();
		password.sendKeys(prop.GetPass());
		//button.click();
		
	}
	
	@Test
	public void Test2() {
		System.out.println("");
		System.out.println("Test 2 Initiated ---> List Group Elements");
		
		clickHome();
		
		List <WebElement> list = driver.findElements(By.xpath("//li[@class='list-group-item justify-content-between']"));
		
		if(list.size()==3) {
			System.out.println("List size is 3");
		}
		else {
			System.out.println("List size is not 3");
		}
		
		if(list.get(1).getText().trim().contains("List Item 2")) {
			System.out.println("Second list item value is set to List Item 2");
		}
		else {
			System.out.println("Second list item value is not set to List Item 2");
		}
		
		List<WebElement> badge = driver.findElements(By.xpath("//li[@class='list-group-item justify-content-between']//span[@class='badge badge-pill badge-primary']"));
		int badgeNumber = Integer.parseInt(badge.get(1).getText());
		
		if(badgeNumber==6) { 
			System.out.println("Badge number of List Item 2 is 6"); 
		} 
		else {
			System.out.println("Badge number of List Item 2 is Not 6"); 
		}
		 
	}
	
	@Test
	public void Test3() {
		System.out.println("");
		System.out.println("Test 3 Initiated ---> Drop Down Menu");
		
		clickHome();
		
		WebElement key = driver.findElement(By.xpath("//button[@class='btn btn-secondary dropdown-toggle']"));
		
		if(key.getText().trim().equals("Option 1")) {
			System.out.println("Default Visible drop-down option is Option 1");
		}
		else {
			System.out.println("Default Visible drop-down option is not Option 1");
		}
		
		key.click();
		driver.findElement(By.xpath("//a[@class='dropdown-item' and contains(text(), 'Option 3')]")).click();
		
	}
	
	@Test
	public void Test4() {
		System.out.println("");
		System.out.println("Test 4 Initiated ---> Buttons Enabled/Disabled");
		clickHome();
		
		WebElement button1 = driver.findElement(By.xpath("//div[@id='test-4-div'] //button[@class='btn btn-lg btn-primary']"));
		WebElement button2 = driver.findElement(By.xpath("//div[@id='test-4-div'] //button[@class='btn btn-lg btn-secondary']"));
		
		if(button1.isEnabled()) {
			System.out.println("Button 1 is Enabled");
		}
		else {
			System.out.println("Button 1 is Disabled");
		}
		
		if(button2.isEnabled()) {
			System.out.println("Button 2 is Enabled");
		}
		else {
			System.out.println("Button 2 is Disabled");
		}
		
	}
	
	@Test
	public void Test5() {
		System.out.println("");
		System.out.println("Test 5 Initiated ---> Button Visibility");
		clickHome();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		WebElement button5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id ='test5-button']")));
		
		boolean status = button5.isDisplayed();
		
		if(status) {
			button5.click();
			if(driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText().contains("You clicked a button")) {
				System.out.println("You clicked a button is Displayed");
			}
			else {
				System.out.println("You clicked a button is not displayed");
			}
		}
			
		else {
			System.out.println("Button 5 is not displayed");
		}
		
	}
	
	@Test
	public void Test6() {
		System.out.println("");
		System.out.println("Test 6 Initiated ---> Table Elements");
		clickHome();
		
		//System.out.println(getData(2,1));
		if(getData(2,2).equals("Ventosanzap")) {
			System.out.println("The value at coordinates 2,2 is Ventosanzap");
		}
		else {
			System.out.println("The value at coordinates 2,2, is not Ventosanzap");
		}
	}
	
	public String getData(int row, int col) {
		row = row+1;
		col = col+1;
		WebElement data1 = driver.findElement(By.xpath("//table[@class='table table-bordered table-dark']/tbody/tr["+row+"]/td["+col+"]"));	
		return data1.getText();
	}
	
	@AfterTest
	public void tearDown() {
		
		//driver.close();
	}
	
	
}
