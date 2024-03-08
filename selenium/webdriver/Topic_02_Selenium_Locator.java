package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("FirstName")).sendKeys("Hello");
    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("header-logo"));
    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("DateOfBirthDay"));
    }

    @Test
    public void TC_04_Tagname() {
        driver.findElements(By.tagName("input"));
    }

    @Test
    public void TC_05_LinkText() {
        driver.findElement(By.linkText("Shipping & returns"));
    }

    @Test
    public void TC_06_Partial_LinkText() {
        driver.findElement(By.partialLinkText("vendor account"));
        driver.findElement(By.partialLinkText("for vendor"));
    }

    @Test
    public void TC_07_Css() {
        //Css vs id
        driver.findElement(By.cssSelector("input[id='FirstName']"));
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));

        //Css vs Class
        driver.findElement(By.cssSelector(".page-title"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector("div.page-title"));

        //Css vs Name
        driver.findElement(By.cssSelector("input[name='FirstName']"));

        //Css vs TagName
        driver.findElement(By.cssSelector("input"));

        //Css vs link text
        driver.findElement(By.cssSelector("a[href='/shipping-returns']"));

        //Css vs partial link text
        driver.findElement(By.cssSelector("a[href*='addresses']"));
    }

    @Test
    public void TC_08_Xpath() {
        //Xpath vs id
        driver.findElement(By.xpath("//input[@id='FirstName']"));

        //Xpath vs Class
        driver.findElement(By.xpath("//div[@class='page-title']"));

        //Xpath vs Name
        driver.findElement(By.xpath("//input[@name='FirstName']"));

        //Xpath vs TagName
        driver.findElement(By.xpath("//input"));

        //Xpath vs link text
        driver.findElement(By.xpath("//a[@href='/shipping-returns']"));
        driver.findElement(By.xpath("//a[text()='Shipping & returns']"));

        //Xpath vs partial link text
        driver.findElement(By.xpath("//a[contains(@href,'addresses')]"));
        driver.findElement(By.xpath("//a[contains(text(),'Addresses')]"));

    }



    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
