package main.org.mhupalo

import main.org.mhupalo.model.DrugDto
import main.org.mhupalo.utils.Constants
import main.org.mhupalo.utils.CsvUtils
import main.org.mhupalo.webdriver.WebDriverController

class ProcessData {

    static void main(String[] args) {
        int index = 0
        try {
            WebDriverController.openMainPage(Constants.COMPENDIUM_MAIN_PAGE_URL)

            List<DrugDto> drugsList = CsvUtils.readDrugsListFromCsv()

            for (int i = 0; i < drugsList.size(); i++) {
                println("Processing drug № $i...")
                index = i
                String drugName = processDbDrugName(drugsList.get(i).tradeName)

                boolean isDrugClicked = WebDriverController.findAndClickDrug(drugName)
                boolean areWeOnDrugPage = false
                if (isDrugClicked)
                    areWeOnDrugPage = WebDriverController.checkFoundDrugsList(drugName)
                if (isDrugClicked && areWeOnDrugPage) {
                    WebDriverController.initDrugPage()

                    String atcName = WebDriverController.getAtcName()
                    WebDriverController.updateWebDriverWaitTimeout(1)
                    String activeComponentNames = processActiveSubstanceName(WebDriverController.getActiveComponent())
                    String ingredientNames = processIngredientName(WebDriverController.getIngredients())
                    WebDriverController.updateWebDriverWaitTimeout(5)
                    String url = WebDriverController.getUrl()

                    drugsList.get(i).activeSubstancesShortened = activeComponentNames
                    drugsList.get(i).excipients = ingredientNames
                    drugsList.get(i).url = url
                    if (!atcName.isEmpty()) {
                        drugsList.get(i).atcCode = atcName
                    }

                }

                WebDriverController.clickLogo()

                if (i % 5 == 0) {
                    CsvUtils.writeDrugsListToCsv(drugsList)
                }

            }

            CsvUtils.writeDrugsListToCsv(drugsList)

        }
        finally {
            WebDriverController.closeBrowser()
            println("End index: $index")
        }
    }

    private static String processDbDrugName(String drugName) {
        String processedDrugName = drugName.replaceAll("®", "").replaceAll("™", "")
                .replaceAll("\"", "").replaceAll("`", "\'")
        if (processedDrugName.contains("(")) {
            processedDrugName = processedDrugName.substring(0, drugName.indexOf("("))
        }
        if (processedDrugName.contains("/")) {
            processedDrugName = processedDrugName.substring(0, drugName.indexOf("/"))
        }
        if (processedDrugName.contains("[")) {
            processedDrugName = processedDrugName.substring(0, drugName.indexOf("["))
        }
        processedDrugName.trim()
    }

    private static String processIngredientName(String ingredients) {
        ingredients ?: ingredients.replace("допоміжні речовини:", "").replace("Допоміжні речовини:", "").trim()
    }

    private static String processActiveSubstanceName(String activeSubstance) {
        activeSubstance ?: activeSubstance.replace("діюча речовина:", "").replace("Діюча речовина:", "").trim()
    }

}
