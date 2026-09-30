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
import java.util.List;
import java.util.stream.Collectors;

public class TestHotel {

    @Test
    public void FirstTest() throws InterruptedException {

        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();

        try {
            driver.manage().window().maximize();
            driver.get("http://www.kurs-selenium.pl/demo/");

            //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement location = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("form[name='fCustomHotelSearch'] .select2-choice")));
            location.click();

            WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".select2-drop-active input.select2-input")));
            search.sendKeys("dubai");
            Thread.sleep(5000);
            //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            search.sendKeys(Keys.ENTER);

            WebElement searchdate1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[name='checkin']")));
            searchdate1.clear();
            searchdate1.sendKeys("14/10/2026");

            WebElement searchdate2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[name='checkout']")));
            searchdate2.clear();
            searchdate2.sendKeys("30/10/2026");

            WebElement persons=wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.name("travellers")));
            persons.click();
            Thread.sleep(3000);
            WebElement child=wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.name("child")));
            child.clear();
            child.sendKeys("2");
            child.sendKeys(Keys.ENTER);
            Thread.sleep(2000);

            WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("button.btn-lg:nth-child(3)")));
            submit.submit();
            Thread.sleep(2000);

            List<String> hotelNames=driver.findElements(
                    By.xpath("//h4[contains(@class,'list_title')]//b")).stream()
                    .map((el-> el.getAttribute("textContent")))
                            .collect(Collectors.toList());

            System.out.println(hotelNames.size());
            hotelNames.forEach(System.out::println);

            Assert.assertEquals("Jumeirah Beach Hotel",hotelNames.get(0));
            Assert.assertEquals("Oasis Beach Tower",hotelNames.get(1));
            Assert.assertEquals("Rose Rayhaan Rotana",hotelNames.get(2));
            Assert.assertEquals("Hyatt Regency Perth",hotelNames.get(3));

        } finally {
            Thread.sleep(5000);
            driver.quit();
        }

    }




}
