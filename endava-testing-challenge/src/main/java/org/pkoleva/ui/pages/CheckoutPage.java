package org.pkoleva.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CheckoutPage extends BasePage{
    public CheckoutPage(WebDriver webDriver, WebDriverWait driverWait) {
        super(webDriver, driverWait);
    }

    //locators
    private By yourInfoTitle = By.xpath("//span[text()='Checkout: Your Information']");
    private By firstNameFld = By.id("first-name");
    private By lastNameFld = By.id("last-name");
    private By zipCodeFld = By.id("postal-code");
    private By continueBtn = By.id("continue");
    private By orderOverviewTitle = By.xpath("//span[text()='Checkout: Overview']");
    private By items = By.className("cart_item");
    private By finishBtn = By.id("finish");
    private By orderCompleteTitle = By.xpath("//span[text()='Checkout: Complete!']");
    private By backBtn = By.id("back-to-products");
    private By thankYouTitle = By.xpath("//h2[text()='Thank you for your order!']");

    public void fillInDataAndContinue(){
        wait.until(ExpectedConditions.presenceOfElementLocated(yourInfoTitle));
        typeInField(firstNameFld, "Alberta");
        typeInField(lastNameFld, "Lee");
        typeInField(zipCodeFld, "53001");
        clickButton(continueBtn);
    }

    public List<WebElement> getItems(){
        wait.until(ExpectedConditions.presenceOfElementLocated(orderOverviewTitle));
        return driver.findElements(items);
    }

    public void finishOrder(){
        clickButton(finishBtn);
        wait.until(ExpectedConditions.presenceOfElementLocated(orderCompleteTitle));
    }

    public boolean checkOrderComplete(){
        return (driver.findElement(backBtn).isDisplayed() &&
        driver.findElement(thankYouTitle).isDisplayed());
    }

    public void goToHomePage(){
        clickButton(backBtn);
    }
}
