package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;

public class Topic_30_Practice_Form_DemoQA {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String imgPath = projectPath + File.separator + "uploadPhoto" + File.separator + "temple.jpg";
    String firstName = "Gia Linh";
    String lastName = "Phan Tu";
    String userEmail = "gialinh@gmail.com";
    String gender = "Female";
    String userNumber = "0123456778";
    String address = "Ho Chi Minh city";
    String state = "NCR";
    String city = "Noida";

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Happy_Flow() {
        driver.get("https://demoqa.com/automation-practice-form");

        scrollToElement(By.id("firstName")).sendKeys(firstName);

        scrollToElement(By.id("lastName")).sendKeys(lastName);

        scrollToElement(By.id("userEmail")).sendKeys(userEmail);

        scrollToElement(By.xpath("//input[@type='radio'][@value='"+gender+"']/following-sibling::label")).click();

        driver.findElement(By.id("userNumber")).sendKeys(userNumber);

        new Actions(driver).click(driver.findElement(By.id("dateOfBirthInput")))
                .keyDown(Keys.CONTROL)
                .sendKeys("A")
                .keyUp(Keys.CONTROL)
                .sendKeys("9 Oct 2000")
                .perform();

        driver.findElement(By.id("subjectsInput")).sendKeys("art");
        driver.findElement(By.xpath("//div[contains(@class,'subjects-auto-complete__option')][text()='Arts']")).click();
        driver.findElement(By.id("subjectsInput")).sendKeys("history");
        driver.findElement(By.xpath("//div[contains(@class,'subjects-auto-complete__option')][text()='History']")).click();
        driver.findElement(By.id("subjectsInput")).sendKeys("bio");
        driver.findElement(By.xpath("//div[contains(@class,'subjects-auto-complete__option')][text()='Biology']")).click();

        driver.findElement(By.xpath("//input[@type='checkbox']/following-sibling::label[text()='Sports']")).click();
        driver.findElement(By.xpath("//input[@type='checkbox']/following-sibling::label[text()='Music']")).click();
        driver.findElement(By.xpath("//input[@type='checkbox']/following-sibling::label[text()='Reading']")).click();

        driver.findElement(By.id("uploadPicture")).sendKeys(imgPath);
        driver.findElement(By.id("currentAddress")).sendKeys(address);

        scrollToElement(By.id("state")).click();
        driver.findElement(By.xpath("//div[@id='state']//div[contains(@class,'option')][text()='"+state+"']")).click();
        driver.findElement(By.id("city")).click();
        driver.findElement(By.xpath("//div[@id='city']//div[contains(@class,'option')][text()='"+city+"']")).click();

        driver.findElement(By.id("submit")).click();

        Assert.assertEquals(driver.findElement(By.id("example-modal-sizes-title-lg")).getText(),"Thanks for submitting the form");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Student Name']/following-sibling::td")).getText(),firstName+" "+lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Student Email']/following-sibling::td")).getText(), userEmail);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile']/following-sibling::td")).getText(), userNumber);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Date of Birth']/following-sibling::td")).getText(),"09 October,2000");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Subjects']/following-sibling::td")).getText(),"Arts, History, Biology");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Hobbies']/following-sibling::td")).getText(),"Sports, Music, Reading");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Picture']/following-sibling::td")).getText(),"temple.jpg");
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
        Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State and City']/following-sibling::td")).getText(),state+" "+city);

    }

    @Test
    public void TC_02_Unhappy_Flow() {
        driver.get("https://demoqa.com/automation-practice-form");

        scrollToElement(By.id("firstName")).sendKeys(firstName);

        scrollToElement(By.id("lastName")).sendKeys(lastName);

        scrollToElement(By.id("userEmail")).sendKeys(userEmail);

        scrollToElement(By.xpath("//input[@type='radio'][@value='"+gender+"']/following-sibling::label")).click();

        new Actions(driver).click(driver.findElement(By.id("dateOfBirthInput")))
                .keyDown(Keys.CONTROL)
                .sendKeys("A")
                .keyUp(Keys.CONTROL)
                .sendKeys("9 Oct 2000")
                .perform();

        driver.findElement(By.id("subjectsInput")).sendKeys("art");
        driver.findElement(By.xpath("//div[contains(@class,'subjects-auto-complete__option')][text()='Arts']")).click();

        driver.findElement(By.xpath("//input[@type='checkbox']/following-sibling::label[text()='Sports']")).click();
        driver.findElement(By.id("uploadPicture")).sendKeys(imgPath);
        driver.findElement(By.id("currentAddress")).sendKeys(address);

        scrollToElement(By.id("state")).click();
        driver.findElement(By.xpath("//div[@id='state']//div[contains(@class,'option')][text()='"+state+"']")).click();
        driver.findElement(By.id("city")).click();
        driver.findElement(By.xpath("//div[@id='city']//div[contains(@class,'option')][text()='"+city+"']")).click();

        driver.findElement(By.id("submit")).click();

        Assert.assertTrue((Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].validity.valid === false;", driver.findElement(By.id("userNumber"))));
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
    private WebElement scrollToElement(By location){
        WebElement element = driver.findElement(location);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return element;
    }

}