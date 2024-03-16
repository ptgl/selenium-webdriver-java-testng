package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Template {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.get("");
    }

    @Test
    public void TC_01() {


    }

    @Test
    public void TC_02() {

    }

    @Test
    public void TC_03() {

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}