package ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pkoleva.ui.pages.CartPage;
import org.pkoleva.ui.pages.CheckoutPage;
import org.pkoleva.ui.pages.ItemsPage;
import org.pkoleva.ui.pages.LoginPage;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wait;

    public LoginPage loginPage;
    public ItemsPage itemsPage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;

    @BeforeEach
    public void beforeEachTest(){
        driver = startBrowser();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Initialize pages.
        loginPage = new LoginPage(driver, wait);
        itemsPage = new ItemsPage(driver, wait);
        cartPage = new CartPage(driver, wait);
        checkoutPage = new CheckoutPage(driver, wait);



        // Navigate to baseUrl.
        driver.get(loginPage.basePageUrl);
        Assertions.assertEquals(loginPage.basePageUrl, driver.getCurrentUrl());
        loginPage.logIn();
//        Assertions.assertTrue(loginPage.verifyLogo());

    }

//    @AfterEach
//    public void afterEachTest(){
//        loginPage.logout();
//    }

    protected static WebDriver startBrowser() {
        // Setup Chrome.
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        return driver;
    }
}
