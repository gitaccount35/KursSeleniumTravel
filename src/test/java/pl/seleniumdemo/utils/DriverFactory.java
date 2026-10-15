package pl.seleniumdemo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public class DriverFactory {


    public static WebDriver getdriver() throws IOException, InterruptedException {

        String name= PropertiesLoader.loadProperty("browser.name");

        if (name.equals("Edge")) {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        }
        else {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
    };
}
