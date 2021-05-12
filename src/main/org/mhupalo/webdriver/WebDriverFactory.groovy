package main.org.mhupalo.webdriver

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

import static java.util.concurrent.TimeUnit.SECONDS

class WebDriverFactory {

    private static final long DEFAULT_IMPLICITLY_WAIT = 4

    public static WebDriver webDriver

    static WebDriver getWebDriver() {
        if (webDriver == null) {
            WebDriverManager.chromedriver().setup()
            webDriver = new ChromeDriver()
            webDriver.manage().timeouts().pageLoadTimeout(DEFAULT_IMPLICITLY_WAIT, SECONDS)
            webDriver.manage().timeouts().setScriptTimeout(DEFAULT_IMPLICITLY_WAIT, SECONDS)
            webDriver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICITLY_WAIT, SECONDS)
            webDriver.manage().window().maximize()
        }
        return webDriver
    }

    static void closeDriver() {
        if (webDriver != null) {
            webDriver.quit()
            webDriver = null
        }
    }

}
