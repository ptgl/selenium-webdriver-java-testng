package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_24_Wait_03_Implicit {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();

    }

    @Test
    public void TC_01_Equal_5s() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start button")).click();
        System.out.println(getDateTimeNow());
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(),"Hello World!");
        System.out.println(getDateTimeNow());

    }

    @Test
    public void TC_02_Less_than_5s() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start button")).click();
        System.out.println(getDateTimeNow());
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(),"Hello World!");
        System.out.println(getDateTimeNow());
    }

    @Test
    public void TC_03_Greater_than_5s() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start button")).click();
        System.out.println(getDateTimeNow());
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(),"Hello World!");
        System.out.println(getDateTimeNow());
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
    private String getDateTimeNow(){
        return new Date().toString();
    }

}