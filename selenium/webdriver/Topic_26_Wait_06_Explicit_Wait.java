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
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Topic_26_Wait_06_Explicit_Wait {
    WebDriver driver;

    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Equal_5s() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start button")).click();
        sleepInSeconds(1);

        System.out.println(getDateTimeNow());
        //explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading img")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(),"Hello World!");
        System.out.println(getDateTimeNow());
    }

    @Test
    public void TC_02_Less_than_5s() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start button")).click();
        sleepInSeconds(1);

        System.out.println(getDateTimeNow());
        //explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading img")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(),"Hello World!");
        System.out.println(getDateTimeNow());
    }

    @Test
    public void TC_03_Greater_than_5s() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start button")).click();
        sleepInSeconds(1);

        System.out.println(getDateTimeNow());
        //explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading img")));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(),"Hello World!");
        System.out.println(getDateTimeNow());
    }

    @Test
    public void TC_04_Ajax_Loading(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar"))).isDisplayed());

        System.out.println("DateTime presents");

        By resultText = By.cssSelector("div.RadAjaxPanel>span");
        System.out.println(driver.findElement(resultText).getText());

        driver.findElement(By.xpath("//td[@title='Thursday, May 30, 2024']")).click();

        System.out.println(getDateTimeNow());
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.raDiv")));

        explicitWait.until(ExpectedConditions.textToBe(resultText, "Thursday, May 30, 2024"));
        System.out.println(getDateTimeNow());
        Assert.assertEquals(driver.findElement(resultText).getText(), "Thursday, May 30, 2024");
    }

    @Test
    public void TC_05_Upload_File(){
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(60));
        driver.get("https://gofile.io/?t=uploadFiles");

        //Wait for Upload button appears
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn-lg"))).click();

        //Wait for Add file button appears
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.filesUploadButton.btn-lg")));

        String projectPath = System.getProperty("user.dir");
        String img1 = "temple.jpg";
        String img2 = "mountain.jpg";
        String img3 = "pink_red.jpg";
        String img4 = "sakura.jpg";
        String img1Path = projectPath + File.separator + "uploadPhoto" + File.separator + img1;
        String img2Path = projectPath + File.separator + "uploadPhoto" + File.separator + img2;
        String img3Path = projectPath + File.separator + "uploadPhoto" + File.separator + img3;
        String img4Path = projectPath + File.separator + "uploadPhoto" + File.separator + img4;

        driver.findElement(By.cssSelector("input#filesUploadInput")).sendKeys(String.join("\n", Arrays.asList(img1Path, img2Path, img3Path)));

        //Wait for successful upload Text appear after uploading successfully
        explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div.mainUploadSuccess div.alert"),"Your files have been successfully uploaded"));

        driver.findElement(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink")).click();

        //Wait for the loading icon disappears
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border")));

        List<WebElement> imgList = driver.findElements(By.cssSelector("div#filesContentTableContent a.contentLink>span.contentName"));

        for(WebElement e : imgList){
            String imgName = e.getText();
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + imgName +"']/parent::a/parent::div/following-sibling::div/a")).isDisplayed());
            Assert.assertTrue(driver.findElement(By.xpath("//span[text()='" + imgName +"']/parent::a/parent::div/following-sibling::div/button[contains(@class,'filesContentOptionPlay')]")).isDisplayed());
        }
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

    private String getDateTimeNow(){
        return new Date().toString();
    }


}