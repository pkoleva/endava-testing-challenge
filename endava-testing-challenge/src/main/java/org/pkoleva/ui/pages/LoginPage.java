package org.pkoleva.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver webDriver, WebDriverWait driverWait) {
        super(webDriver, driverWait);
    }

    //operational data
    private String username = "standard_user";
    private String password = "secret_sauce";

    //locators
//    private By usernameString = By.xpath("");
//    private By passwordString = By.xpath("");
    private By usernameFld = By.id("user-name");
    private By passwordFld = By.id("password");
    private By loginBtn = By.id("login-button");
    private By logoutBtn = By.id("logout_sidebar_link");


    //methods
    public String getUsername(){
        return "";
    }

    public String getPassword(){
        return "";
    }

    public void logIn(){
//        username = getUsername();
//        password = getPassword();
        if(driver.findElement(loginBtn).isDisplayed()== false) {
            logout();
        }
        typeInField(usernameFld, username);
        typeInField(passwordFld, password);
        clickButton(loginBtn);
    }

    public void logout(){
        clickButton(menuBtn);
        clickButton(logoutBtn);
    }
}
