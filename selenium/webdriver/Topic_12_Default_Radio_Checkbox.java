package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_12_Default_Radio_Checkbox {
    WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_default_radio_checkbox_1() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        sleepInSeconds(1);
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        sleepInSeconds(1);

        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");
        checkElement(dualZoneCheckbox);
        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());
        uncheckElement(dualZoneCheckbox);
        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        sleepInSeconds(1);
        By twoPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
        checkElement(twoPetrolRadio);
        Assert.assertTrue(driver.findElement(twoPetrolRadio).isSelected());
    }

    @Test
    public void TC_02default_radio_checkbox_2() {
        driver.get("https://material.angular.io/components/radio/examples");
        sleepInSeconds(1);
        driver.findElement(By.xpath("//span[text()='Ok, Got it']/ancestor::button")).click();
        sleepInSeconds(1);
        By summerRadio = By.cssSelector("input[type='radio'][value='Summer']");
        checkElement(summerRadio);
        Assert.assertTrue(driver.findElement(summerRadio).isSelected());

        driver.get("https://material.angular.io/components/checkbox/examples");
        By checkedCheckbox = By.xpath("//label[text()='Checked']/preceding-sibling::div/input");
        By indeterminateCheckbox = By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input");

        checkElement(checkedCheckbox);
        Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
        checkElement(indeterminateCheckbox);
        Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());

        uncheckElement(checkedCheckbox);
        Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
        uncheckElement(indeterminateCheckbox);
        Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
    }

    @Test
    public void TC_03_select_all_checkboxes() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        for(WebElement checkbox : allCheckboxes){
            if(!checkbox.isSelected()){
                checkbox.click();
            }
        }

        for(WebElement checkbox : allCheckboxes){
            Assert.assertTrue(checkbox.isSelected());
        }

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        allCheckboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        By heartAttackCheckbox = By.xpath("//label[text()=' Heart Attack ']/preceding-sibling::input");
        checkElement(heartAttackCheckbox);

        for(WebElement checkbox : allCheckboxes){
            if(checkbox.getAttribute("value").equals("Heart Attack")){
                Assert.assertTrue(checkbox.isSelected());
            }else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }


    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }


    private void checkElement(By location){
        WebElement e = driver.findElement(location);
        if(!e.isSelected()){
            e.click();
        }
    }

    private void uncheckElement(By location){
        WebElement e = driver.findElement(location);
        if(e.isSelected()){
            e.click();
        }
    }

    private void sleepInSeconds(long seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}