package main.org.mhupalo.model

enum CsvHeaders {

    TRADE_NAME("Торгівельне найменування"),
    INTERNATIONAL_NAME("Міжнародне непатентоване найменування"),
    ACTIVE_SUBSTANCES("Склад (діючі)"),
    ATC_CODE("Код АТС"),
    APPLICANT_NAME("Заявник: назва українською"),
    APPLICANT_COUNTRY("Заявник: країна"),
    APPLICANT_ADDRESS("Заявник: адреса"),
    PRODUCER_NAME("Виробник: назва українською"),
    PRODUCER_COUNTRY("Виробник: країна"),
    PRODUCER_ADDRESS("Виробник: адреса"),
    REGISTRATION_CERTIFICATE_NUMBER("Номер Реєстраційного посвідчення"),
    EXPIRATION_DATE("Термін придатності: значення"),
    EXPIRATION_DATE_MEASUREMENT_UNIT("Термін придатності: одиниця вимірювання"),
    EXCIPIENTS("Допоміжні речовини"),
    ACTIVE_SUBSTANCES_SHORTENED("Діючі речовини"),
    DRUG_URL("Посилання на медикамент")

    private String value

    CsvHeaders(String name) {
        this.value = name
    }

    String getValue() {
        this.value
    }

}