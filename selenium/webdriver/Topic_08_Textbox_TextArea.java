package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_08_Textbox_TextArea {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Login_Empty_Email_Password() {



    }

    @Test
    public void TC_02_Login_Invalid_Email() {

    }

    @Test
    public void TC_03_Login_Password6() {

    }
    @Test
    public void TC_04_Login_Incorrect_Email_Password() {

    }
    @Test
    public void TC_05_Register() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        sleepInSeconds(1);
        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
        sleepInSeconds(1);

        String firstName = "Satoshi", lastName = "Mayaka", password = "Abcd@1234";
        String email = getRandomEmail();

        //Register
        driver.findElement(By.id("firstname")).sendKeys(firstName);
        driver.findElement(By.id("lastname")).sendKeys(lastName);
        driver.findElement(By.id("email_address")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirmation")).sendKeys(password);
        driver.findElement(By.cssSelector("button[title='Register']")).click();
        sleepInSeconds(2);

        //Verify register successfully
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
        String info = driver.findElement(By.xpath("//a[text()='Change Password']/parent::p")).getText();
        Assert.assertTrue(info.contains(firstName + " " + lastName));
        Assert.assertTrue(info.contains(email));

        //Logout
        driver.findElement(By.xpath("//a/span[text()='Account']")).click();
        driver.findElement(By.cssSelector("a[title='Log Out']")).click();
        sleepInSeconds(5);

        //Login
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        sleepInSeconds(1);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("pass")).sendKeys(password);
        driver.findElement(By.id("send2")).click();
        sleepInSeconds(3);

        //Verify account info
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), email);


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

    private String getRandomEmail(){
        return "automation" + new Random().nextInt(99999) + "@gmail.com";
    }
}