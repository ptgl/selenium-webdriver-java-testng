package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Topic_05_Register_Practice_Xpath {
    WebDriver driver;
    By txtEmailError = By.id("txtEmail-error");
    By txtCEmailError = By.id("txtCEmail-error");
    By emailField = By.id("txtEmail");
    By confirmEmailField = By.id("txtCEmail");
    By passwordField = By.id("txtPassword");
    By confirmPassField = By.id("txtCPassword");
    By txtPasswordError = By.id("txtPassword-error");
    By txtCPassError = By.id("txtCPassword-error");
    By phoneNumberField = By.id("txtPhone");
    By txtPhoneError = By.id("txtPhone-error");
    By signUpBtn = By.xpath("//button[text()='ĐĂNG KÝ'and @type='submit']");

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();

    }

    @BeforeMethod
    public void openUrl(){
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

    }

    @Test
    public void TC_01_Register_Empty() {
        driver.findElement(signUpBtn).click();
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(txtEmailError).getText(), "Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(txtCEmailError).getText(), "Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(txtPasswordError).getText(), "Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(txtCPassError).getText(), "Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(txtPhoneError).getText(), "Vui lòng nhập số điện thoại.");

    }

    @Test
    public void TC_02_Register_InvalidEmail() {
        driver.findElement(emailField).sendKeys("aa");
        driver.findElement(signUpBtn).click();
        Assert.assertEquals(driver.findElement(txtEmailError).getText(), "Vui lòng nhập email hợp lệ");
    }

    @Test
    public void TC_03_Register_Incorrect_ConfirmEmail() {
        driver.findElement(emailField).sendKeys("mail@gmail.com");
        driver.findElement(confirmEmailField).sendKeys("aa");
        driver.findElement(signUpBtn).click();
        Assert.assertEquals(driver.findElement(txtCEmailError).getText(), "Email nhập lại không đúng");
    }

    @Test
    public void TC_04_Register_Less6Password() {
        driver.findElement(passwordField).sendKeys("aa");
        driver.findElement(confirmPassField).sendKeys("aa");
        driver.findElement(signUpBtn).click();
        String errorMsg = "Mật khẩu phải có ít nhất 6 ký tự";
        Assert.assertEquals(driver.findElement(txtPasswordError).getText(), errorMsg);
        Assert.assertEquals(driver.findElement(txtCPassError).getText(), errorMsg);
    }
    @Test
    public void TC_05_Register_Incorrect_ConfirmPassword() {
        driver.findElement(passwordField).sendKeys("123456");
        driver.findElement(confirmPassField).sendKeys("abcdefg");
        driver.findElement(signUpBtn).click();
        Assert.assertEquals(driver.findElement(txtCPassError).getText(), "Mật khẩu bạn nhập không khớp");

    }
    @Test
    public void TC_06_Register_Invalid_PhoneNumber() {
        driver.findElement(phoneNumberField).sendKeys("0931");
        driver.findElement(signUpBtn).click();
        Assert.assertEquals(driver.findElement(txtPhoneError).getText(), "Số điện thoại phải từ 10-11 số.");

        driver.findElement(phoneNumberField).clear();
        driver.findElement(phoneNumberField).sendKeys("093145689018392934324");
        driver.findElement(signUpBtn).click();
        Assert.assertEquals(driver.findElement(txtPhoneError).getText(), "Số điện thoại phải từ 10-11 số.");

        driver.findElement(phoneNumberField).clear();
        driver.findElement(phoneNumberField).sendKeys("123");
        driver.findElement(signUpBtn).click();
        Assert.assertEquals(driver.findElement(txtPhoneError).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");

    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}