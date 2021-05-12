package main.org.mhupalo.page_object

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

class MainPage extends PageObject {

    private final String container = "//div[@id='site']//nav[@class='bg-secondary py-2']"
    private final String mainLogo = "//a[@href='https://compendium.com.ua/uk/']"
    private final String article = "//article"
    private final String inputForm = "//input[@id='search']"
    private final String searchHints = "//div[@id='hints']"
    private static final Integer HINTS_SHOWN_NUMBER = 9
    private String drugsInSearch = searchHints + "//a"

    MainPage(WebDriver webDriver) {
        super(webDriver)
        waiter.waitUntilPageIsLoaded()
    }

    boolean areWeOnDrugListsPage() {
        isElementExists(article) && getElementText(article).toLowerCase().contains("знайдено")
    }

    boolean processFoundDrugsListPage(String drugName) {
        String xpathDrugs = article + "//a"
        waiter.waitUntilElementVisible(xpathDrugs)
        int drugIndex = 0

        List<WebElement> drugs = findElementsByXpath(xpathDrugs)
        for (int i = 0; i < drugs.size(); i++) {
            if (drugs.get(i).getText().toLowerCase() == drugName.toLowerCase()) {
                drugIndex = i + 1
                break
            }
        }

        if (drugIndex == 0) {
            for (int i = 0; i < drugs.size(); i++) {
                if (drugs.get(i).getText().toLowerCase().startsWith(drugName.toLowerCase())) {
                    drugIndex = i + 1
                    break
                }
            }
        }

        if (drugIndex == 0) {
            for (int i = 0; i < drugs.size(); i++) {
                if (drugs.get(i).getText().toLowerCase().contains(drugName.toLowerCase())) {
                    drugIndex = i + 1
                    break
                }
            }
        }

        if (drugIndex != 0) {
            clickOn("($xpathDrugs)[$drugIndex]")
            return true
        } else {
            return false
        }
    }

    void clickOnSearch() {
        clickOnElement(inputForm)
    }

    void clickOnLogo() {
        clickOnElement(mainLogo)
    }

    boolean findAndClickOnDrugInSearch(String searchQuery) {
        doSendKeys(inputForm, searchQuery)
        try {
            waiter.waitUntilElementVisible(searchHints)
        } catch (Exception ignored) {}
        isElementVisible(searchHints) ? clickOnDrugInSearch(searchQuery) : false
    }

    boolean clickOnDrugInSearch(String drugName) {
        List<WebElement> drugs = findElementsByXpath(drugsInSearch)
        boolean isDrugClicked = false
        int drugsFoundNumber = drugs.size() > HINTS_SHOWN_NUMBER ? HINTS_SHOWN_NUMBER : drugs.size()
        for (int i = 0; i < drugsFoundNumber; i++) {
            if (drugs.get(i).getText().toLowerCase() == drugName.toLowerCase()) {
                int elementIndex = i + 1
                clickOnElement(drugsInSearch + "[$elementIndex]")
                isDrugClicked = true
            }
        }
        if (!isDrugClicked) {
            for (int i = 0; i < drugsFoundNumber; i++) {
                if (drugs.get(i).getText().toLowerCase().startsWith(drugName.toLowerCase())) {
                    int elementIndex = i + 1
                    clickOnElement(drugsInSearch + "[$elementIndex]")
                    isDrugClicked = true
                }
            }
        }
        if (!isDrugClicked) {
            for (int i = 0; i < drugsFoundNumber; i++) {
                if (drugs.get(i).getText().toLowerCase().contains(drugName.toLowerCase())) {
                    int elementIndex = i + 1
                    clickOnElement(drugsInSearch + "[$elementIndex]")
                    isDrugClicked = true
                }
            }
        }
        if (!isDrugClicked && drugsFoundNumber != 0) {
            clickOnElement("($drugsInSearch)[1]")
            isDrugClicked = true
        }
        isDrugClicked
    }

}
