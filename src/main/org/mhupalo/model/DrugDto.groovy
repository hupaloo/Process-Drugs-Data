package main.org.mhupalo.model

import groovy.transform.Canonical

@Canonical
class DrugDto {

    String tradeName
    String internationalName
    String activeSubstances
    String atcCode
    String applicantName
    String applicantCountry
    String applicantAddress
    String producerName
    String producerCountry
    String producerAddress
    String registrationCertificateNumber
    String expirationDateValue
    String expirationDateMeasurementUnit

    // custom
    String excipients
    String activeSubstancesShortened
    String url

}
