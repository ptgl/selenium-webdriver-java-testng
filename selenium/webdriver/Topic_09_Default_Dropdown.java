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
import java.util.Random;

public class Topic_09_Default_Dropdown {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01() {

        //Register
        String email = getRandomEmail(), password = "Abcd@1234";
        String day = "1", month = "May", year = "1980";


        driver.findElement(By.id("FirstName")).sendKeys("Mayaka");
        driver.findElement(By.id("LastName")).sendKeys("Satoshi");
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

        Select dateSelect = new Select(driver.findElement(By.name("DateOfBirthDay")));
        Select monthSelect = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        Select yearSelect = new Select(driver.findElement(By.name("DateOfBirthYear")));

        dateSelect.selectByVisibleText(day);
        monthSelect.selectByVisibleText(month);
        yearSelect.selectByVisibleText(year);

        Assert.assertEquals(dateSelect.getOptions().size(),32);
        Assert.assertEquals(monthSelect.getOptions().size(),13);
        Assert.assertEquals(yearSelect.getOptions().size(),112);

        driver.findElement(By.id("register-button")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");

        //Login
        driver.findElement(By.cssSelector("a.ico-login")).click();
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.login-button")).click();

        //Verify account info
        driver.findElement(By.className("ico-account")).click();
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);

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