package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Topic_14_Action {
    WebDriver driver;
    Actions action;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Hover() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        action.moveToElement(driver.findElement(By.id("age"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover() {
        driver.get("https://www.fahasa.com/");
        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
        action.moveToElement(driver.findElement(By.cssSelector("a[title='Bách Hóa Online - Lưu Niệm']"))).perform();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.fhs_column_stretch span.menu-title")).getText(), "Bách Hóa Online - Lưu Niệm");
    }

    @Test
    public void TC_03_Click_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable li"));
        action.clickAndHold(numbers.get(0))
                .pause(2000)
                .moveToElement(numbers.get(14))
                .pause(2000)
                .release()
                .perform();


        List<WebElement> selectedNumbers = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
        Assert.assertEquals(selectedNumbers.size(), 12);

        String[] allExpectedTextNumberArray = {"1", "2", "3", "5", "6","7","9","10","11","13","14","15"};
        //Conver array to list string
        List<String> allExpectedTextNumber = Arrays.asList(allExpectedTextNumberArray);

        List<String> allActualTextNumber = new ArrayList<>();
        for(WebElement e : selectedNumbers){
            allActualTextNumber.add(e.getText());
        }
        Assert.assertEquals(allExpectedTextNumber,allActualTextNumber);


        //Click from 1->15
        action.clickAndHold(numbers.get(0))
                .pause(2000)
                .moveToElement(numbers.get(11))
                .pause(2000)
                .release()
                .keyDown(Keys.CONTROL)
                .clickAndHold(numbers.get(12))
                .moveToElement(numbers.get(14))
                .release()
                .keyUp(Keys.CONTROL)
                .perform();

        for(int i = 0; i < 20; i++){
            String classValue = numbers.get(i).getAttribute("class");
            if(i<15){
                Assert.assertTrue(classValue.contains("ui-selected"));
            }else{
                Assert.assertFalse(classValue.contains("ui-selected"));
            }
        }
    }

    @Test
    public void TC_04_Click_SelectElement() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable li"));
        action.click(numbers.get(0))
                .keyDown(Keys.CONTROL)
                .click(numbers.get(2))
                .click(numbers.get(5))
                .click(numbers.get(10))
                .keyUp(Keys.CONTROL)
                .release()
                .perform();

        List<WebElement> selectedNumbers = driver.findElements(By.cssSelector("ol#selectable li.ui-selected"));
        List<String> allActualTextNumber = new ArrayList<>();
        for(WebElement e : selectedNumbers){
            allActualTextNumber.add(e.getText());
        }

        Assert.assertEquals(selectedNumbers.size(), 4);

        String[] allExpectedTextNumberArray = {"1", "3", "6", "11"};
        List<String> allExpectedTextNumber = Arrays.asList(allExpectedTextNumberArray);

        Assert.assertEquals(allExpectedTextNumber,allActualTextNumber);
    }

    @Test
    public void TC05_DoubleClick(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
        Assert.assertEquals(driver.findElement(By.id("demo")).getText(),"Hello Automation Guys!");

    }

    @Test
    public void TC06_RightClick(){
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
        WebElement quitElement  = driver.findElement(By.xpath("//ul[contains(@class,'context-menu-list')]/li/span[text()='Quit']"));
        Assert.assertTrue(quitElement.isDisplayed());

        action.moveToElement(quitElement).perform();
        Assert.assertEquals("Quit", driver.findElement(By.cssSelector("ul.context-menu-list>li.context-menu-visible")).getText());
    }

    @Test
    public void TC07_Drag_drop(){
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement small = driver.findElement(By.id("draggable"));
        WebElement big = driver.findElement(By.id("droptarget"));
        action.dragAndDrop(small,big).perform();
        Assert.assertEquals(big.getText(),"You did great!");
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}