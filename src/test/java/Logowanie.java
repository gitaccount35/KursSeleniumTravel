import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Logowanie {


    @Test
    public void FirstTest() throws InterruptedException {

        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("http://www.kurs-selenium.pl/demo/");

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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

            Thread.sleep(3000);

            WebElement heading = driver.findElement(By.xpath("//h3[@class='RTL']"));
            Assert.assertTrue(heading.getText().contains(name));
            Assert.assertEquals(heading.getText(), "Hi, Piotr Test");


        } finally {
            Thread.sleep(5000);
            driver.quit();
        }

    }




}
