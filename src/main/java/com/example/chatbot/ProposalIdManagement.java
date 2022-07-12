package com.example.chatbot;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ProposalIdManagement {
    static TokenManagement tokenManager = new TokenManagement();
    static RestTemplate restTemplateProposalId = new RestTemplate();

    public String proposalIdGeneration(String cusId){
        HttpHeaders ProposalAuthHeaders = AuthHeadersManagement.AuthHeaders("130");
        String tok = tokenManager.CusPropFullTokenization();
        ProposalAuthHeaders.setBearerAuth(tok);
        HttpEntity<String> httpEntityProposal = new HttpEntity<>("{\"name\":\"Proposal-07-July-2022-0334\"," +
                "\"customerPartyId\":" +
                "\"" +
                cusId +
                "\"," +
                "\"lobId\":\"61babd043571dd6f65eef3d6\",\"taxRate\":10}", ProposalAuthHeaders); //By right lobId is CyberQuote LOB
        ResponseEntity<String> proposalIdResponse = restTemplateProposalId.exchange("https://dev.apis.discovermarket.com/proposal/v2/proposals",
                HttpMethod.POST, httpEntityProposal, String.class);
        JSONObject proposalId = new JSONObject(proposalIdResponse.getBody());
        String propId = proposalId.getJSONObject("data").getString("id");
        return propId;
    }

}

