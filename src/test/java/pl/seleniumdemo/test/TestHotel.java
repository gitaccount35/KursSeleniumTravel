package pl.seleniumdemo.test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsSearchPage;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class TestHotel extends BaseTest {

    @Test
    public void HotelSearchTest() throws InterruptedException, IOException {


        try {

            HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);

            ExtentTest test = extentReports.createTest("Hotel Search Test");

            List<String> hotelNames = hotelSearchPage.setCity("Dubai")
                            .setDates1("14/10/2026")
                            .setDates2("30/10/2026")
                            .setTravellers("2")
                            .performSearch().getHotelNames();

            test.log(Status.PASS, "Hotel search done. ");
            test.log(Status.PASS, "Hotel dates set. ");
            test.log(Status.PASS, "Setting travelers done. ");
            test.log(Status.PASS, "Performing search done. ", SeleniumHelper.getScreenshot(driver));

            /*
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

            WebElement persons = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.name("travellers")));
            persons.click();
            Thread.sleep(3000);
            WebElement child = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.name("child")));
            child.clear();
            child.sendKeys("2");
            child.sendKeys(Keys.ENTER);
            Thread.sleep(2000);

            WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("button.btn-lg:nth-child(3)")));
            submit.submit();
            Thread.sleep(2000);
            */

           // ResultsSearchPage resultsSearchPage = new ResultsSearchPage(driver);
            //List<String> hotelNames=resultsSearchPage.getHotelNames();

            /*


            List<String> hotelNames = driver.findElements(
                            By.xpath("//h4[contains(@class,'list_title')]//b")).stream()
                    .map((el -> el.getAttribute("textContent")))
                    .collect(Collectors.toList());
            */
            System.out.println(hotelNames.size());
            hotelNames.forEach(System.out::println);

            Assert.assertEquals(hotelNames.get(0),"Jumeirah Beach Hotel" );
            Assert.assertEquals(hotelNames.get(1),"Oasis Beach Tower" );
            Assert.assertEquals( hotelNames.get(2),"Rose Rayhaan Rotana");
            Assert.assertEquals(hotelNames.get(3),"Hyatt Regency Perth" );

            test.log(Status.PASS, "Assertions passed. ", SeleniumHelper.getScreenshot(driver));

        } finally {
            Thread.sleep(5000);

        }

    }
    @Test
    public void HotelSearchNoResultsTest() throws InterruptedException, IOException {


        try {

            HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);

            ExtentTest test = extentReports.createTest("Hotel Search No Result Test");

            hotelSearchPage.setDates1("14/10/2026");
            test.log(Status.PASS, "Setting checkin date done. ", SeleniumHelper.getScreenshot(driver));

            Thread.sleep(2000);
            hotelSearchPage.setDates2("30/10/2026");
            test.log(Status.PASS, "Setting checkout date done. ", SeleniumHelper.getScreenshot(driver));
            Thread.sleep(3000);
            hotelSearchPage.setTravellers("2");
            test.log(Status.PASS, "Setting travellers done. ", SeleniumHelper.getScreenshot(driver));
            Thread.sleep(3000);
            hotelSearchPage.performSearch();
            test.log(Status.PASS, "Performing search done. ", SeleniumHelper.getScreenshot(driver));

            /*
            //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


            WebElement searchdate1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[name='checkin']")));
            searchdate1.clear();
            searchdate1.sendKeys("14/10/2026");

            WebElement searchdate2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("input[name='checkout']")));
            searchdate2.clear();
            searchdate2.sendKeys("30/10/2026");

            WebElement persons = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.name("travellers")));
            persons.click();
            Thread.sleep(3000);
            WebElement child = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.name("child")));
            child.clear();
            child.sendKeys("2");
            child.sendKeys(Keys.ENTER);
            Thread.sleep(2000);

            WebElement submitbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[text()=' Search']")));
                    // By.cssSelector("button.btn-lg:nth-child(3)")));
            submitbutton.click();
            Thread.sleep(2000);

             */
            ResultsSearchPage resultsSearchPage = new ResultsSearchPage(driver);

            String text =resultsSearchPage.getHotelName();

            /*
            WebElement text=wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h2[@class='text-center']")));
            */

            Assert.assertEquals(text,"No Results Found");

            test.log(Status.PASS, "Assertion passed. ", SeleniumHelper.getScreenshot(driver));


        } finally {
        Thread.sleep(5000);

    }
    }

}