package webdriver;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v123.network.Network;
import org.openqa.selenium.devtools.v123.network.model.Headers;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Topic_13_Alert {
    WebDriver driver;

    String username = "admin";
    String pass = "admin";

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked an alert successfully");

    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked: Ok");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        alert.dismiss();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked: Cancel");
    }

    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS prompt");
        alert.sendKeys("hello world");
        alert.accept();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You entered: hello world");
    }

    @Test
    public void TC_04_Authenticate_Alert_Bypass(){
        driver.get("http://"+username+":"+pass+"@the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

        driver.get(getUrlByUsernameAndPass("http://the-internet.herokuapp.com/basic_auth", username, pass));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

    }

    @Test
    public void TC_05_Authenticate_Alert_AutoIT() throws IOException {
        String rootFoler = System.getProperty("user.dir");
        String chromeAuthen = rootFoler+"\\autoITScript\\authen_chrome.exe";
        Runtime.getRuntime().exec(new String[]{chromeAuthen, username, pass});
        driver.get("http://the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    @Test
    public  void  TC_06_Authen_Alert_Selenium4(){
        // Get DevTool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        // Start new session
        devTools.createSession();

        // Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Encode username/ password
        Map<String, Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", username, pass).getBytes()));
        headers.put("Authorization", basicAuthen);

        // Set to Header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("https://the-internet.herokuapp.com/basic_auth");
    }

    private String getUrlByUsernameAndPass(String url, String username, String pass){
        String[] spiltUrl = url.split("//");
        return spiltUrl[0]+username+":"+pass+"@"+spiltUrl[1];
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