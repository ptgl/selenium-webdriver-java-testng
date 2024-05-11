package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Wait_01_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;
    By customGenderTextBox = By.cssSelector("input[name='custom_gender']");
    By customLabel = By.xpath("//label[text()='Custom']");

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @BeforeMethod
    public void openUrl(){
        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();

    }

    @Test
    public void TC_01_Visible() {
        //Wait for the label is displayed on UI
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(customLabel));

        Assert.assertTrue(driver.findElement(customLabel).isDisplayed());

        driver.findElement(By.xpath("//label[text()='Custom']")).click();

        //Wait for the textbox is displayed on UI
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(customGenderTextBox));

        Assert.assertTrue(driver.findElement(customGenderTextBox).isDisplayed());

    }

    @Test
    public void TC_02_Invisible_In_DOM() {
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(customLabel));
        driver.findElement(By.xpath("//label[text()='Custom']")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(customGenderTextBox));

        //Click Female gender too hide Custom label
        driver.findElement(By.xpath("//label[text()='Female']")).click();

        //Wait for the textbox disappears
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(customGenderTextBox));

        Assert.assertFalse(driver.findElement(customGenderTextBox).isDisplayed());
    }
    @Test
    public void TC_02_Invisible_Not_In_DOM() {
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(customLabel));
        Assert.assertTrue(driver.findElement(customLabel).isDisplayed());

        //Close the popup
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(customLabel));

        Assert.assertEquals(driver.findElements(customLabel).size(), 0);
    }


    @Test
    public void TC_03_Presence() {

        //Wait for the label is in DOM and on UI
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(customLabel));

        Assert.assertTrue(driver.findElement(customLabel).isDisplayed());

        //Wait for the textbox is in DOM but not on UI
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(customGenderTextBox));

        Assert.assertFalse(driver.findElement(customGenderTextBox).isDisplayed());

    }

    @Test
    public void TC_04_Staleness() {
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(customLabel));
        driver.findElement(By.xpath("//label[text()='Custom']")).click();
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(customGenderTextBox));

        //Element exists
        WebElement customGender = driver.findElement(customGenderTextBox);

        //Close the popup
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        //Wait for element not in DOM
        explicitWait.until(ExpectedConditions.stalenessOf(customGender));

        Assert.assertEquals(driver.findElements(customLabel).size(), 0);
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