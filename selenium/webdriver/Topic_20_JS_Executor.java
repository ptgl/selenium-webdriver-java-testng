package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_20_JS_Executor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_JE() {
        navigateToUrlByJS("http://live.techpanda.org");
        String domain = executeForBrowser("return document.domain").toString();
        Assert.assertEquals("live.techpanda.org", domain);

        Assert.assertEquals(executeForBrowser("return document.URL").toString(), "http://live.techpanda.org/");

        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");

        hightlightElement("//a[@title='Sony Xperia']/ancestor::div[@class='product-info']//button");
        clickToElementByJS("//a[@title='Sony Xperia']/ancestor::div[@class='product-info']//button");

        String expectedMsg = "Sony Xperia was added to your shopping cart.";
        Assert.assertTrue(getInnerText().contains(expectedMsg));
        Assert.assertTrue(isExpectedTextInInnerText(expectedMsg));

        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");

        String title = executeForBrowser("return document.title").toString();
        Assert.assertEquals(title, "Customer Service");

        scrollToElementOnDown("//input[@id='newsletter']");
        hightlightElement("//input[@id='newsletter']");

        sendkeyToElementByJS("//input[@id='newsletter']", "pgialinh.iuetv@gmail.com");

        hightlightElement("//button[@title='Subscribe']");
        clickToElementByJS("//button[@title='Subscribe']");

        System.out.println(jsExecutor.executeScript("return arguments[0].innerText", driver.findElement(By.cssSelector("li.success-msg span"))).toString());
        String successMsg = getInnerTextOfElement("//li[@class='success-msg']//span");
        Assert.assertEquals(successMsg,"Thank you for your subscription.");

        navigateToUrlByJS("https://www.facebook.com/");
        domain = executeForBrowser("return document.domain").toString();
        Assert.assertEquals("facebook.com", domain);
    }

    @Test
    public void TC_02_Validation_Message() {
        navigateToUrlByJS("https://automationfc.github.io/html5/index.html");

        Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");
        sendkeyToElementByJS("//input[@id='fname']", "test");

        Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Please fill out this field.");
        sendkeyToElementByJS("//input[@id='pass']", "test");

        Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please fill out this field.");
        sendkeyToElementByJS("//input[@id='em']", "test");
        Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please include an '@' in the email address. 'test' is missing an '@'.");
        sendkeyToElementByJS("//input[@id='em']", "test@mail.com");

        Assert.assertEquals(getElementValidationMessage("//select"), "Please select an item in the list.");

    }

    @Test
    public void TC_03_Create_Account() {
        navigateToUrlByJS("http://live.techpanda.org/");
        clickToElementByJS("//a[@data-target-element='#header-account']");
        clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
        clickToElementByJS("//a[@title='Create an Account']");

        sendkeyToElementByJS("//input[@name='firstname']", "automation");
        sendkeyToElementByJS("//input[@name='lastname']","testing");
        sendkeyToElementByJS("//input[@name='email']",getRandomEmail());
        sendkeyToElementByJS("//input[@name='password']","Abcd@1234");
        sendkeyToElementByJS("//input[@name='confirmation']","Abcd@1234");

        clickToElementByJS("//button[@title='Register']");
        Assert.assertEquals(getInnerTextOfElement("//li[@class='success-msg']//li/span"),"Thank you for registering with Main Website Store.");

        clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
        clickToElementByJS("//a[@title='Log Out']");
        sleepInSeconds(5);

        Assert.assertTrue(executeForBrowser("return document.URL").toString().contains("http://live.techpanda.org"));
    }

    @Test
    public void TC_04_Broken_Img(){
        navigateToUrlByJS("https://automationfc.github.io/basic-form/index.html");

        //Broken image
        Assert.assertFalse(isImageLoaded("//img[@alt='broken_04']"));

        //Not broken image
        Assert.assertTrue(isImageLoaded("//img[@alt='broken_05']"));
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public String getInnerTextOfElement(String locator){
        return (String) jsExecutor.executeScript("return  arguments[0].innerText;", getElement(locator));
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        System.out.println("actual Text "+textActual);
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSeconds(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    private String getRandomEmail(){
        return "automation" + new Random().nextInt(99999) + "@gmail.com";
    }

    private void sleepInSeconds(long seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}