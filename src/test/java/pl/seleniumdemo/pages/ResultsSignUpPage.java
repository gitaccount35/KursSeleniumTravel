package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultsSignUpPage {

    @FindBy(xpath = "//h3[@class='RTL']")
    WebElement heading;

    public ResultsSignUpPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public String getHeadingText() {
        return heading.getText();
    }
}
