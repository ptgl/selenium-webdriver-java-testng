package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_WebElement_Commands {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
    }

    @Test
    public void TC_01() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        boolean isMailDisplayed = driver.findElement(By.id("mail")).isDisplayed();
        boolean isUnder18Displayed = driver.findElement(By.id("under_18")).isDisplayed();
        boolean isEduDisplayed = driver.findElement(By.id("edu")).isDisplayed();
        boolean isNameUser5Displayed = driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed();

        Assert.assertTrue(isMailDisplayed);
        Assert.assertTrue(isEduDisplayed);
        Assert.assertTrue(isUnder18Displayed);
        Assert.assertFalse(isNameUser5Displayed);

        if(isMailDisplayed){
            driver.findElement(By.id("mail")).sendKeys("Automation Testing");
            System.out.println("Email textbox is displayed");
        }else {
            System.out.println("Email textbox is not displayed");
        }

        if(isEduDisplayed){
            driver.findElement(By.id("edu")).sendKeys("Automation Testing");
            System.out.println("Education textarea is displayed");
        }else {
            System.out.println("Education textarea is not displayed");
        }

        if(isUnder18Displayed){
            driver.findElement(By.id("under_18")).click();
            System.out.println("Under 18 radio is displayed");
        }else {
            System.out.println("Under 18 radio is not displayed");
        }

        if(isNameUser5Displayed){
            System.out.println("Name User 5 text is displayed");
        }else {
            System.out.println("Name User 5 text is not displayed");
        }
    }

    @Test
    public void TC_02() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        Assert.assertTrue(driver.findElement(By.id("mail")).isEnabled());
        printElementEnabled(driver.findElement(By.id("mail")).isEnabled(), "Email textbox");

        Assert.assertTrue(driver.findElement(By.id("edu")).isEnabled());
        printElementEnabled(driver.findElement(By.id("edu")).isEnabled(), "Education textarea");

        Assert.assertTrue(driver.findElement(By.id("under_18")).isEnabled());
        printElementEnabled(driver.findElement(By.id("under_18")).isEnabled(), "Under 18 radio");

        Assert.assertTrue(driver.findElement(By.id("job1")).isEnabled());
        printElementEnabled(driver.findElement(By.id("job1")).isEnabled(), "Job role 1 dropdown");

        Assert.assertTrue(driver.findElement(By.id("job2")).isEnabled());
        printElementEnabled(driver.findElement(By.id("job2")).isEnabled(), "Job role 2 dropdown");

        Assert.assertTrue(driver.findElement(By.id("development")).isEnabled());
        printElementEnabled(driver.findElement(By.id("development")).isEnabled(), "Interest development checkbox");

        Assert.assertTrue(driver.findElement(By.id("slider-1")).isEnabled());
        printElementEnabled(driver.findElement(By.id("slider-1")).isEnabled(), "Slider 1");

        Assert.assertFalse(driver.findElement(By.id("disable_password")).isEnabled());
        printElementEnabled(driver.findElement(By.id("disable_password")).isEnabled(), "Password textbox");

        Assert.assertFalse(driver.findElement(By.id("radio-disabled")).isEnabled());
        printElementEnabled(driver.findElement(By.id("radio-disabled")).isEnabled(), "Age (radio button is disabled)");

        Assert.assertFalse(driver.findElement(By.id("bio")).isEnabled());
        printElementEnabled(driver.findElement(By.id("bio")).isEnabled(), "Biograpgy textarea");

        Assert.assertFalse(driver.findElement(By.id("job3")).isEnabled());
        printElementEnabled(driver.findElement(By.id("job3")).isEnabled(), "Job role 3 dropdown");

        Assert.assertFalse(driver.findElement(By.id("check-disbaled")).isEnabled());
        printElementEnabled(driver.findElement(By.id("check-disbaled")).isEnabled(), "Interest (checkbox is disabled)");

        Assert.assertFalse(driver.findElement(By.id("slider-2")).isEnabled());
        printElementEnabled(driver.findElement(By.id("slider-2")).isEnabled(), "Slider 2");
    }

    @Test
    public void TC_03() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement under18Radio = driver.findElement(By.id("under_18"));
        WebElement javaCheckbox = driver.findElement(By.id("java"));

        under18Radio.click();
        javaCheckbox.click();

        Assert.assertTrue(under18Radio.isSelected());
        Assert.assertTrue(javaCheckbox.isSelected());

        javaCheckbox.click();
        Assert.assertFalse(javaCheckbox.isSelected());

    }

    @Test
    public void TC_04() {
        driver.get("https://login.mailchimp.com/signup/");

        driver.findElement(By.id("email")).sendKeys("automationfc@gmail.com");

        //Case: Number
        driver.findElement(By.id("new_password")).sendKeys("123");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.lowercase-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.uppercase-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.completed.number-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.special-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //Case: lowercase
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("abc");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.completed.lowercase-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.uppercase-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.number-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.special-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //Case: uppercase
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("ABC");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.lowercase-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.completed.uppercase-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.number-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.special-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //Case: special character
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("!!!");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.lowercase-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.uppercase-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.number-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.completed.special-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());

        //Case: maxlength
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("12345678");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.lowercase-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.uppercase-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.completed.number-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.not-completed.special-char")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());

        //Case: valid
        driver.findElement(By.id("new_password")).clear();
        driver.findElement(By.id("new_password")).sendKeys("123abcAbc!!!");
        Assert.assertFalse(driver.findElement(By.cssSelector("li.completed.lowercase-char")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.completed.uppercase-char")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.completed.number-char")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.completed.special-char")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());

    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private void printElementEnabled(boolean isEnable, String elementName){
        if(isEnable){
            System.out.println(elementName + " is enabled");
        }else {
            System.out.println(elementName + " is disabled");
        }
    }
}