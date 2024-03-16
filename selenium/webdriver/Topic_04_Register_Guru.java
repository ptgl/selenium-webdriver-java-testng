package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Register_Guru {
    WebDriver driver;
    String userId;
    String pass;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.get("https://demo.guru99.com/");
    }

    @Test
    public void TC_01_Register() {
        driver.findElement(By.name("emailid")).sendKeys("abc@mail.com");
        driver.findElement(By.name("btnLogin")).click();
        userId = driver.findElement(By.xpath("//td[contains(text(),'User ID')]/following-sibling::td")).getText();
        pass = driver.findElement(By.xpath("//td[contains(text(),'Password')]/following-sibling::td")).getText();
    }

    @Test
    public void TC_02_Login() {
        driver.get("https://demo.guru99.com/v4/");
        driver.findElement(By.name("uid")).sendKeys(userId);
        driver.findElement(By.name("password")).sendKeys(pass);
        driver.findElement(By.name("btnLogin")).click();
        String message = driver.findElement(By.tagName("marquee")).getText();
        Assert.assertEquals(message,"Welcome To Manager's Page of Guru99 Bank");
        String message2 = driver.findElement(By.cssSelector("tr.heading3>td")).getText();
        Assert.assertEquals(message2,"Manger Id : "+userId);

    }



    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}