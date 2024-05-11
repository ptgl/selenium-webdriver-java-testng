package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Topic_23_Wait_02_Find_Element {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Find_Element() {
        //Case 1: Find 1 element
        //Find success -> go to next step
        System.out.println(getDateTimeNow());
        driver.findElement(By.cssSelector("input[name='email']"));
        System.out.println(getDateTimeNow());


        //Case 2: Find many elements
        //Find success the first one -> go to next step
        System.out.println(getDateTimeNow());
        driver.findElement(By.cssSelector("input[name='email'],[name='pass']")).sendKeys("abc");
        System.out.println(getDateTimeNow());


        //Case 3: Find no element
        //Find until timeout -> false test case
        //Throw NoSuchElementException
        // STOP test case -> not go to next step
        System.out.println(getDateTimeNow());
        driver.findElement(By.id("not-found"));
        System.out.println(getDateTimeNow());
    }

    @Test
    public void TC_02_Find_Elements() {
        //Case 1: Find 1 element
        //Find success -> go to next step
        System.out.println(getDateTimeNow());
        List<WebElement> result = driver.findElements(By.cssSelector("input[name='email']"));
        System.out.println(getDateTimeNow());
        System.out.println("Found "+result.size()+" element.");


        //Case 2: Find many elements
        //Find success the first one -> go to next step
        System.out.println(getDateTimeNow());
        result = driver.findElements(By.cssSelector("input[name='email'],[name='pass']"));
        System.out.println(getDateTimeNow());
        System.out.println("Found "+result.size()+" elements.");


        //Case 3: Find no element
        //Find until timeout -> empty list -> go to next step
        System.out.println(getDateTimeNow());
        result = driver.findElements(By.id("not-found"));
        System.out.println(getDateTimeNow());
        System.out.println("Found "+result.size()+" elements.");
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }


    private String getDateTimeNow(){
        return new Date().toString();
    }

}