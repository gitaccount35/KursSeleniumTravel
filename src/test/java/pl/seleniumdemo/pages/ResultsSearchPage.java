package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class ResultsSearchPage {

    @FindBy(xpath = "//h4[contains(@class,'list_title')]//b")
    List<WebElement> hotelNames;

    @FindBy(xpath = "//h2[@class='text-center']" )
    WebElement hotelName;

    private final WebDriverWait wait;

    public ResultsSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public List<String> getHotelNames() throws InterruptedException {

        Thread.sleep(3000);
        return hotelNames.stream()
                .map((el -> el.getAttribute("textContent")))
                .collect(Collectors.toList());
    }

    public String getHotelName(){
        //musi być przez wait bo inaczej jest blad
        return wait.until(ExpectedConditions.visibilityOf(hotelName)).getText();
    }



}
