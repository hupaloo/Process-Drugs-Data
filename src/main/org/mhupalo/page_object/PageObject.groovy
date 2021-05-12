package main.org.mhupalo.page_object

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.PageFactory

import static java.util.concurrent.TimeUnit.SECONDS

class PageObject {

    private static final Integer TIMEOUT = 4

    WebDriver webDriver
    WebDriverWaiter waiter

    PageObject(WebDriver webDriver) {
        this.webDriver = webDriver
        setWebDriverTimeout(TIMEOUT)
        this.waiter = new WebDriverWaiter(webDriver, TIMEOUT)
        PageFactory.initElements(webDriver, this)
    }

    public setWebDriverTimeout(Integer timeout) {
        this.webDriver.manage().timeouts().pageLoadTimeout(timeout, SECONDS)
        this.webDriver.manage().timeouts().setScriptTimeout(timeout, SECONDS)
        this.webDriver.manage().timeouts().implicitlyWait(timeout, SECONDS)
    }

    protected void clickOn(String xpath) {
        WebElement element = findElementByXpath(xpath)
        JavascriptExecutor executor = (JavascriptExecutor)webDriver
        executor.executeScript("arguments[0].click();", element)
    }

    protected void clickOnElement(String xpath) {
        WebElement element = findElementByXpath(xpath)
        element.click()
    }

    protected void doSendKeys(String xpath, String text) {
        WebElement element = findElementByXpath(xpath)
        element.sendKeys(text)
    }

    protected String getElementText(String xpath) {
        WebElement element = findElementByXpath(xpath)
        element.getText()
    }

    protected String getElementsText(String xpath) {
        List<WebElement> elements = findElementsByXpath(xpath)
        String text = ""
        for (int i = 0; i < elements.size(); i++) {
            text += elements.get(i).getText()
            if (i != elements.size() - 1) {
                text += ", "
            }
        }
        text
    }

    protected boolean isElementExists(String xpath) {
        findElementsByXpath(xpath).size > 0
    }

    protected boolean isElementVisible(String xpath) {
        findElementByXpath(xpath).isDisplayed()
    }

    protected WebElement findElementByXpath(String xpath) {
        webDriver.findElement(By.xpath(xpath))
    }

    protected List<WebElement> findElementsByXpath(String xpath) {
        webDriver.findElements(By.xpath(xpath))
    }

}
