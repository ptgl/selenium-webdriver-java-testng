package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Selenium_Text {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://automationfc.github.io/basic-form/");
    }

    @Test
    public void TC_01_Text_String() {

        //Text() = can find text that:
        //Absolute exact text, not cover the space
        //Text at any index in the nested test
        //<tag> text </child> </tag>
        //<tag> </child> text </tag>
        //<tag> </child> text <child>xyz</child> </tag> (child can contains other string)
        driver.findElements(By.xpath("//h5[text()='Michael Jackson']"));

        //Contains(Text(),..) can find text that:
        //<tag>text<tag>
        //<tag>   text can contain spaces  </tag>
        //<tag> text <child></child> </tag> (text at the first index)
        //NOT for:
        //<tag> <child> text </child> </tag> (text in the child)
        //<tag><child></child> text </tag> (text not at the first index)
        //<tag> <child></child> text <child></child></tag>
        driver.findElements(By.xpath("//h5[contains(text(),'Michael Jackson')]"));

        // String()= or .=
        //<tag>text</tag>
        driver.findElements(By.xpath("//h5[string()='Michael Jackson']"));

        //Contains(String(),..) or Contains(.,'azx')
        //get all cases
        driver.findElements(By.xpath("//h5[contains(string(),'Michael Jackson')]"));


        System.out.println(driver.findElement(By.id("nested")).getText());



    }

    @Test
    public void TC_02_Concat() {
        String concatText = driver.findElement(By.xpath("//h5/span[text()=concat('Hello \"John\", What',\"'s happened?\")]")).getText();
        Assert.assertEquals(concatText, "Hello \"John\", What's happened?");
    }

    @Test
    public void TC_03_And_Or_Not() {
        driver.findElement(By.xpath("//input[@type='email' and @id='mail']")).isDisplayed();
        driver.findElements(By.xpath("//input[@type='email' or @type='number']"));
        driver.findElements(By.xpath("//input[not(@type='email')]"));
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}