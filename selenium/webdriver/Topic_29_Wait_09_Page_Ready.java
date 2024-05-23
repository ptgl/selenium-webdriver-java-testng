package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_29_Wait_09_Page_Ready {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_nopcommerce() {
        driver.get("https://admin-demo.nopcommerce.com");
        driver.findElement(By.cssSelector("button.login-button")).click();

        // Wait for page load success after login
        Assert.assertTrue(isPageLoadedSuccess());

        driver.findElement(By.xpath("//i[contains(@class,'fa-user')]/parent::a")).click();

        //Wait for the dropdown shows all items
        Assert.assertTrue(isAllDropdowmItemsShown());

        driver.findElement(By.xpath("//i[contains(@class,'fa-dot-circle')]/following-sibling::p[normalize-space()='Customer roles']")).click();

        //Wait for page start to load
        sleepInSeconds(1);

        //Wait for page load success
        Assert.assertTrue(isPageLoadedSuccess());

        Assert.assertTrue(driver.findElement(By.cssSelector("button[aria-controls='customerroles-grid']")).isDisplayed());

        driver.findElement(By.xpath("//i[contains(@class,'fa-book')]/parent::a")).click();

        //Wait for the dropdown shows all items
        Assert.assertTrue(isAllDropdowmItemsShown());

        driver.findElement(By.xpath("//i[contains(@class,'fa-dot-circle')]/following-sibling::p[normalize-space()='Product tags']")).click();

        Assert.assertTrue(isPageLoadedSuccess());

        driver.findElement(By.cssSelector("th>input.mastercheckbox")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("th>input.mastercheckbox")).isSelected());
    }

    @Test
    public void TC_02_api_orangehrm() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(60));
        driver.get("https://api.orangehrm.com");
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loader")));

        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input.search")));

        Assert.assertEquals(driver.findElement(By.cssSelector("#project h1")).getText(),"OrangeHRM REST API Documentation");

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private void sleepInSeconds(long seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPageLoadedSuccess(){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return explicitWait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        });
    }

    public boolean isAllDropdowmItemsShown(){
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("ul.nav-treeview[style='display: block;']"))).size() == 1;
    }

}