package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_18_IFrame {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Kyna_Follower() {
        driver.get("https://kynaenglish.vn/");
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[data-testid='fb:page Facebook Social Plugin']")));
        Assert.assertTrue(driver.findElement(By.xpath("//a[@title='Kyna English 1 kèm 1']/parent::div/following-sibling::div/div")).getText().contains("42K followers"));

        driver.switchTo().defaultContent();
        sleepInSeconds(2);
        driver.findElement(By.xpath("//footer//a[text()='Về Chúng Tôi']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.container>ul>li>b")).getText(),"Về chúng tôi");
    }

    @Test
    public void TC_02_Chat_Iframe() {
        driver.get("https://skills.kynaenglish.vn/");
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#cs-live-chat>iframe")));
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("div.button_bar")).click();

        driver.findElement(By.cssSelector("input[ng-model='login.username']")).sendKeys("Harry");
        driver.findElement(By.cssSelector("input[ng-model='login.phone']")).sendKeys("0123456789");

        Select serviceSelect = new Select(driver.findElement(By.id("serviceSelect")));
        serviceSelect.selectByVisibleText("TƯ VẤN TUYỂN SINH");
        driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Hello World");

    }

    @Test
    public void TC_03_Campus_Safety_Survey() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("div.details__form-iframe")).click();
        sleepInSeconds(2);
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));
        sleepInSeconds(2);

        Select year = new Select(driver.findElement(By.id("RESULT_RadioButton-2")));
        year.selectByVisibleText("Sophomore");
        Select residence = new Select(driver.findElement(By.id("RESULT_RadioButton-3")));
        residence.selectByVisibleText("Off Campus");
        driver.findElement(By.xpath("//label[text()='Female']")).click();
        driver.findElement(By.cssSelector("input[name='Submit']")).click();

        driver.switchTo().defaultContent();
        sleepInSeconds(2);
        driver.findElement(By.cssSelector("nav.header.js-header>div.container>a[title='Log in']")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("button#login")).click();
        Assert.assertEquals(driver.findElement(By.id("message-error")).getText(),"Username and password are both required.");

    }

    @Test
    public void TC_04_HDBank(){
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='login_page']")));
        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("123");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.id("keyboard")).isDisplayed());
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