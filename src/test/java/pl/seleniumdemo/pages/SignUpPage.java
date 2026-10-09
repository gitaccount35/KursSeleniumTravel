package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    @FindBy(name = "firstname")
    WebElement firstname;

    @FindBy(name= "lastname")
    WebElement lastname;

    @FindBy(name="phone")
    WebElement phone;

    @FindBy(name="email")
    WebElement email;

    @FindBy(name="password")
    WebElement password;

    @FindBy(name="confirmpassword")
    WebElement confirmpassword;

    @FindBy(xpath = "//button[text()=' Sign Up']")
    WebElement submitbutton;


    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void setFirstname(String name) {
        firstname.sendKeys(name);
    }
    public void setLastname(String name) {
        lastname.sendKeys(name);
    }
    public void setPhone(String phonenumber) {
        phone.sendKeys(phonenumber);
    }
    public void setEmail(String emailtext) {

        email.sendKeys(emailtext);
    }
    public void setPassword(String passwordtext) {
        password.sendKeys(passwordtext);
    }
    public void setConfirmpassword(String confirmpasswordtext) {
        confirmpassword.sendKeys(confirmpasswordtext);
    }
    public void clickSubmitbutton() {
        submitbutton.click();
    }



}
