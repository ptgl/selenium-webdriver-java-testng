package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_16_Random_Popup {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_RandomPoup_InDom_1() {
        driver.get("https://www.javacodegeeks.com/");
        By lepopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");

        //if letter popup is shown, close the popup
        //Check the popup already render and is displayed
        if(driver.findElements(lepopup).size() > 0 && driver.findElements(lepopup).get(0).isDisplayed()){
            System.out.println("Popup shows");
            driver.findElement(By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none']) div.lepopup-element-html-content>a")).click();
            sleepInSeconds(2);
        }else {
            System.out.println("Popup isn't shown");
        }

        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Agile Testing Explained", Keys.ENTER);
        sleepInSeconds(3);
        Assert.assertTrue(driver.findElement(By.cssSelector("ul#posts-container>li>a[aria-label='Agile Testing Explained']")).isDisplayed());

    }


    @Test
    public void TC_02_RandomPopup_InDom_2() {
        driver.get("https://vnk.edu.vn/");
        By tvePopup = By.cssSelector("div#tve_editor");

        //sleepInSeconds(30);
        if(driver.findElement(tvePopup).isDisplayed()){
            System.out.println("Popup shows");
            driver.findElement(By.cssSelector("svg[data-name='closeclose']")).click();
            sleepInSeconds(2);
        }else {
            System.out.println("Popup isn't shown");
        }

    }

    @Test
    public void TC_03_RandomPopup_NotInDom() {
        driver.get("https://dehieu.vn/");

        if(driver.findElement(By.cssSelector("div.modal-content")).isDisplayed()){
            System.out.println("Popup shows");
            driver.findElement(By.cssSelector("button.close")).click();
        }else {
            System.out.println("Popup isn't shown");
        }
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

}