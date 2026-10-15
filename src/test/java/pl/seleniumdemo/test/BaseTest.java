package pl.seleniumdemo.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.seleniumdemo.utils.DriverFactory;

import java.io.IOException;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() throws IOException, InterruptedException {

        driver = DriverFactory.getdriver();
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}
