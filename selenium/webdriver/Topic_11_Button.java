package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_11_Button {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        WebElement loginButton = driver.findElement(By.cssSelector("form button[title='Đăng nhập']"));
        Assert.assertFalse(loginButton.isEnabled());
        String disabledBackgroundColor = loginButton.getCssValue("background-color");
        System.out.println(disabledBackgroundColor);

        Color loginBtnColor = Color.fromString(disabledBackgroundColor);
        String loginBtnColorHex = loginBtnColor.asHex().toUpperCase();
        System.out.println(loginBtnColorHex);
        Assert.assertEquals(loginBtnColorHex, "#000000");

        driver.findElement(By.id("login_username")).sendKeys("mail@gmail.com");
        driver.findElement(By.id("login_password")).sendKeys("Abcd@1234");
        Assert.assertTrue(loginButton.isEnabled());

        String enableBackgroundColor = loginButton.getCssValue("background-color");
        System.out.println(enableBackgroundColor);
        loginBtnColorHex = Color.fromString(enableBackgroundColor).asHex().toUpperCase();
        Assert.assertEquals(loginBtnColorHex, "#C92127");
        System.out.println(loginBtnColorHex);

    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}