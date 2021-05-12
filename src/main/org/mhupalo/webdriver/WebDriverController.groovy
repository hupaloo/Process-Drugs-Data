package main.org.mhupalo.webdriver

import main.org.mhupalo.page_object.DrugPage
import main.org.mhupalo.page_object.MainPage;

class WebDriverController {

    static WebDriverFactory webDriverFactory = new WebDriverFactory()

    static MainPage mainPage
    static DrugPage drugPage

    static void openMainPage(String url) {
        webDriverFactory.getWebDriver().get(url)
        mainPage = new MainPage(webDriverFactory.getWebDriver())
    }

    static boolean findAndClickDrug(String drugName) {
        mainPage.clickOnSearch()
        boolean isDrugClicked = mainPage.findAndClickOnDrugInSearch(drugName.toLowerCase())
        if (!isDrugClicked) {
            clickLogo()
            return false
        } else {
            return true
        }
    }

    static boolean checkFoundDrugsList(String drugName) {
        mainPage = new MainPage(webDriverFactory.getWebDriver())
        boolean areWeOnDrugPage = true
        if (mainPage.areWeOnDrugListsPage()) {
            areWeOnDrugPage = mainPage.processFoundDrugsListPage(drugName)
        }
        areWeOnDrugPage
    }

    static void clickLogo() {
        mainPage.clickOnLogo()
        mainPage = new MainPage(webDriverFactory.getWebDriver())
    }


//    Drug page

    static void initDrugPage() {
        drugPage = new DrugPage(webDriverFactory.getWebDriver())
    }

    static String getActiveComponent() {
        drugPage.getActiveComponentName()
    }

    static String getAtcName() {
        drugPage.getAtcName()
    }

    static void updateWebDriverWaitTimeout(Integer timeout) {
        drugPage.setWebDriverTimeout(timeout)
    }

    static String getIngredients() {
        drugPage.getIngredients()
    }

    static String getUrl() {
        drugPage.getUrl()
    }

    static void closeBrowser() {
        webDriverFactory.closeDriver()
    }

}
