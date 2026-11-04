# KursSeleniumTravel

KursSeleniumTravel is a Java UI test automation project for the [Kurs Selenium travel demo site](http://www.kurs-selenium.pl/demo/). It uses Selenium WebDriver and TestNG to exercise hotel search and account sign-up flows. Page Object classes keep page interactions separate from the test assertions.

## Technology

- Java
- Maven
- Selenium WebDriver
- TestNG
- WebDriverManager
- ExtentReports

## Requirements

- JDK and Maven installed
- An internet connection to access the demo site and download the browser driver
- Microsoft Edge (default) or Mozilla Firefox installed

## Run the tests

Run the suite from the project root:

```bash
mvn test
```

By default, the browser is configured in `src/test/resources/config.properties`:

```properties
browser.name = Edge
```

The project uses Edge when this value is `Edge`; any other value selects Firefox. The tests open the demo site in a new browser session for each test. `testng.xml` also defines the `TestHotel` and `LogowanieTest` suites.

## Automated tests

| Test class | Test method | What it tests |
|---|---|---|
| `TestHotel` | `HotelSearchTest` | Searches for hotels in Dubai for the configured dates and two travellers, then verifies the first four returned hotel names. |
| `TestHotel` | `HotelSearchNoResultsTest` | Searches with dates and travellers but no destination, then verifies that the results page displays **No Results Found**. |
| `LogowanieTest` | `SignUpTest` | Fills the sign-up form through individual page actions and verifies the resulting greeting for the new account. |
| `LogowanieTest` | `SignUpTest2` | Fills the sign-up form using a `User` model and verifies the resulting greeting. |
| `LogowanieTest` | `SignUpInvalidTest` | Submits an empty sign-up form and checks that required-field validation messages are displayed. |

The sign-up tests create a random email address on each run. These are browser-based integration tests against the live demo site, so results can depend on that site's availability and behavior.

## Reports

Test execution writes an ExtentReports HTML report to `index.html` in the project root. Screenshots are attached to selected report steps.
