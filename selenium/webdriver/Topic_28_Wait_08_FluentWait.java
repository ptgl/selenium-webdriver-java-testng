package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_28_Wait_08_FluentWait {
    WebDriver driver;

    WebDriverWait explicitWait;
    FluentWait<WebDriver> fluentDriver;
    FluentWait<String> fluentString;
    FluentWait<WebElement> fluentElement;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();

    }

    @Test
    public void TC_0_Initial() {
        //set polling in explicit
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));

        fluentDriver = new FluentWait<WebDriver>(driver);
        fluentElement = new FluentWait<WebElement>(driver.findElement(By.cssSelector("")));
        fluentString = new FluentWait<String>("Hello World");

        fluentDriver.withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofMillis(300))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(TimeoutException.class)
                    .until(new Function<WebDriver, String>() {
                        @Override
                        public String apply(WebDriver webDriver) {
                            return "null";
                        }
                    });

    }

    @Test
    public void TC_02_CountDown() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        fluentElement = new FluentWait<WebElement>(driver.findElement(By.id("javascript_countdown_time")));
        fluentElement.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                //.ignoring(TimeoutException.class)
                .until(new Function<WebElement, Boolean>() {
                    @Override
                    public Boolean apply(WebElement element) {
                        return element.isDisplayed();
                    }
                });

        fluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                String time = element.getText();
                System.out.println(time);
                return time.endsWith("00");
            }
        });

    }

    @Test
    public void TC_03_Dynamic_Loading() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start button")).click();

        fluentDriver = new FluentWait<WebDriver>(driver);

        fluentDriver.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return webDriver.findElement(By.cssSelector("div#finish h4")).getText().equals("Hello World!");
                    }
                });

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

}