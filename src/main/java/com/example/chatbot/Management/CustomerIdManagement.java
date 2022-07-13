package com.example.chatbot.Management;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CustomerIdManagement {
    TokenManagement tokenManager = new TokenManagement(); // call tok
    RestTemplate restTemplateCustomerId = new RestTemplate();
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
        ResponseEntity<String> customerIdResponse = restTemplateCustomerId.exchange("https://dev.apis.discovermarket.com/customer/v2/customers",
                HttpMethod.POST, httpEntityCustomer, String.class);
        JSONObject customerId = new JSONObject(customerIdResponse.getBody());
        return customerId.getJSONObject("data").getString("id");
    }

}
