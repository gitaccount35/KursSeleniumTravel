package pl.seleniumdemo.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.model.User;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsSearchPage;
import pl.seleniumdemo.pages.ResultsSignUpPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class LogowanieTest extends BaseTest {


    @Test
    public void SignUpTest() throws InterruptedException {


        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
            hotelSearchPage.setMyAccount();
            hotelSearchPage.setSignup();

            SignUpPage signUpPage = new SignUpPage(driver);

            String name="Piotr";
            signUpPage.setFirstname(name);
            signUpPage.setLastname("Test");
            signUpPage.setPhone("+48500500500");

            int randomNumber=(int)(Math.random()*10000);
            String emailaddress="piotrmail"+randomNumber+"@gmail.com";
            signUpPage.setEmail(emailaddress);
            signUpPage.setPassword("12345678");
            signUpPage.setConfirmpassword("12345678");

            Thread.sleep(3000);
            signUpPage.clickSubmitbutton();

            /*
            //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            //Thread.sleep(5000);
            //WebElement myaccount = driver.findElement(
            //       By.cssSelector("ul.navbar-side:nth-child(1) > li:nth-child(1) > a:nth-child(1)"));
            driver.findElements(
                    By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed)
                    .findFirst().ifPresent(WebElement::click);

            //WebElement signup = driver.findElement(
            //By.cssSelector(".open > ul:nth-child(2) > li:nth-child(2) > a:nth-child(1)"));

            driver.findElements(
                    By.xpath("//a[text()='  Sign Up']")).get(1).click();

            String name="Piotr";
            WebElement firstname= driver.findElement(By.name("firstname"));
            firstname.sendKeys(name);

            WebElement lastname= driver.findElement(By.name("lastname"));
            lastname.sendKeys("Test");

            WebElement phone= driver.findElement(By.name("phone"));
            phone.sendKeys("+48500500500");

            int randomNumber=(int)(Math.random()*10000);
            String emailaddress="piotrmail"+randomNumber+"@gmail.com";
            WebElement email= driver.findElement(By.name("email"));
            email.sendKeys(emailaddress);

            WebElement password= driver.findElement(By.name("password"));
            password.sendKeys("12345678");

            WebElement confirmpassword= driver.findElement(By.name("confirmpassword"));
            confirmpassword.sendKeys("12345678");

            Thread.sleep(3000);
            WebElement submitbutton= driver.findElement(By.xpath("//button[text()=' Sign Up']"));
            submitbutton.click();

            */
            Thread.sleep(3000);

            //WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));

            ResultsSignUpPage resultsSignUpPage = new ResultsSignUpPage(driver);

            Assert.assertTrue(resultsSignUpPage.getHeadingText().contains(name));
            Assert.assertEquals(resultsSignUpPage.getHeadingText(), "Hi, Piotr Test");


        } finally {
            Thread.sleep(5000);

        }

    }

    @Test
    public void SignUpTest2() throws InterruptedException {


        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
            hotelSearchPage.setMyAccount();
            hotelSearchPage.setSignup();

            SignUpPage signUpPage = new SignUpPage(driver);

            User user = new User();
            user.setFistname("Piotr");
            user.setLastname("Test");
            user.setPhone("+48500500500");

            int randomNumber=(int)(Math.random()*10000);
            String emailaddress="piotrmail"+randomNumber+"@gmail.com";

            user.setEmail(emailaddress);
            user.setPassword("12345678");
            user.setConfirmpassword("12345678");

            Thread.sleep(3000);

            signUpPage.fillSignUpForm(user);

            Thread.sleep(3000);

            //WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));

            ResultsSignUpPage resultsSignUpPage = new ResultsSignUpPage(driver);

            Assert.assertTrue(resultsSignUpPage.getHeadingText().contains(user.getFistname()));
            Assert.assertEquals(resultsSignUpPage.getHeadingText(), "Hi, Piotr Test");


        } finally {
            Thread.sleep(5000);

        }

    }

    @Test
    public void SignUpInvalidTest() throws InterruptedException {

        try {

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
            hotelSearchPage.setMyAccount();
            hotelSearchPage.setSignup();
            Thread.sleep(3000);
            SignUpPage signUpPage = new SignUpPage(driver);
            signUpPage.clickSubmitbutton();

            List <String> errors=signUpPage.getErrors();

            /*
            driver.findElements(
                            By.xpath("//li[@id='li_myaccount']")).stream().filter(WebElement::isDisplayed)
                    .findFirst().ifPresent(WebElement::click);

            //WebElement signup = driver.findElement(
            //By.cssSelector(".open > ul:nth-child(2) > li:nth-child(2) > a:nth-child(1)"));

            driver.findElements(
                    By.xpath("//a[text()='  Sign Up']")).get(1).click();


            Thread.sleep(3000);
            WebElement submitbutton= driver.findElement(By.xpath("//button[text()=' Sign Up']"));
            submitbutton.click();

            Thread.sleep(3000);

            List<String> errors= driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
                    .stream().map(WebElement::getText)
                    .collect(Collectors.toList());

            //errors.forEach(System.out::println);
            */

            SoftAssert softAssert= new SoftAssert();
            softAssert.assertTrue(errors.contains("The Email field is required."));
            softAssert.assertTrue(errors.contains("The Password field is required."));
            softAssert.assertTrue(errors.contains("The Password field is required."));
            softAssert.assertTrue(errors.contains("The First name field is required."));
            softAssert.assertTrue(errors.contains("The Last Name field is required."));
            softAssert.assertAll();


          } finally {
            Thread.sleep(5000);

        }

    }

}
