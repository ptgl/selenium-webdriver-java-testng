package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_19_Windows_Tab {
    WebDriver driver;
    WebDriver driver2;


    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        driver2 = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Basic_Form() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        String parentId = driver.getWindowHandle();
        String parentTitle = "Selenium WebDriver";
        String ggTitle = "Google";
        String fbTitle = "Facebook – log in or sign up";
        String tkTitle = "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh";

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        switchToWindowByTitle(ggTitle);
        Assert.assertEquals(driver.getTitle(), ggTitle);

        switchToWindowByTitle(parentTitle);
        Assert.assertEquals(driver.getTitle(), parentTitle);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        switchToWindowByTitle(fbTitle);
        Assert.assertEquals(driver.getTitle(), fbTitle);

        switchToWindowByTitle(parentTitle);
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();

        switchToWindowByTitle(tkTitle);
        Assert.assertEquals(driver.getTitle(), tkTitle);

        clossAllWindowsExceptParent(parentId);

    }

    @Test
    public void TC_02_Compare_Mobile() {
        driver.get("http://live.techpanda.org/");
        String parentId = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[@title='Sony Xperia']/ancestor::div[@class='product-info']//li/a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/ancestor::div[@class='product-info']//li/a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Samsung Galaxy has been added to comparison list.");
        driver.findElement(By.cssSelector("button[title='Compare']")).click();

        switchToWindowByTitle("'Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(),"COMPARE PRODUCTS");
        clossAllWindowsExceptParent(parentId);
        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        driver.switchTo().alert().accept();
        sleepInSeconds(1);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The comparison list was cleared.");


    }

    @Test
    public void TC_03_Dictionary() {
        driver.get("https://dictionary.cambridge.org/vi/");
        String parentId = driver.getWindowHandle();
        driver.findElement(By.xpath("//header//span[text()='Đăng nhập']")).click();
        switchToWindowByTitle("Login");
        Assert.assertEquals(driver.getTitle(),"Login");
        driver.findElement(By.cssSelector("#login form.gigya-login-form input[type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#login span[data-bound-to='password']")).getText(),"This field is required");
        Assert.assertEquals(driver.findElement(By.cssSelector("#login span[data-bound-to='loginID']")).getText(),"This field is required");
        clossAllWindowsExceptParent(parentId);
        driver.findElement(By.cssSelector("input[aria-label='Tìm kiếm']")).sendKeys("automation", Keys.ENTER);
        Assert.assertEquals(driver.findElement(By.cssSelector("span.headword>span")).getText(), "automation");
    }

    @Test
    public void TC_04_Selenium4() {
        driver.get("https://kynaenglish.vn/");
        String parentId = driver.getWindowHandle();
        printInfo();

        driver.switchTo().newWindow(WindowType.WINDOW).get("https://kynaenglish.vn/kyna-english-teachers");
        printInfo();
        driver.findElement(By.cssSelector("input[placeholder='Nhập tên Giáo viên']")).sendKeys("Marry");
        sleepInSeconds(3);

        driver.switchTo().newWindow(WindowType.TAB).get("https://www.facebook.com/");
        printInfo();
        driver.findElement(By.id("email")).sendKeys("mail@mail.com");
        sleepInSeconds(3);

        clossAllWindowsExceptParent(parentId);
        printInfo();
        sleepInSeconds(3);
    }

    @Test
    public void TC_05_Two_Drivers(){
        driver.get("http://live.techpanda.org/");
        driver2.get("https://dictionary.cambridge.org/vi/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[@title='Sony Xperia']/ancestor::div[@class='product-info']//li/a[@class='link-compare']")).click();

        driver2.findElement(By.cssSelector("input[aria-label='Tìm kiếm']")).sendKeys("automation", Keys.ENTER);
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");
        Assert.assertEquals(driver2.findElement(By.cssSelector("span.headword>span")).getText(), "automation");

        driver2.quit();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }

    private void printInfo(){
        System.out.println("Driver Id: "+driver.toString());
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
    }

    private void switchToWindowByTitle(String tilte){
        Set<String> windows= driver.getWindowHandles();
        for (String id : windows){
            driver.switchTo().window(id);
            if(driver.getTitle().equals(tilte)){
                driver.switchTo().window(id);
                return;
            }
        }
    }

    private void clossAllWindowsExceptParent(String parentId){
        Set<String> windows= driver.getWindowHandles();
        for (String id : windows){
            driver.switchTo().window(id);
            if(!id.equals(parentId)){
                driver.close();
            }
        }
        driver.switchTo().window(parentId);
    }

    private void sleepInSeconds(long seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}