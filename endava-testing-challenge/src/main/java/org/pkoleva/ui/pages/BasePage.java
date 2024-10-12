package org.pkoleva.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public String basePageUrl = "https://www.saucedemo.com/";
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected By menuBtn = By.id("react-burger-menu-btn");
    protected By cartBtn = By.className("shopping_cart_link");

    public BasePage(WebDriver webDriver, WebDriverWait driverWait) {
        driver = webDriver;
        wait = driverWait;
    }



    public void clickButton(By locator){
        var button = driver.findElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    public void typeInField(By locator, String criteria){
        var textBox = driver.findElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(textBox));
        textBox.sendKeys(criteria);
    }

    public void getValueFromField(){

    }

}
