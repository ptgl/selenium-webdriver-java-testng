package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Topic_21_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String img1 = "temple.jpg";
    String img2 = "mountain.jpg";
    String img3 = "pink_red.jpg";
    String img4 = "sakura.jpg";
    String img1Path = projectPath + File.separator + "uploadPhoto" + File.separator + img1;
    String img2Path = projectPath + File.separator + "uploadPhoto" + File.separator + img2;
    String img3Path = projectPath + File.separator + "uploadPhoto" + File.separator + img3;
    String img4Path = projectPath + File.separator + "uploadPhoto" + File.separator + img4;


    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Single_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By sendFile = By.cssSelector("input[name='files[]']");

        List<String> imageList = Arrays.asList(img1, img2, img3, img4);
        List<String> imagePathList = Arrays.asList(img1Path, img2Path, img3Path, img4Path);

        for(String path : imagePathList){
            driver.findElement(sendFile).sendKeys(path);
            sleepInSeconds(2);
        }

        List<WebElement> imageTexts = driver.findElements(By.cssSelector("tbody.files tr.image td p.name"));
        for(int i = 0; i < imageTexts.size(); i++){
            String imgName = imageTexts.get(i).getText();
            Assert.assertEquals(imgName, imageList.get(i));
            driver.findElement(By.xpath("//td/p[text()='"+imageList.get(i)+"']/parent::td/following-sibling::td/button[contains(@class, 'start')]")).click();
            sleepInSeconds(2);
        }
        sleepInSeconds(3);

        for(String name : imageList){
            Assert.assertTrue(driver.findElement(By.cssSelector("p.name a[title='"+name+"']")).isDisplayed());
        }
    }

    @Test
    public void TC_02_Multiple_Files() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By sendFile = By.cssSelector("input[name='files[]']");

        List<String> imageList = Arrays.asList(img1, img2, img3, img4);

        driver.findElement(sendFile).sendKeys(String.join("\n", Arrays.asList(img1Path, img2Path, img3Path, img4Path)));
        sleepInSeconds(2);

        List<WebElement> imageTexts = driver.findElements(By.cssSelector("tbody.files tr.image td p.name"));
        for(int i = 0; i < imageTexts.size(); i++){
            String imgName = imageTexts.get(i).getText();
            Assert.assertEquals(imgName, imageList.get(i));
            driver.findElement(By.xpath("//td/p[text()='"+imageList.get(i)+"']/parent::td/following-sibling::td/button[contains(@class, 'start')]")).click();
            sleepInSeconds(2);
        }
        sleepInSeconds(3);

        for(String name : imageList){
            Assert.assertTrue(driver.findElement(By.cssSelector("p.name a[title='"+name+"']")).isDisplayed());
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

}