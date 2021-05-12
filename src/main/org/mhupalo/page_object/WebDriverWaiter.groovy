package main.org.mhupalo.page_object

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class WebDriverWaiter {

    WebDriverWait wait

    WebDriverWaiter(WebDriver webDriver, long timeOutInSeconds) {
        this.wait = new WebDriverWait(webDriver, timeOutInSeconds)
    }

    void waitUntilElementVisible(String xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)))
    }

    void waitUntilPageIsLoaded() {
        wait.until { (((JavascriptExecutor) it).executeScript("return document.readyState") == "complete") }
    }

}
