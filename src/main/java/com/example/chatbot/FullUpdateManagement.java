package com.example.chatbot;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class FullUpdateManagement {
    AuthHeadersManagement authHeadersManagement = new AuthHeadersManagement();
    TokenManagement tokenManager = new TokenManagement();
    RestTemplate restTemplateFullUpdate = new RestTemplate();

    public void FullUpdateGeneration(String cusId, String propId) {

        HttpHeaders FullUpdateAuthHeaders = authHeadersManagement.AuthHeaders("4330");
        String tok = tokenManager.CusPropFullTokenization();
        FullUpdateAuthHeaders.setBearerAuth(tok);
        HttpEntity<String> httpEntityFullUpdate = new HttpEntity<>("{\n" +
                "   \"currentStep\":\"fact-finding\",\n" +
                "   \"effectiveDate\":\"2022-07-07T09:21:01.834+00:00\",\n" +
                "   \"expiryDate\":null,\n" +
                "   \"filterSelection\":{\n" +
                "      \"toIsoCurrencyCode\":\"USD\",\n" +
                "      \"fromIsoCurrencyCode\":\"SGD\",\n" +
                "      \"exchangeRate\":null,\n" +
                "      \"areaOfCoverageCode\":null,\n" +
                "      \"minPrice\":null,\n" +
                "      \"membersSelection\":[\n" +
                "         \"62c6a57d1f69c43fecb629f8\"\n" +
                "      ],\n" +
                "      \"maxPrice\":null,\n" +
                "      \"coverSelections\":[\n" +
                "         {\n" +
                "            \"typeCode\":\"COT0000001\",\n" +
                "            \"typeDescription\":\"inpatient_available\",\n" +
                "            \"isSelected\":true\n" +
                "         },\n" +
                "         {\n" +
                "            \"typeCode\":\"COT0000002\",\n" +
                "            \"typeDescription\":\"outpatient_available\",\n" +
                "            \"isSelected\":false\n" +
                "         },\n" +
                "         {\n" +
                "            \"typeCode\":\"COT0000003\",\n" +
                "            \"typeDescription\":\"dental_available\",\n" +
                "            \"isSelected\":false\n" +
                "         },\n" +
                "         {\n" +
                "            \"typeCode\":\"COT0000004\",\n" +
                "            \"typeDescription\":\"maternity_available\",\n" +
                "            \"isSelected\":false\n" +
                "         },\n" +
                "         {\n" +
                "            \"typeCode\":\"COT0000005\",\n" +
                "            \"typeDescription\":\"vision_available\",\n" +
                "            \"isSelected\":false\n" +
                "         },\n" +
                "         {\n" +
                "            \"typeCode\":\"COT0000006\",\n" +
                "            \"typeDescription\":\"wellness_available\",\n" +
                "            \"isSelected\":false\n" +
                "         },\n" +
                "         {\n" +
                "            \"typeCode\":\"COT0000020\",\n" +
                "            \"typeDescription\":\"repatriation_and_evacuation_available\",\n" +
                "            \"isSelected\":false\n" +
                "         }\n" +
                "      ],\n" +
                "      \"areaOfCoverageSelections\":[\n" +
                "         {\n" +
                "            \"code\":\"AC0000001\",\n" +
                "            \"description\":\"worldwide_exclude_usa\",\n" +
                "            \"isSelected\":true\n" +
                "         },\n" +
                "         {\n" +
                "            \"code\":\"AC0000002\",\n" +
                "            \"description\":\"worldwide_include_usa\",\n" +
                "            \"isSelected\":false\n" +
                "         },\n" +
                "         {\n" +
                "            \"code\":\"AC0000003\",\n" +
                "            \"description\":\"asia_include_singapore\",\n" +
                "            \"isSelected\":false\n" +
                "         }\n" +
                "      ],\n" +
                "      \"productOptionsSelections\":null,\n" +
                "      \"tariffOptionsSelections\":null,\n" +
                "      \"sorting\":\"Insurer-AZ\"\n" +
                "   },\n" +
                "   \"name\":\"Proposal-07-July-2022-0520\",\n" +
                "   \"note\":null,\n" +
                "   \"exchangeRate\":1,\n" +
                "   \"currency\":\"SGD\",\n" +
                "   \"quotations\":[\n" +
                "      {\n" +
                "         \"proposalId\":\"" +
                propId +
                "\",\n" +
                "         \"displayOrder\":1,\n" +
                "         \"proposalQuotationId\":\"62c6a57f018fe342ab6f8365\",\n" +
                "         \"productId\":\"620db4d8930b8e4c589482b7\",\n" +
                "         \"quotationDetails\":[\n" +
                "            {\n" +
                "               \"partyId\":\"62c6a57d1f69c43fecb629f8\",\n" +
                "               \"lastSyncTimeStamp\":\"2022-07-07T09:21:03.671Z\",\n" +
                "               \"externalSourceId\":null,\n" +
                "               \"mobileNumber\":\"\",\n" +
                "               \"nationalityIsoCountryCode\":\"\",\n" +
                "               \"email\":\"\",\n" +
                "               \"countryResidenceIsoCountryCode\":\"\",\n" +
                "               \"customerUserId\":\"" +
                cusId +
                "\",\n" +
                "               \"partyDescription\":null,\n" +
                "               \"partyName\":\"Primary Insured\",\n" +
                "               \"partyLastName\":\"\",\n" +
                "               \"dateOfBirth\":null,\n" +
                "               \"dateOfDeath\":null,\n" +
                "               \"genderCode\":\"0\",\n" +
                "               \"maritalStatusCode\":\"NK\",\n" +
                "               \"annualPremiumAmount\":0,\n" +
                "               \"productSelectedList\":[\n" +
                "                  \n" +
                "               ],\n" +
                "               \"partyDependents\":[\n" +
                "                  \n" +
                "               ]\n" +
                "            }\n" +
                "         ],\n" +
                "         \"startDate\":null,\n" +
                "         \"endDate\":\"\",\n" +
                "         \"isSelectedForPolicy\":false,\n" +
                "         \"taxAmount\":0,\n" +
                "         \"totalPremium\":0\n" +
                "      },\n" +
                "      {\n" +
                "         \"proposalId\":\"" +
                propId +
                "\",\n" +
                "         \"displayOrder\":2,\n" +
                "         \"proposalQuotationId\":\"62c6a57fd23dbfc853a7ef72\",\n" +
                "         \"productId\":\"620db4e3930b8e4c589482b9\",\n" +
                "         \"quotationDetails\":[\n" +
                "            {\n" +
                "               \"partyId\":\"62c6a57d1f69c43fecb629f8\",\n" +
                "               \"lastSyncTimeStamp\":\"2022-07-07T09:21:03.671Z\",\n" +
                "               \"externalSourceId\":null,\n" +
                "               \"mobileNumber\":\"\",\n" +
                "               \"nationalityIsoCountryCode\":\"\",\n" +
                "               \"email\":\"\",\n" +
                "               \"countryResidenceIsoCountryCode\":\"\",\n" +
                "               \"customerUserId\":\"" +
                cusId +
                "\",\n" +
                "               \"partyDescription\":null,\n" +
                "               \"partyName\":\"Primary Insured\",\n" +
                "               \"partyLastName\":\"\",\n" +
                "               \"dateOfBirth\":null,\n" +
                "               \"dateOfDeath\":null,\n" +
                "               \"genderCode\":\"0\",\n" +
                "               \"maritalStatusCode\":\"NK\",\n" +
                "               \"annualPremiumAmount\":0,\n" +
                "               \"productSelectedList\":[\n" +
                "                  \n" +
                "               ],\n" +
                "               \"partyDependents\":[\n" +
                "                  \n" +
                "               ]\n" +
                "            }\n" +
                "         ],\n" +
                "         \"startDate\":null,\n" +
                "         \"endDate\":\"\",\n" +
                "         \"isSelectedForPolicy\":false,\n" +
                "         \"taxAmount\":0,\n" +
                "         \"totalPremium\":0\n" +
                "      },\n" +
                "      {\n" +
                "         \"proposalId\":\"" +
                propId +
                "\",\n" +
                "         \"displayOrder\":3,\n" +
                "         \"proposalQuotationId\":\"62c6a57f0ab7438f4aab213e\",\n" +
                "         \"productId\":\"620db4ef930b8e4c589482bb\",\n" +
                "         \"quotationDetails\":[\n" +
                "            {\n" +
                "               \"partyId\":\"62c6a57d1f69c43fecb629f8\",\n" +
                "               \"lastSyncTimeStamp\":\"2022-07-07T09:21:03.671Z\",\n" +
                "               \"externalSourceId\":null,\n" +
                "               \"mobileNumber\":\"\",\n" +
                "               \"nationalityIsoCountryCode\":\"\",\n" +
                "               \"email\":\"\",\n" +
                "               \"countryResidenceIsoCountryCode\":\"\",\n" +
                "               \"customerUserId\":\"" +
                cusId +
                "\",\n" +
                "               \"partyDescription\":null,\n" +
                "               \"partyName\":\"Primary Insured\",\n" +
                "               \"partyLastName\":\"\",\n" +
                "               \"dateOfBirth\":null,\n" +
                "               \"dateOfDeath\":null,\n" +
                "               \"genderCode\":\"0\",\n" +
                "               \"maritalStatusCode\":\"NK\",\n" +
                "               \"annualPremiumAmount\":0,\n" +
                "               \"productSelectedList\":[\n" +
                "                  \n" +
                "               ],\n" +
                "               \"partyDependents\":[\n" +
                "                  \n" +
                "               ]\n" +
                "            }\n" +
                "         ],\n" +
                "         \"startDate\":null,\n" +
                "         \"endDate\":\"\",\n" +
                "         \"isSelectedForPolicy\":false,\n" +
                "         \"taxAmount\":0,\n" +
                "         \"totalPremium\":0\n" +
                "      },\n" +
                "      {\n" +
                "         \"proposalId\":\"" +
                propId +
                "\",\n" +
                "         \"displayOrder\":4,\n" +
                "         \"proposalQuotationId\":\"62c6a57fbde73be9bfd0be00\",\n" +
                "         \"productId\":\"620db4f9930b8e4c589482bd\",\n" +
                "         \"quotationDetails\":[\n" +
                "            {\n" +
                "               \"partyId\":\"62c6a57d1f69c43fecb629f8\",\n" +
                "               \"lastSyncTimeStamp\":\"2022-07-07T09:21:03.671Z\",\n" +
                "               \"externalSourceId\":null,\n" +
                "               \"mobileNumber\":\"\",\n" +
                "               \"nationalityIsoCountryCode\":\"\",\n" +
                "               \"email\":\"\",\n" +
                "               \"countryResidenceIsoCountryCode\":\"\",\n" +
                "               \"customerUserId\":\"" +
                cusId +
                "\",\n" +
                "               \"partyDescription\":null,\n" +
                "               \"partyName\":\"Primary Insured\",\n" +
                "               \"partyLastName\":\"\",\n" +
                "               \"dateOfBirth\":null,\n" +
                "               \"dateOfDeath\":null,\n" +
                "               \"genderCode\":\"0\",\n" +
                "               \"maritalStatusCode\":\"NK\",\n" +
                "               \"annualPremiumAmount\":0,\n" +
                "               \"productSelectedList\":[\n" +
                "                  \n" +
                "               ],\n" +
                "               \"partyDependents\":[\n" +
                "                  \n" +
                "               ]\n" +
                "            }\n" +
                "         ],\n" +
                "         \"startDate\":null,\n" +
                "         \"endDate\":\"\",\n" +
                "         \"isSelectedForPolicy\":false,\n" +
                "         \"taxAmount\":0,\n" +
                "         \"totalPremium\":0\n" +
                "      }\n" +
                "   ],\n" +
                "   \"baseBundleId\":\"620db4ca930b8e4c589482b5\"\n" +
                "}", FullUpdateAuthHeaders);

        restTemplateFullUpdate.exchange("https://dev.apis.discovermarket.com/proposal/v2/proposals/" +
                propId +
                "/full-update", HttpMethod.PUT, httpEntityFullUpdate, String.class);
    }
}