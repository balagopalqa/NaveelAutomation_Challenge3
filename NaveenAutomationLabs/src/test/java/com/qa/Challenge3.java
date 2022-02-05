package com.qa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Challenge3 {
	public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "./Driverss/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("https://www.saucedemo.com");
		driver.manage().window().maximize();

		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();

		List<WebElement> price = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
		ArrayList<Double> price_arr = new ArrayList<Double>();

		for (int i = 0; i < price.size(); i++) {
			// To Print price
			String price_str = price.get(i).getText();

			// Removing $
			price_str = price_str.replaceAll("[^a-zA-Z0-9.]", "");
			// String to double conversion
			double j = Double.parseDouble(price_str);
			// System.out.println(s);
			price_arr.add(j);

		}
		// Finding the max
		Double highest = Collections.max(price_arr);

		System.out.println("Highest value is:" + highest);

		// clicking the max element as per the value got dynamically
		driver.findElement(By.xpath("//div[@class='inventory_item_price'][text()='" + highest
				+ "']//parent::div[@class='pricebar']//button")).click();

		driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();

		// getting
		String prod_value = driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText();

		String prod_value_double = prod_value.replaceAll("[^a-zA-Z0-9.]", "");
		// System.out.println(prod_value_double);
		Assert.assertEquals(String.valueOf(highest), prod_value_double.trim());
		System.out.println("Price of product matches in the cart");

	}
}
