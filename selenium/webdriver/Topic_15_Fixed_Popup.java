package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_15_Fixed_Popup {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }



    @Test
    public void TC_01_Fixed_PopUp_InDom() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("button.login_")).click();
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("div#modal-login-v1[style]>div")).isDisplayed());

        driver.findElement(By.cssSelector("div#modal-login-v1[style] input#account-input")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("div#modal-login-v1[style] input#password-input")).sendKeys("automationfc");
        driver.findElement(By.cssSelector("div#modal-login-v1[style] button.btn-login-v1")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1[style] div.error-login-panel")).getText(),"Tài khoản không tồn tại!");

        driver.findElement(By.cssSelector("div#modal-login-v1[style] button.close")).click();
        sleepInSeconds(2);
        Assert.assertFalse(driver.findElement(By.cssSelector("div#modal-login-v1[style]>div")).isDisplayed());
    }

    @Test
    public void TC_01_Fixed_PopUp_NotInDom_1() {
        driver.get("https://tiki.vn/");

        //Click account icon and verify login popup is display
        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

        //Left the field blank and verify the error message
        driver.findElement(By.cssSelector("p.login-with-email")).click();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[1]")).getText(),"Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@type='password']/parent::div/following-sibling::span")).getText(),"Mật khẩu không được để trống");

        //Close the popup
        driver.findElement(By.cssSelector("button.btn-close")).click();
        sleepInSeconds(2);

        //The popup is not in DOM anymore so use findElements to verify it disappears
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(),0);
    }

    @Test
    public void TC_01_Fixed_PopUp_NotInDom_2() {
        driver.get("https://www.facebook.com/");

        //Click create new account and verify show registration popup
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);
        Assert.assertTrue(driver.findElement(By.cssSelector("div#reg_box")).isDisplayed());

        //Close the popup and verify it isn't in DOM
        driver.findElement(By.xpath("//div[@id='reg_box']/ancestor::div/img")).click();
        sleepInSeconds(2);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Assert.assertEquals(driver.findElements(By.cssSelector("div#reg_box")).size(), 0);
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