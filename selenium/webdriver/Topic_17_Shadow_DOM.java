package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Shadow_DOM {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01() {
        driver.get("https://automationfc.github.io/shadow-dom/");
        WebElement shadowHost = driver.findElement(By.id("shadow_host"));
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        Assert.assertEquals(shadowRoot.findElement(By.cssSelector("#shadow_content>span")).getText(),"some text");
        Assert.assertFalse(shadowRoot.findElement(By.cssSelector("input[type='checkbox']")).isSelected());

        shadowHost = shadowRoot.findElement(By.cssSelector("#nested_shadow_host"));
        shadowRoot = shadowHost.getShadowRoot();
        Assert.assertEquals(shadowRoot.findElement(By.cssSelector("#nested_shadow_content>div")).getText(),"nested text");

    }

    @Test
    public void TC_02_shopee() {
        driver.get("https://shopee.vn/");

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

}