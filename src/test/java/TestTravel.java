import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestTravel {

    @Test
    public void FirstTest() throws InterruptedException {

        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();

        try {
            driver.manage().window().maximize();
            driver.get("http://www.kurs-selenium.pl/demo/");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement location = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("form[name='fCustomHotelSearch'] .select2-choice")));
            location.click();

            WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".select2-drop-active input.select2-input")));
            search.sendKeys("Dubai");

            WebElement searchdate1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[name='checkin']")));
            searchdate1.clear();
            searchdate1.sendKeys("14/10/2026");

            WebElement searchdate2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[name='checkout']")));
            searchdate2.clear();
            searchdate2.sendKeys("30/10/2026");

            WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("button.btn-lg:nth-child(3)")));
            submit.submit();

        } finally {
            Thread.sleep(5000);
            driver.quit();
        }

    }




}
