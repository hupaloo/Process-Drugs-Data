package main.org.mhupalo.page_object

import org.openqa.selenium.WebDriver

class DrugPage extends PageObject {

    private static String ACTIVE_SUBSTANCE = "//div[@class='substance row no-gutters']"
    private static final String ATC = "//a[@class='tax atc']"
    private static final String INGREDIENTS = "//div[@class='ingredients']"
    private static final String DRUG_TEXT_SECTION = "//section[@class='collapse show']"

    DrugPage(WebDriver webDriver) {
        super(webDriver)
        waiter.waitUntilPageIsLoaded()
    }

    String getActiveComponentName() {
        if (isElementExists(ACTIVE_SUBSTANCE)) {
            return getElementsText(ACTIVE_SUBSTANCE + "/div[1]")
        } else {
            if (isElementExists(DRUG_TEXT_SECTION)) {
                return getActiveSubstanceFromDrugTextSection(getElementText(DRUG_TEXT_SECTION))
            } else {
                return ""
            }
        }
    }

    String getAtcName() {
        isElementExists(ATC) ? getElementText(ATC) : ""
    }

    String getIngredients() {
        if (isElementExists(INGREDIENTS)) {
            return getElementsText(INGREDIENTS)
        } else {
            if (isElementExists(DRUG_TEXT_SECTION)) {
                return getIngredientsFromDrugTextSection(getElementText(DRUG_TEXT_SECTION))
            } else {
                return ""
            }
        }
    }

    String getUrl() {
        webDriver.currentUrl
    }

    private static String getIngredientsFromDrugTextSection(String drugSectionText) {
        String strBegin = "допоміжні речовини:"
        String strBegin2 = "Допоміжні речовини:"
        if (drugSectionText.contains(strBegin)) {
            int startIndex = drugSectionText.indexOf(strBegin)
            String ingredients = drugSectionText.substring(startIndex)
            int indexOfSemicolon = ingredients.indexOf(";")
            int indexOfDot = ingredients.indexOf(".")
            boolean semicolonExists = indexOfSemicolon != -1
            boolean dotExists = indexOfDot != -1
            int endIndex = 0
            if (semicolonExists && dotExists) {
                endIndex = indexOfSemicolon > indexOfDot ? indexOfDot : indexOfSemicolon
            } else {
                if (semicolonExists) {
                    endIndex = indexOfSemicolon
                }
                if (dotExists) {
                    endIndex = indexOfDot
                }
            }
            return ingredients.substring(0, endIndex)
        } else if (drugSectionText.contains(strBegin2)) {
            int startIndex = drugSectionText.indexOf(strBegin2)
            String ingredients = drugSectionText.substring(startIndex)
            int indexOfSemicolon = ingredients.indexOf(";")
            int indexOfDot = ingredients.indexOf(".")
            boolean semicolonExists = indexOfSemicolon != -1
            boolean dotExists = indexOfDot != -1
            int endIndex = 0
            if (semicolonExists && dotExists) {
                endIndex = indexOfSemicolon > indexOfDot ? indexOfDot : indexOfSemicolon
            } else {
                if (semicolonExists) {
                    endIndex = indexOfSemicolon
                }
                if (dotExists) {
                    endIndex = indexOfDot
                }
            }
            return ingredients.substring(0, endIndex)
        } else {
            return ""
        }
    }

    private static String getActiveSubstanceFromDrugTextSection(String drugSectionText) {
        String strBegin = "діюча речовина:"
        String strBegin2 = "діюча речовина:"
        if (drugSectionText.contains(strBegin)) {
            int startIndex = drugSectionText.indexOf(strBegin)
            String ingredients = drugSectionText.substring(startIndex)
            int endIndex = ingredients.indexOf(";") > ingredients.indexOf(".") ? ingredients.indexOf(".") : ingredients.indexOf(";")
            return ingredients.substring(0, endIndex)
        } else if (drugSectionText.contains(strBegin2)) {
            int startIndex = drugSectionText.indexOf(strBegin2)
            String ingredients = drugSectionText.substring(startIndex)
            int endIndex = ingredients.indexOf(";") > ingredients.indexOf(".") ? ingredients.indexOf(".") : ingredients.indexOf(";")
            return ingredients.substring(0, endIndex)
        } else {
            return ""
        }
    }


}
