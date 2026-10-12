package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.seleniumdemo.model.User;

import java.util.List;
import java.util.stream.Collectors;

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

    @FindBy(xpath="//div[@class='alert alert-danger']//p")
    List<WebElement> errors;


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

    public List<String> getErrors(){

        return errors.stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void fillSignUpForm(User user) {

        firstname.sendKeys(user.getFistname());
        lastname.sendKeys(user.getLastname());
        phone.sendKeys(user.getPhone());
        email.sendKeys(user.getEmail());
        password.sendKeys(user.getPassword());
        confirmpassword.sendKeys(user.getPassword());
        submitbutton.click();
    }


}
