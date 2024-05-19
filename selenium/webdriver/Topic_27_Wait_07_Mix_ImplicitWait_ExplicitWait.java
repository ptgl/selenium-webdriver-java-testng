package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_27_Wait_07_Mix_ImplicitWait_ExplicitWait {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();

    }

    @Test
    public void TC_01_Only_Implicit_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");

        driver.findElement(By.cssSelector("input#email"));

    }

    @Test
    public void TC_02_Only_Implicit_NotFound() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");

        //Not found element
        //Polling 0.5s
        //Until reach timeout throw NoSuchElement -> fail test case
        driver.findElement(By.cssSelector("input#notfound"));
    }

    @Test
    public void TC_03_Only_Explicit_Found() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
    }

    @Test
    public void TC_04_Only_Explicit_NotFound_By() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");

        //Not found element
        //Polling 0.5s
        //Until reach timeout throw TimeoutException -> fail test case
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#notfound")));
    }

    @Test
    public void TC_04_Only_Explicit_NotFound_WebElement() {
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");

        // Without set implicit wait -> findElement throw NoSuchElementException after 0s
        // If set implicit wait Xs -> findElement throw NoSuchElementException after Xs
        // Fail at step driver.findElement -> not go to explicit wait
        // So the execution time = the time implicit wait
        System.out.println(getDateTimeNow());

        try {
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#notfound"))));
        }catch (Exception e){
            System.out.println(getDateTimeNow());
            throw e;
        }
    }

    @Test
    public void TC_05_Mix_Explicit_Implicit() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        //implicit < explicit
        //implicit = explicit
        //implicit > explicit
        // -> implicit starts -> after 0.5,1,2s explicit starts -> TimeoutException

        driver.get("https://www.facebook.com/");

        System.out.println(getDateTimeNow());

        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#notfound")));
        }catch (Exception e){
            System.out.println(getDateTimeNow());
            throw e;
        }
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private String getDateTimeNow(){
        return new Date().toString();
    }


}