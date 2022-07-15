package com.example.chatbot.Management;

import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


import java.net.URI;

public class CustomerIdManagement {
    TokenManagement tokenManager = new TokenManagement(); // call tok
    RestTemplate restTemplate = new RestTemplate();
    AuthHeadersManagement authHeadersManagement = new AuthHeadersManagement();
    public String customerIdGeneration(){
        HttpHeaders CustomerAuthHeaders = authHeadersManagement.AuthHeaders("699");
        String tok = tokenManager.CusPropFullTokenization();
        CustomerAuthHeaders.setBearerAuth(tok);
        HttpEntity<String> httpEntityCustomer = new HttpEntity<>("{\"dateOfBirth\":null,\"dateOfDeath\":null,\"description\":\"\",\"email\":\"\"," +
                "\"externalSourceId\":\"\",\"genderCode\":\"\",\"isoCountryCode\":\"\",\"isoResidenceCountryCode\":\"\",\"lastName\":\"\",\"maritalStatusCode\":\"NK\"," +
                "\"mobile\":\"\",\"name\":\"Primary Insured\",\"companyRegistrationNumber\":\"\",\"companyRegistrationAuthority\":\"\",\"website\":\"\"," +
                "\"personalInformationConsent\":true,\"communication\":[{\"communicationChannelName\":\"\",\"isPreferred\":true,\"communicationDetails\":" +
                "[{\"locationType\":\"\",\"isPreferred\":true,\"blockNumber\":\"\",\"unitNumber\":\"\",\"addressLine1\":\"\",\"addressLine2\":\"\",\"isoCityCode\":\"\"," +
                "\"isoCountryCode\":\"\",\"postalCode\":\"\",\"internationalTelecomCountryCode\":\"\",\"phoneNumber\":\"\",\"emailAddress\":\"\"}]}]" +
                ",\"userLoginMethod\":\"Anonymous\"}", CustomerAuthHeaders);
        ResponseEntity<String> customerIdResponse = restTemplate.exchange("https://uat.apis.discovermarket.com/customer/v2/customers",
                HttpMethod.POST, httpEntityCustomer, String.class);
        JSONObject customerId = new JSONObject(customerIdResponse.getBody());
        return customerId.getJSONObject("data").getString("id");
    }
    public void customerIdUpdate(String cusId, String email){
        HttpHeaders CustomerAuthHeaders = authHeadersManagement.AuthHeaders("1334");
        String tok = tokenManager.CusPropFullTokenization();
        CustomerAuthHeaders.setBearerAuth(tok);
        HttpEntity<String> httpEntityCustomer = new HttpEntity<>("{\n" +
                "   \"id\":\"" +
                cusId +
                "\",\n" +
                "   \"createdById\":\"00000000ab1083230a6539d4\",\n" +
                "   \"updatedById\":\"00000000ab1083230a6539d4\",\n" +
                "   \"createdTs\":\"2022-07-14T02:51:54.314+00:00\",\n" +
                "   \"updatedTs\":\"2022-07-14T02:51:55.153+00:00\",\n" +
                "   \"name\":\"Primary Insured\",\n" +
                "   \"lastName\":\"\",\n" +
                "   \"description\":\"\",\n" +
                "   \"dateOfBirth\":null,\n" +
                "   \"dateOfDeath\":null,\n" +
                "   \"genderCode\":\"0\",\n" +
                "   \"maritalStatusCode\":\"NK\",\n" +
                "   \"mobile\":\"\",\n" +
                "   \"email\":\"" +
                email +
                "\",\n" +
                "   \"isoCountryCode\":\"\",\n" +
                "   \"isoResidenceCountryCode\":\"\",\n" +
                "   \"partyToPartyRelationships\":[\n" +
                "      {\n" +
                "         \"toPartyId\":\"619c9d2e4b0253465a797fd1\",\n" +
                "         \"typeCode\":\"BL\",\n" +
                "         \"typeDescription\":\"BelongTo\"\n" +
                "      }\n" +
                "   ],\n" +
                "   \"partyStatus\":{\n" +
                "      \"statusCode\":\"OP\",\n" +
                "      \"description\":\"Open\",\n" +
                "      \"remarks\":null,\n" +
                "      \"reasonCode\":null,\n" +
                "      \"effectiveDate\":null\n" +
                "   },\n" +
                "   \"customerUserId\":null,\n" +
                "   \"hasConsented\":null,\n" +
                "   \"lastSyncTime\":\"2022-07-14T02:51:54.307+00:00\",\n" +
                "   \"externalSourceId\":\"\",\n" +
                "   \"nationality\":\"\",\n" +
                "   \"countryOfResidence\":\"\",\n" +
                "   \"updatedBy\":\"string\",\n" +
                "   \"dependents\":[\n" +
                "      \n" +
                "   ],\n" +
                "   \"companyRegistrationNumber\":\"\",\n" +
                "   \"companyRegistrationAuthority\":\"\",\n" +
                "   \"website\":\"\",\n" +
                "   \"communications\":[\n" +
                "      {\n" +
                "         \"communicationChannelName\":\"\",\n" +
                "         \"communicationDetails\":[\n" +
                "            {\n" +
                "               \"locationType\":\"\",\n" +
                "               \"blockNumber\":\"\",\n" +
                "               \"unitNumber\":\"\",\n" +
                "               \"addressLine1\":\"\",\n" +
                "               \"addressLine2\":\"\",\n" +
                "               \"isoCityCode\":\"\",\n" +
                "               \"isoCountryCode\":\"\",\n" +
                "               \"postalCode\":\"\",\n" +
                "               \"internationalTelecomCountryCode\":\"\",\n" +
                "               \"phoneNumber\":\"\",\n" +
                "               \"emailAddress\":\"\",\n" +
                "               \"preferred\":false\n" +
                "            }\n" +
                "         ],\n" +
                "         \"preferred\":false\n" +
                "      }\n" +
                "   ],\n" +
                "   \"riskDetails\":null,\n" +
                "   \"contacts\":[\n" +
                "      \n" +
                "   ],\n" +
                "   \"contractPolicyRelations\":null,\n" +
                "   \"updatedByCode\":\"string\"\n" +
                "}", CustomerAuthHeaders);
        ResponseEntity<String> customerIdResponse = restTemplate.exchange("https://uat.apis.discovermarket.com/customer/v2/customers/" + cusId,
                HttpMethod.PUT, httpEntityCustomer, String.class);
        System.out.println("*******");
        JSONObject customerUpdated = new JSONObject(customerIdResponse.getBody());
        System.out.println(customerUpdated);

    }
    public void contactsGeneration(String cusId, String name, String email, String phoneNumber){
        HttpHeaders CustomerAuthHeaders = authHeadersManagement.AuthHeaders("1334");
        String tok = tokenManager.CusPropFullTokenization();
        CustomerAuthHeaders.setBearerAuth(tok);
            HttpEntity<String> httpEntityContacts = new HttpEntity<>("{\n" +
                    "   \"partyName\":\"" +
                    name +
                    "\",\n" +
                    "   \"partyLastName\":\"\",\n" +
                    "   \"department\":\"\",\n" +
                    "   \"designation\":\"\",\n" +
                    "   \"nationalityISOCountryCode\":\"\",\n" +
                    "   \"partyDescription\":\"\",\n" +
                    "   \"personalInformationConsent\":false,\n" +
                    "   \"communications\":[\n" +
                    "      {\n" +
                    "         \"communicationChannelName\":\"email\",\n" +
                    "         \"isPreferred\":false,\n" +
                    "         \"communicationDetails\":[\n" +
                    "            {\n" +
                    "               \"locationType\":\"email\",\n" +
                    "               \"isPreferred\":false,\n" +
                    "               \"blockNumber\":\"\",\n" +
                    "               \"unitNumber\":\"\",\n" +
                    "               \"addressLine1\":\"\",\n" +
                    "               \"addressLine2\":\"\",\n" +
                    "               \"isoCityCode\":\"\",\n" +
                    "               \"isoCountryCode\":\"\",\n" +
                    "               \"postalCode\":\"\",\n" +
                    "               \"internationalTelecomCountryCode\":\"\",\n" +
                    "               \"phoneNumber\":\"\",\n" +
                    "               \"emailAddress\":\"" +
                    email +
                    "\"\n" +
                    "            }\n" +
                    "         ]\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"communicationChannelName\":\"phone\",\n" +
                    "         \"isPreferred\":false,\n" +
                    "         \"communicationDetails\":[\n" +
                    "            {\n" +
                    "               \"locationType\":\"mobile_phone\",\n" +
                    "               \"isPreferred\":false,\n" +
                    "               \"blockNumber\":\"\",\n" +
                    "               \"unitNumber\":\"\",\n" +
                    "               \"addressLine1\":\"\",\n" +
                    "               \"addressLine2\":\"\",\n" +
                    "               \"isoCityCode\":\"\",\n" +
                    "               \"isoCountryCode\":\"\",\n" +
                    "               \"postalCode\":\"\",\n" +
                    "               \"internationalTelecomCountryCode\":\"sg\",\n" +
                    "               \"phoneNumber\":\"" +
                    phoneNumber +
                    "\",\n" +
                    "               \"emailAddress\":\"\"\n" +
                    "            },\n" +
                    "            {\n" +
                    "               \"locationType\":\"land_phone\",\n" +
                    "               \"isPreferred\":false,\n" +
                    "               \"blockNumber\":\"\",\n" +
                    "               \"unitNumber\":\"\",\n" +
                    "               \"addressLine1\":\"\",\n" +
                    "               \"addressLine2\":\"\",\n" +
                    "               \"isoCityCode\":\"\",\n" +
                    "               \"isoCountryCode\":\"\",\n" +
                    "               \"postalCode\":\"\",\n" +
                    "               \"internationalTelecomCountryCode\":\"sg\",\n" +
                    "               \"phoneNumber\":\"" +
                    phoneNumber +
                    "\",\n" +
                    "               \"emailAddress\":\"\"\n" +
                    "            }\n" +
                    "         ]\n" +
                    "      }\n" +
                    "   ],\n" +
                    "   \"partyToPartyRelations\":[\n" +
                    "      {\n" +
                    "         \"toPartyId\":\"" +
                    cusId +
                    "\",\n" +
                    "         \"typeCode\":\"\",\n" +
                    "         \"typeDescription\":\"\"\n" +
                    "      }\n" +
                    "   ],\n" +
                    "   \"partyStatus\":{\n" +
                    "      \"description\":\"\",\n" +
                    "      \"effectiveDate\":null,\n" +
                    "      \"reasonCode\":\"\",\n" +
                    "      \"remarks\":\"\",\n" +
                    "      \"statusCode\":\"\"\n" +
                    "   }\n" +
                    "}", CustomerAuthHeaders);
        ResponseEntity<String> contactsResponse = restTemplate.exchange("https://uat.apis.discovermarket.com/common/v2/contacts" ,
                HttpMethod.POST, httpEntityContacts, String.class);
        System.out.println("*******");
        System.out.println(contactsResponse);
    }
}
