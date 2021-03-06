package com.example.chatbot;

import com.example.chatbot.Management.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DialogflowFulfillment {
    // To hold all related info for each unique user
    JSONObject userInfo = new JSONObject();
    //List of business activities that are marked as excluded
    List<String> excludedBizActivities = new ArrayList<>();
    Map<String, String> BizActivities = new HashMap<>();
    RestTemplate restTemplate = new RestTemplate();
    ObjectTypesGenerator objectTypesGenerator = new ObjectTypesGenerator();
    TokenManagement tokenManagement = new TokenManagement();
    HttpHeaders headers = new HttpHeaders();
    CustomerIdManagement customerIdManager = new CustomerIdManagement();
    ProposalIdManagement proposalIdManager = new ProposalIdManagement();
    FullUpdateManagement fullUpdateManagement = new FullUpdateManagement();
    AuthHeadersManagement authHeadersManagement = new AuthHeadersManagement();
    HttpHeaders reCalcHeaders = authHeadersManagement.AuthHeadersNoLength();
    JSONArray activitiesList;
    ResponseEntity<String> BAResponse = null;
    String tok = tokenManagement.tokenization();
    String cusPropFullTok = tokenManagement.CusPropFullTokenization();
    public JSONObject fulfillment(JSONObject payload) {

        // Parse the payload to retrieve the relevant information
        String sessionID = payload.getString("session"); // To hold the unique sessionID from dialogflow
        String intent = payload.getJSONObject("queryResult").getJSONObject("intent").getString("displayName"); // To extract the intent of the user input from the dialogflow request
        JSONObject params = payload.getJSONObject("queryResult").getJSONObject("parameters");
        String user = sessionID.split("/")[sessionID.split("/").length-1];

        // Create unique context objects based on each sessionID
        JSONArray restartContext = new JSONArray("[{\"name\":\"" + sessionID + "/contexts/restart\", \"lifespanCount\": 2}]");
        JSONArray quoteContext = new JSONArray("[{\"name\":\"" + sessionID + "/contexts/quote\", \"lifespanCount\": 2}]");
        JSONArray turnoverContext = new JSONArray("[{\"name\":\"" + sessionID + "/contexts/turnover\", \"lifespanCount\": 2}]");
        JSONArray exposureContext = new JSONArray("[{\"name\":\"" + sessionID + "/contexts/exposure\", \"lifespanCount\": 2}]");
        JSONArray nameContext = new JSONArray("[{\"name\":\"" + sessionID + "/contexts/name\", \"lifespanCount\": 99}]");
        JSONArray nameFollowUpContext = new JSONArray("[{\"name\":\"" + sessionID + "/contexts/name-followup\", \"lifespanCount\": 2}]");


        // To be returned to dialogflow to respond to user. Refer to https://cloud.google.com/dialogflow/es/docs/reference/rest/v2/WebhookResponse to view the correct format to respond to dialogflow
        JSONObject fulfillment = new JSONObject();

        switch (intent) {
            case "Welcome":
                // Use welcome intent to initialise new user

                if (!userInfo.has(user)) {
                    userInfo.put(user, new JSONObject("{\"BA\":0,\"Exposure\":0, \"BizActs\":\"\", \"Exp\":\"\", \"CustomerId\":\"\", \"ProposalId\":\"\", \"turnover\":0}"));
                    try {
                        System.out.println("Initialising new user");

                        String cusId = customerIdManager.customerIdGeneration();
                        //Update UserInfo with CustomerId
                        userInfo.getJSONObject(user).put("CustomerId", cusId);
                        System.out.println(user + " customerId: " + cusId);
                        String propId = proposalIdManager.proposalIdGeneration(cusId);
                        //Update userInfo with ProposalId
                        userInfo.getJSONObject(user).put("ProposalId", propId);
                        System.out.println(user + " proposalId: " + propId);

                        //FullUpdate
                        fullUpdateManagement.FullUpdateGeneration(cusId, propId);
                        System.out.println("Full update done");

                    } catch (HttpClientErrorException | HttpServerErrorException e) {
                        fulfillment.put("fulfillmentText", "We are experiencing technical difficulties, please try again later");
                        break;
                    }
                } else {
                    userInfo.getJSONObject(user).put("BA", 0);
                    userInfo.getJSONObject(user).put("Exposure", 0);
                    userInfo.getJSONObject(user).put("BizActs", "");
                    userInfo.getJSONObject(user).put("Exp", "");
                    userInfo.getJSONObject(user).put("turnover", 0);
                }

                break;
            case "BA":
            case "Exposure":

                // Check if the given business activity is one of the excluded ones
                if (excludedBizActivities.contains(params.getString(intent))) {
                    fulfillment.put("outputContexts", restartContext);
                    fulfillment.put("fulfillmentText", "Your application has been rejected.\nIn case of any questions or inquiries, please contact us:\nEmail: no-reply@brokerabc.com\nPhone: 8888 8888.\n\nTo start over, type \"restart\". Good bye!");
                    return fulfillment;
                }

                int percentage = Integer.parseInt(params.getString("percentage").substring(0, params.getString("percentage").lastIndexOf("%")));

                boolean BA = intent.equals("BA");

                // Update user percentage and input data
                userInfo.getJSONObject(user).put(intent, userInfo.getJSONObject(user).getInt(intent) + percentage);
                userInfo.getJSONObject(user).put(BA ? "BizActs" : "Exp", userInfo.getJSONObject(user).getString(BA ? "BizActs" : "Exp")
                        + params.getString(intent) + ": " + params.getString("percentage") + "\n");

                // Validation of input data total business activity or exposure adds up to 100%
                if (userInfo.getJSONObject(user).getInt(intent) == 100) {

                    fulfillment.put("outputContexts", BA ? turnoverContext : nameContext);
                    fulfillment.put("fulfillmentText", BA ? "What is your company's annual turnover?" : "Great, thanks! Now we just need to know a bit more about you. What is your name?");

                } else {

                    fulfillment.put("outputContexts", BA ? quoteContext : exposureContext);
                    if (userInfo.getJSONObject(user).getInt(intent) < 100) {

                        fulfillment.put("fulfillmentText", BA ? "What are your other business activities? (total business activity must add up to 100%)\n\n" + userInfo.getJSONObject(user).getString("BizActs") :
                                "What is your other market exposure? (total exposure must add up to 100%)\n\n" + userInfo.getJSONObject(user).getString("Exp"));
                    } else {

                        fulfillment.put("fulfillmentText", BA ? "Total business activity cannot be more than 100%, please state your business activities again" :
                                "Total exposure cannot be more than 100%, please state your exposure again");
                        userInfo.getJSONObject(user).put(BA ? "BA" : "Exposure", 0);
                        userInfo.getJSONObject(user).put(BA ? "BizActs" : "Exp", "");
                    }
                }
                break;
            case "quote-start":
            case "Welcome - yes":
            case "reset":

                userInfo.getJSONObject(user).put("BA", 0);
                userInfo.getJSONObject(user).put("Exposure", 0);
                userInfo.getJSONObject(user).put("BizActs", "");
                userInfo.getJSONObject(user).put("Exp", "");
                userInfo.getJSONObject(user).put("turnover", 0);

                StringBuilder BizActsString = new StringBuilder("Please enter your business activity and its corresponding percentage (eg. Retail: 20%/ B0001: 30%):\n\n");

                HttpEntity<String> httpEntity = new HttpEntity<>("", headers);

                // Retrieve the risk details from the DCM risk details API
                HttpStatus code;

                if (BAResponse == null) {
                    try {
                        System.out.println("***********");
                        System.out.println("Retrieving business activities");
                        headers.setBearerAuth(tok);
                        BAResponse = restTemplate.exchange("https://product-service-uat.discovermarket.com/v2/riskdetailinfos/619c9d2e4b0253465a797fd1/620db4ca930b8e4c589482b5",
                                HttpMethod.GET, httpEntity, String.class);
                        code = BAResponse.getStatusCode();
                        System.out.println(code);

                    } catch (HttpClientErrorException e) {
                        System.out.println("--Setting new token for risk-detail api--");
                        tok = tokenManagement.tokenization();
                        headers.setBearerAuth(tok);
                        try {
                            BAResponse = restTemplate.exchange("https://product-service-uat.discovermarket.com/v2/riskdetailinfos/619c9d2e4b0253465a797fd1/620db4ca930b8e4c589482b5",
                                    HttpMethod.GET, httpEntity, String.class);
                        } catch (HttpServerErrorException E) {
                            fulfillment.put("fulfillmentText", "We are experiencing technical difficulties, please try again later");
                            break;
                        }
                        code = BAResponse.getStatusCode();
                        System.out.println(code);
                    } catch (HttpServerErrorException e) {
                        fulfillment.put("fulfillmentText", "We are experiencing technical difficulties, please try again later");
                        break;
                    }
                    JSONObject riskDetails = new JSONObject(BAResponse.getBody());
                    activitiesList = riskDetails.getJSONObject("data").getJSONObject("customerCategory").getJSONArray("objectTypes").getJSONObject(0).getJSONArray("riskDetailDataGroups").getJSONObject(0).getJSONArray("dataDetailAttributes").getJSONObject(0).getJSONArray("options");
                }

                // To generate the String of business activities to display to user, as well as to update the list of excluded business activites
                for (int i = 1; i < activitiesList.length(); i++) {
                    JSONObject activity = activitiesList.getJSONObject(i);
                    BizActivities.put(activity.getString("key"), activity.getString("category"));
                    BizActsString.append(activity.getString("key")).append(": ").append(activity.getString("value")).append("\n");
                    if (activity.getString("category").equals("excluded")) {

                        excludedBizActivities.add(activity.getString("key") + " " + activity.getString("value"));
                    }
                }
                fulfillment.put("outputContexts", quoteContext);
                fulfillment.put("fulfillmentText", BizActsString.toString());
                break;
            case "ET":
                int turnover = params.getInt("number");
                userInfo.getJSONObject(user).put("turnover", turnover);
                //Ensure turnover is positive
                if (turnover <= 0) {
                    fulfillment.put("outputContexts", turnoverContext);
                    fulfillment.put("fulfillmentText", "Annual turnover must be a positive non-zero number, please re-enter");
                }
                break;

            case "Name":
                userInfo.getJSONObject(user).put("name", params.getJSONObject("person").getString("name"));


                objectTypesGenerator.generateObjectTypes(userInfo.getJSONObject(user), BizActivities, cusPropFullTok);

                fulfillment.put("outputContexts", nameFollowUpContext);
                fulfillment.put("fulfillmentText", "Hi " + userInfo.getJSONObject(user).getString("name") + "!\nYour current number is "
                        + user.split(":")[1] + ",\nwould you like to enter a new number?");
                break;
            case "Email 0":
                userInfo.getJSONObject(user).put("email", params.getString("Email"));
                System.out.println("----");
                System.out.println("User: " + user);

                httpEntity = new HttpEntity<>("", reCalcHeaders);

                ResponseEntity<String> resp;
                try {
                    reCalcHeaders.setBearerAuth(cusPropFullTok);
                    resp = restTemplate.exchange("https://dev.apis.discovermarket.com/proposal/v2/proposals/" +
                                    userInfo.getJSONObject(user).getString("ProposalId") +
                                    "/re-calculate",
                            HttpMethod.GET, httpEntity, String.class);
                } catch (HttpClientErrorException e)  {
                    System.out.println("--Setting new token for re-calc api--");
                    cusPropFullTok = tokenManagement.CusPropFullTokenization();
                    reCalcHeaders.setBearerAuth(cusPropFullTok);
                    try {
                        resp = restTemplate.exchange("https://dev.apis.discovermarket.com/proposal/v2/proposals/" +
                                        userInfo.getJSONObject(user).getString("ProposalId") +
                                        "/re-calculate",
                                HttpMethod.GET, httpEntity, String.class);
                    } catch (HttpServerErrorException E){
                        System.out.println("DCM SERVER ERROR");
                        fulfillment.put("fulfillmentText", "We are experiencing technical difficulties, please try again later");
                        break;
                    }
                    code = resp.getStatusCode();
                    System.out.println(code);
                } catch (HttpServerErrorException e){
                    System.out.println("DCM SERVER ERROR");
                    fulfillment.put("fulfillmentText", "We are experiencing technical difficulties, please try again later");
                    break;
                }

                userInfo.getJSONObject(user).put("proposal", new JSONObject(resp.getBody()).getJSONObject("data"));
                fulfillment.put("followupEventInput", new JSONObject("{\"name\": \"Email1\"}"));

                break;

            case "Email 1":

                userInfo.getJSONObject(user).put("quoteString", "Thank you, here are your quotes:\n\nStandard --- " + String.format("$%.2f", userInfo.getJSONObject(user).getJSONObject("proposal").getJSONArray("quotations").getJSONObject(0).getFloat("totalPremium")) +
                        "\nSilver --- " + String.format("$%.2f", userInfo.getJSONObject(user).getJSONObject("proposal").getJSONArray("quotations").getJSONObject(1).getFloat("totalPremium"))  +
                        "\nGold --- " + String.format("$%.2f", userInfo.getJSONObject(user).getJSONObject("proposal").getJSONArray("quotations").getJSONObject(2).getFloat("totalPremium")) +
                        "\nPlatinum --- " + String.format("$%.2f", userInfo.getJSONObject(user).getJSONObject("proposal").getJSONArray("quotations").getJSONObject(3).getFloat("totalPremium")) +
                        "\n\nWould you like to receive the full details of the quote in an email sent to " + userInfo.getJSONObject(user).getString("email") + "?");
                fulfillment.put("fulfillmentText", userInfo.getJSONObject(user).getString("quoteString"));
                break;

            case "Email 1 - yes":

                //Refresh Token
                headers = authHeadersManagement.AuthHeadersNoLength();
                httpEntity = new HttpEntity<>("",headers);
                resp = restTemplate.exchange("https://dev.apis.discovermarket.com/common/v2/tokens/validate/a4e5b33319273003eb6f47a663049af8d62fcbd3",
                        HttpMethod.GET, httpEntity, String.class);
                JSONObject mainBody = new JSONObject(resp.getBody());
                String Rid = mainBody.getString("id");
                JSONObject parameters = mainBody.getJSONObject("params");
                JSONObject tenantInfo = mainBody.getJSONObject("tenantInfo");
                System.out.println("----");
                System.out.println(Rid);
                System.out.println("----");
                System.out.println(parameters);
                System.out.println("----");
                System.out.println(tenantInfo);
                System.out.println("----");

                //token API
                headers = authHeadersManagement.AuthHeaders("923");
                headers.setBearerAuth(tokenManagement.CusPropFullTokenization());
                httpEntity = new HttpEntity<>("{\n" +
                        "   \"id\":\"" +
                        Rid +//correct
                        "\",\n" +
                        "   \"token\":\"a4e5b33319273003eb6f47a663049af8d62fcbd3\",\n" + //same
                        "   \"noExpiry\":true,\n" +
                        "   \"expiryDate\":\"2022-05-12T04:18:29.674+00:00\",\n" +
                        "   \"tokenDatetime\":\"2022-05-12T04:23:41.789+00:00\",\n" +
                        "   \"tokenUserId\":\"60dc72afbb70767572d556ce\",\n" +
                        "   \"params\":{\n" +
                        "      \"tenantIdentifier\":\"" +
                        parameters.getString("tenantIdentifier") + //correct
                        "\",\n" +
                        "      \"redirectURL\":\"\",\n" +
                        "      \"userIdentifier\":\"\",\n" +
                        "      \"productIdentifier\":\"620db4ca930b8e4c589482b5\",\n" + //same
                        "      \"objectTypeIdentifier\":\"620e07fd1214a58016490e5a\",\n" + //same
                        "      \"customerCategoryIdentifier\":\"620d23b8aa366a52e2814394\",\n" + //same
                        "      \"proposalIdentifier\":\"" +
                        userInfo.getJSONObject(user).getString("ProposalId") + //correct
                        "\",\n" +
                        "      \"loginMethodIdentifier\":\"" +
                        parameters.getString("loginMethodIdentifier") + //correct
                        "\",\n" +
                        "      \"loginMethodName\":\"Anonymous\",\n" + //same
                        "      \"lobIdentifier\":\"61babd043571dd6f65eef3d6\",\n" + //same
                        "      \"info\":\"Tenant 5 Anonymous Cyber Globe\"\n" + //same
                        "   },\n" +
                        "   \"tenantInfo\":\n" +
                        tenantInfo +
                        "}",headers);
                resp = restTemplate.exchange("https://dev.apis.discovermarket.com/common/v2/tokens",
                        HttpMethod.POST, httpEntity, String.class);
                JSONObject body = new JSONObject(resp.getBody()).getJSONObject("data");
                String magicToken = body.getString("token");
                String tenantId = body.getJSONObject("params").getString("tenantIdentifier");

                //Email API
                headers = authHeadersManagement.AuthHeaders("270");
                headers.setBearerAuth(tokenManagement.CusPropFullTokenization());
                httpEntity = new HttpEntity<>("{\n" +
                        "   \"code\":\"email-quote\",\n" +
                        "   \"tenantId\":\"" +
                        tenantId +
                        "\",\n" +
                        "   \"toEmail\":\"" +
                        userInfo.getJSONObject(user).getString("email") +
                        "\",\n" +
                        "   \"subject\":\"Discovermarket Proposal - " +
                        userInfo.getJSONObject(user).getString("ProposalId") +
                        "\",\n" +
                        "   \"params\":{\n" +
                        "      \"proposal_link\":\"https://dcmp-dev.discovermarket.com/riskdetails/" +
                        magicToken +
                        "\"\n" +
                        "   }\n" +
                        "}",headers);
                restTemplate.exchange("https://dev.apis.discovermarket.com/notification/v2/notification/email",
                        HttpMethod.POST, httpEntity, String.class);
                break;
        }
        return fulfillment;
    }
}
