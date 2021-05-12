package main.org.mhupalo

import main.org.mhupalo.model.DrugDto
import main.org.mhupalo.utils.CsvUtils

class CsvAfterProcessing {

    static void main(String[] args) {

        List<DrugDto> drugsList = CsvUtils.readDrugsListFromCsv()

        drugsList.forEach {
            def ingredients = it.excipients
            if (ingredients.endsWith(".")) {
                ingredients = ingredients.substring(0, ingredients.length() - 1)
            }
            if (ingredients.endsWith(",")) {
                ingredients = ingredients.substring(0, ingredients.length() - 1)
            }
            it.excipients = ingredients
        }

        CsvUtils.writeDrugsListToCsv(drugsList)

    }

}
