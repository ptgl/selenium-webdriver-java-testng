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

import java.time.Duration;
import java.util.List;

public class Topic_10_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_Jquery() {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

        String selectedNumber = "10";
        String selectedSpeed = "Faster";
        String selectedSalutation = "Mrs.";

        selectCustomDropdownByCss("#number-button", "#number-menu div", selectedNumber);
        selectCustomDropdownByXpath("//span[@id='speed-button']", "//ul[@id='speed-menu']//div", selectedSpeed);
        selectCustomDropdownByCss("#salutation-button", "#salutation-menu div", selectedSalutation);

        Assert.assertEquals(driver.findElement(By.cssSelector("#number-button span.ui-selectmenu-text")).getText(),selectedNumber);
        Assert.assertEquals(driver.findElement(By.cssSelector("#speed-button span.ui-selectmenu-text")).getText(),selectedSpeed);
        Assert.assertEquals(driver.findElement(By.cssSelector("#salutation-button span.ui-selectmenu-text")).getText(),selectedSalutation);

    }

    @Test
    public void TC_02_React(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        String name = "Matt";

        selectCustomDropdownByCss( "div[role='listbox']", "div.menu span", name);
        Assert.assertEquals(driver.findElement(By.cssSelector("div[role='listbox'] div.text")).getText(), name);

        name = "Elliot Fu";
        selectCustomDropdownByXpath("//div[@role='listbox']", "//div[contains(@class,'menu')]//span", name);
        Assert.assertEquals(driver.findElement(By.cssSelector("div[role='listbox'] div.text")).getText(), name);
    }


    @Test
    public void TC_03_VueJs() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        String name = "Third Option";

        selectCustomDropdownByCss( "li.dropdown-toggle", "ul.dropdown-menu a", name);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText().contains(name));

        name = "First Option";
        selectCustomDropdownByXpath("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", name);
        Assert.assertTrue(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText().contains(name));

    }

    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

        String name = "Aruba";

        selectCustomDropdownByCss( "input.search", "div.menu span", name);
        Assert.assertTrue(driver.findElement(By.cssSelector("div.text")).getText().contains(name));

        name = "Benin";
        selectCustomDropdownByXpath("//input[@class='search']", "//div[contains(@class,'menu')]//span", name);
        Assert.assertTrue(driver.findElement(By.cssSelector("div.text")).getText().contains(name));

    }

    @AfterClass
    public void afterClass(){
       driver.quit();
    }


    private void selectCustomDropdownByCss(String parentCss, String childCss, String itemText){
        driver.findElement(By.cssSelector(parentCss)).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));
        List<WebElement> options = driver.findElements(By.cssSelector(childCss));

        for(WebElement e : options){
            if(e.getText().contains(itemText)){
                e.click();
                break;
            }
        }
    }

    private void selectCustomDropdownByXpath(String parentXpath, String childTextXpath, String itemText){
        driver.findElement(By.xpath(parentXpath)).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childTextXpath)));
        driver.findElement(By.xpath(childTextXpath+"[contains(text(),'"+itemText+"')]")).click();
    }


}