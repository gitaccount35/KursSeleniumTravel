package pl.seleniumdemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger();

    public HotelSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public HotelSearchPage setCity(String city) throws InterruptedException {
        logger.info("Searching for city: " + city);
        location.click();
        search.sendKeys(city);
        Thread.sleep(3000);
        String xpath= String.format("//span[@class='select2-match' and text()='%s']",city);
        driver.findElement(By.xpath(xpath)).click();
        logger.info("hotel search done");
        return this;
    }

    public HotelSearchPage setDates1(String checkindate) throws InterruptedException {

        logger.info("Searching for date from: " + checkindate);
        Thread.sleep(3000);
        searchdate1.click();
        searchdate1.clear();
        searchdate1.sendKeys(checkindate);
        logger.info("date from input done");
        return this;
    }

    public HotelSearchPage setDates2(String checkoutdate) {

        logger.info("Searching for date to: " + checkoutdate);
        searchdate2.click();
        searchdate2.clear();
        searchdate2.sendKeys(checkoutdate);
        logger.info("date to input done");
        return this;
    }

    public HotelSearchPage setTravellers(String children) throws InterruptedException {

        logger.info("Searching for travellers children: " + children);
        persons.click();
        Thread.sleep(3000);
        child.clear();
        child.sendKeys(children);
        child.sendKeys(Keys.ENTER);
        logger.info("travellers search done");
        return this;
    }

    public ResultsSearchPage performSearch()
    {
        wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
        logger.info("hotel search clicked");
        return new ResultsSearchPage(driver);
    }

    public void setMyAccount() {
        myaccount.stream().filter(WebElement::isDisplayed)
                .findFirst().ifPresent(WebElement::click);
    }
    public void setSignup() {
        signup.get(1).click();
    }

}
