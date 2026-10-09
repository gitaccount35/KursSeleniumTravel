package pl.seleniumdemo.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HotelSearchPage {


    @FindBy(css = "form[name='fCustomHotelSearch'] .select2-choice")
    private WebElement location;

    @FindBy(css= ".select2-drop-active input.select2-input")
    private WebElement search;

    /*   usuniete bo bylo zahardkodowane Dubai i zostalo zmienione
    poprzez dodanie w metodzie nizej setCity odpowiedniej zmiennej miasta

    @FindBy(xpath = "//span[@class='select2-match' and text()='Dubai']")
    private WebElement searchHotel;
    */

    @FindBy(css = "form[name='fCustomHotelSearch'] input[name='checkin']")
    private WebElement searchdate1;

    @FindBy(css = "form[name='fCustomHotelSearch'] input[name='checkout']")
    private WebElement searchdate2;

    @FindBy(name = "travellers")
    private WebElement persons;

    @FindBy(name = "child")
    private WebElement child;

    @FindBy(css = "button.btn-lg:nth-child(3)")
    private WebElement submit;

    @FindBy(xpath = "//li[@id='li_myaccount']")
    List<WebElement> myaccount;

    @FindBy(xpath ="//a[text()='  Sign Up']" )
    List<WebElement> signup;


    private final WebDriverWait wait;

    private WebDriver driver;

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void setCity(String city) throws InterruptedException {
        location.click();
        search.sendKeys(city);
        Thread.sleep(3000);
        String xpath= String.format("//span[@class='select2-match' and text()='%s']",city);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void setDates1(String checkindate) {
        searchdate1.click();
        searchdate1.clear();
        searchdate1.sendKeys(checkindate);
    }

    public void setDates2(String checkoutdate) {

        searchdate2.click();
        searchdate2.clear();
        searchdate2.sendKeys(checkoutdate);
    }

    public void setTravellers(String children) {
        persons.click();
        child.clear();
        child.sendKeys(children);
        child.sendKeys(Keys.ENTER);
    }

    public void performSearch() {
        submit.submit();
    }

    public void setMyAccount() {
        myaccount.stream().filter(WebElement::isDisplayed)
                .findFirst().ifPresent(WebElement::click);
    }
    public void setSignup() {
        signup.get(1).click();
    }

}
