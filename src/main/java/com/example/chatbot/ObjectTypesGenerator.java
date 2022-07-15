package com.example.chatbot;

import com.example.chatbot.Management.AuthHeadersManagement;
import com.example.chatbot.Management.TokenManagement;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class ObjectTypesGenerator {
    TokenManagement tokenManager = new TokenManagement();
    RestTemplate restTemplate = new RestTemplate();
    AuthHeadersManagement authHeadersManagement = new AuthHeadersManagement();
    HttpHeaders headers = authHeadersManagement.AuthHeaders("1537");
    HttpHeaders head = authHeadersManagement.AuthHeaders("1555");

    public void generateObjectTypes(JSONObject usersInfo, Map<String, String> bizActivities) {

        JSONObject objectTypes = new JSONObject("{\"objectTypes\":[{\"objectTypeIdentifier\":\"620e07fd1214a58016490e5a\",\"objectTypeDescription\":\"Business\",\"riskDetailsDataGroups\":[{\"dataGroupIdentifier\":\"621067fc1214a58016490ea1\",\"dataGroupName\":\"Business Activity\",\"dataGroupAttributes\":[],\"dataDetailAttributes\":[]},{\"dataGroupIdentifier\":\"6210681f1214a58016490ea3\",\"dataGroupName\":\"Financial\",\"dataGroupAttributes\":[],\"dataDetailAttributes\":[[{\"attributeIdentifier\":\"620e4b861214a58016490e72\",\"attributeName\":\"Estimated Turnover for Current Year\",\"value\":\"\",\"category\":\"\"}]]},{\"dataGroupIdentifier\":\"6210684a1214a58016490ea5\",\"dataGroupName\":\"Exposure\",\"dataGroupAttributes\":[],\"dataDetailAttributes\":[[{\"attributeIdentifier\":\"620e64e01214a58016490e7a\",\"attributeName\":\"Domestic\",\"value\":\"0\",\"category\":\"\"}, {\"attributeIdentifier\":\"620e64f21214a58016490e7c\",\"attributeName\":\"US/Canada\",\"value\":\"0\",\"category\":\"\"}, {\"attributeIdentifier\":\"620e65031214a58016490e7e\",\"attributeName\":\"Australia/NZ\",\"value\":\"0\",\"category\":\"\"}, {\"attributeIdentifier\":\"620e65161214a58016490e80\",\"attributeName\":\"Rest of the world\",\"value\":\"0\",\"category\":\"\"}, {\"attributeIdentifier\":\"620e653a1214a58016490e82\",\"attributeName\":\"By clicking this checkbox, I agree the business does not operate in sanctioned countries.\",\"value\":\"true\",\"category\":\"\"}]]}],\"riskDetailsQuestions\":[]}]}");
        JSONObject exposure = new JSONObject("{\"Domestic\":\"0\", \"US/Canada\":\"0\", \"Australia/NZ\":\"0\", \"Rest of the world\":\"0\"}");

        String[] BizActs = usersInfo.getString("BizActs").split("\n");
        String[] userExposure = usersInfo.getString("Exp").split("\n");

        for (String bizAct : BizActs) {
            JSONArray BATemplate = new JSONArray("[{\"attributeIdentifier\":\"620e49fe1214a58016490e6e\",\"attributeName\":\"Business Code/Description\",\"value\":\"\",\"category\":\"standard\"},{\"attributeIdentifier\":\"620e4b0d1214a58016490e70\",\"attributeName\":\"% of Turnover\",\"value\":\"\",\"category\":\"\"}]");

            String code = bizAct.split(" ")[0];
            String category = bizActivities.get(code);
            int percentage = Integer.parseInt(bizAct.split(": ")[1].substring(0, bizAct.split(": ")[1].lastIndexOf("%")));

            BATemplate.getJSONObject(0).put("value", code);
            BATemplate.getJSONObject(0).put("category", category);
            BATemplate.getJSONObject(1).put("value", percentage);
            objectTypes.getJSONArray("objectTypes").getJSONObject(0).getJSONArray("riskDetailsDataGroups").getJSONObject(0).getJSONArray("dataDetailAttributes").put(BATemplate);

        }

        for (String exp : userExposure) {
            String percentage = exp.split(": ")[1].substring(0, exp.split(": ")[1].lastIndexOf("%"));
            exposure.put(exp.split(": ")[0], percentage);
        }

        for (int i = 0; i < 4; i++) {
            String attributeName = objectTypes.getJSONArray("objectTypes").getJSONObject(0).getJSONArray("riskDetailsDataGroups").getJSONObject(2).getJSONArray("dataDetailAttributes").getJSONArray(0).getJSONObject(i).getString("attributeName");
            objectTypes.getJSONArray("objectTypes").getJSONObject(0).getJSONArray("riskDetailsDataGroups").getJSONObject(2).getJSONArray("dataDetailAttributes").getJSONArray(0).getJSONObject(i).put("value", exposure.getString(attributeName));
        }

        objectTypes.getJSONArray("objectTypes").getJSONObject(0).getJSONArray("riskDetailsDataGroups").getJSONObject(1).getJSONArray("dataDetailAttributes").getJSONArray(0).getJSONObject(0).put("value", String.valueOf(usersInfo.getInt("turnover")));
        HttpEntity<String> httpEntity = new HttpEntity<>(objectTypes.toString(), headers);
        try {
            restTemplate.exchange("https://uat.apis.discovermarket.com/proposal/v2/proposals/" +
                            usersInfo.getString("ProposalId") +
                            "/object-types",
                    HttpMethod.PUT, httpEntity, String.class);
        } catch (HttpClientErrorException e) {
            System.out.println("--Setting new token for object types api--");
            headers.setBearerAuth(tokenManager.CusPropFullTokenization());
            try {
                restTemplate.exchange("https://uat.apis.discovermarket.com/proposal/v2/proposals/" +
                                usersInfo.getString("ProposalId") +
                                "/object-types",
                        HttpMethod.PUT, httpEntity, String.class);
            } catch (HttpServerErrorException E) {
                System.out.println("Server issues");
            }
        } catch (HttpServerErrorException e) {
            System.out.println("Server issues");
        }
    }
//    public void secondObjectTypeGeneration(){
//        String tok = tokenManager.CusPropFullTokenization();
//        head.setBearerAuth(tok);
//        HttpEntity<String> httpEntity = new HttpEntity<>("{\n" +
//                "   \"objectTypes\":[\n" +
//                "      {\n" +
//                "         \"objectTypeIdentifier\":\"620e07fd1214a58016490e5a\",\n" +
//                "         \"objectTypeDescription\":\"Business\",\n" +
//                "         \"riskDetailsDataGroups\":[\n" +
//                "            {\n" +
//                "               \"dataGroupIdentifier\":\"621067fc1214a58016490ea1\",\n" +
//                "               \"dataGroupName\":\"Business Activity\",\n" +
//                "               \"dataGroupAttributes\":[\n" +
//                "                  \n" +
//                "               ],\n" +
//                "               \"dataDetailAttributes\":[\n" +
//                "                  [\n" +
//                "                     {\n" +
//                "                        \"attributeIdentifier\":\"620e49fe1214a58016490e6e\",\n" +
//                "                        \"attributeName\":\"Business Code/Description\",\n" +
//                "                        \"value\":\"B0001\",\n" +
//                "                        \"category\":\"standard\"\n" +
//                "                     },\n" +
//                "                     {\n" +
//                "                        \"attributeIdentifier\":\"620e4b0d1214a58016490e70\",\n" +
//                "                        \"attributeName\":\"% of Turnover\",\n" +
//                "                        \"value\":100,\n" +
//                "                        \"category\":\"\"\n" +
//                "                     }\n" +
//                "                  ]\n" +
//                "               ]\n" +
//                "            },\n" +
//                "            {\n" +
//                "               \"dataGroupIdentifier\":\"6210681f1214a58016490ea3\",\n" +
//                "               \"dataGroupName\":\"Financial\",\n" +
//                "               \"dataGroupAttributes\":[\n" +
//                "                  \n" +
//                "               ],\n" +
//                "               \"dataDetailAttributes\":[\n" +
//                "                  [\n" +
//                "                     {\n" +
//                "                        \"attributeIdentifier\":\"620e4b861214a58016490e72\",\n" +
//                "                        \"attributeName\":\"Estimated Turnover for Current Year\",\n" +
//                "                        \"value\":\"1234566\",\n" +
//                "                        \"category\":\"\"\n" +
//                "                     }\n" +
//                "                  ]\n" +
//                "               ]\n" +
//                "            },\n" +
//                "            {\n" +
//                "               \"dataGroupIdentifier\":\"6210684a1214a58016490ea5\",\n" +
//                "               \"dataGroupName\":\"Exposure\",\n" +
//                "               \"dataGroupAttributes\":[\n" +
//                "                  \n" +
//                "               ],\n" +
//                "               \"dataDetailAttributes\":[\n" +
//                "                  [\n" +
//                "                     {\n" +
//                "                        \"attributeIdentifier\":\"620e64e01214a58016490e7a\",\n" +
//                "                        \"attributeName\":\"Domestic\",\n" +
//                "                        \"value\":\"100\",\n" +
//                "                        \"category\":\"\"\n" +
//                "                     },\n" +
//                "                     {\n" +
//                "                        \"attributeIdentifier\":\"620e64f21214a58016490e7c\",\n" +
//                "                        \"attributeName\":\"US/Canada\",\n" +
//                "                        \"value\":\"0\",\n" +
//                "                        \"category\":\"\"\n" +
//                "                     },\n" +
//                "                     {\n" +
//                "                        \"attributeIdentifier\":\"620e65031214a58016490e7e\",\n" +
//                "                        \"attributeName\":\"Australia/NZ\",\n" +
//                "                        \"value\":\"0\",\n" +
//                "                        \"category\":\"\"\n" +
//                "                     },\n" +
//                "                     {\n" +
//                "                        \"attributeIdentifier\":\"620e65161214a58016490e80\",\n" +
//                "                        \"attributeName\":\"Rest of the world\",\n" +
//                "                        \"value\":\"0\",\n" +
//                "                        \"category\":\"\"\n" +
//                "                     },\n" +
//                "                     {\n" +
//                "                        \"attributeIdentifier\":\"620e653a1214a58016490e82\",\n" +
//                "                        \"attributeName\":\"By clicking this checkbox, I agree the business does not operate in sanctioned countries.\",\n" +
//                "                        \"value\":\"true\",\n" +
//                "                        \"category\":\"\"\n" +
//                "                     }\n" +
//                "                  ]\n" +
//                "               ]\n" +
//                "            }\n" +
//                "         ],\n" +
//                "         \"riskDetailsQuestions\":[\n" +
//                "            \n" +
//                "         ]\n" +
//                "      }\n" +
//                "   ]\n" +
//                "}", head);
//    }
}
