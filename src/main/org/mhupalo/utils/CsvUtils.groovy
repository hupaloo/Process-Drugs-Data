package main.org.mhupalo.utils

import main.org.mhupalo.model.DrugDto
import org.apache.commons.csv.CSVPrinter
import org.apache.commons.csv.CSVRecord

import java.nio.charset.Charset

import static main.org.mhupalo.model.CsvHeaders.*
import static org.apache.commons.csv.CSVFormat.DEFAULT

class CsvUtils {

    private static final String[] HEADERS = [TRADE_NAME.value, INTERNATIONAL_NAME.value, ACTIVE_SUBSTANCES.value,
                                             ATC_CODE.value, APPLICANT_NAME.value, APPLICANT_COUNTRY.value,
                                             APPLICANT_ADDRESS.value, PRODUCER_NAME.value, PRODUCER_COUNTRY.value, PRODUCER_ADDRESS.value,
                                             REGISTRATION_CERTIFICATE_NUMBER.value, EXPIRATION_DATE.value, EXPIRATION_DATE_MEASUREMENT_UNIT.value,
                                             EXCIPIENTS.value, ACTIVE_SUBSTANCES_SHORTENED.value, DRUG_URL.value]

    private static final String CSV_FILE_PATH = "src/main/org/mhupalo/resources/reestr_atc_final.csv"
    private static final Charset CHARSET = Charset.forName("x-maccyrillic")

    static List<DrugDto> readDrugsListFromCsv() {
        Reader csvFile = new FileReader(CSV_FILE_PATH, CHARSET)

        Iterable<CSVRecord> records = DEFAULT
                .withAllowMissingColumnNames(true)
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(csvFile)

        List<DrugDto> drugsList = []
        for (CSVRecord record : records) {
            DrugDto drugRecord = new DrugDto(
                    record.get(TRADE_NAME.value),
                    record.get(INTERNATIONAL_NAME.value),
                    record.get(ACTIVE_SUBSTANCES.value),
                    record.get(ATC_CODE.value),
                    record.get(APPLICANT_NAME.value),
                    record.get(APPLICANT_COUNTRY.value),
                    record.get(APPLICANT_ADDRESS.value),
                    record.get(PRODUCER_NAME.value),
                    record.get(PRODUCER_COUNTRY.value),
                    record.get(PRODUCER_ADDRESS.value),
                    record.get(REGISTRATION_CERTIFICATE_NUMBER.value),
                    record.get(EXPIRATION_DATE.value),
                    record.get(EXPIRATION_DATE_MEASUREMENT_UNIT.value),
                    record.get(EXCIPIENTS.value),
                    record.get(ACTIVE_SUBSTANCES_SHORTENED.value),
                    record.get(DRUG_URL.value)
            )
            drugsList.add(drugRecord)
        }

        drugsList
    }

    static void writeDrugsListToCsv(List<DrugDto> drugsList) {
        FileWriter out = new FileWriter(CSV_FILE_PATH, CHARSET)
        try (CSVPrinter printer = new CSVPrinter(out, DEFAULT.withHeader(HEADERS))) {
            drugsList.forEach {
                printer.printRecord(
                        it.tradeName,
                        it.internationalName,
                        it.activeSubstances,
                        it.atcCode,
                        it.applicantName,
                        it.applicantCountry,
                        it.applicantAddress,
                        it.producerName,
                        it.producerCountry,
                        it.producerAddress,
                        it.registrationCertificateNumber,
                        it.expirationDateValue,
                        it.expirationDateMeasurementUnit,
                        it.excipients,
                        it.activeSubstancesShortened,
                        it.url
                )
            }
        }
    }

}
