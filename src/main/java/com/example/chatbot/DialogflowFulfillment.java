package com.example.chatbot;

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
    static JSONObject userInfo = new JSONObject();
    //List of business activities that are marked as excluded
    List<String> excludedBizActivities = new ArrayList<>();
    Map<String, String> BizActivities = new HashMap<>();
    RestTemplate restTemplate = new RestTemplate();
    ObjectTypesGenerator objectTypesGenerator = new ObjectTypesGenerator();
    TokenManagement tokenManagement = new TokenManagement();
    HttpHeaders headers = new HttpHeaders();
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

        // To be returned to dialogflow to respond to user. Refer to https://cloud.google.com/dialogflow/es/docs/reference/rest/v2/WebhookResponse to view the correct format to respond to dialogflow
        JSONObject fulfillment = new JSONObject();

        switch (intent) {
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

                userInfo.put(user, new JSONObject("{\"BA\":0,\"Exposure\":0, \"BizActs\":\"\", \"Exp\":\"\", \"turnover\":0}"));
                // Reset the list of excluded business activities so that it is refreshed whenever the conversation restarts
                excludedBizActivities = new ArrayList<>();
                StringBuilder BizActsString = new StringBuilder("Please enter your business activity and its corresponding percentage (eg. Retail: 20%/ B0001: 30%):\n\n");


                HttpEntity<String> httpEntity = new HttpEntity<>("", headers);

                // Retrieve the risk details from the DCM risk details API
                HttpStatus code;
                ResponseEntity<String> response;
                try {
                    System.out.println("***********");
                    response = restTemplate.exchange("https://product-service-dev.discovermarket.com/v2/riskdetailinfos/619c9d2e4b0253465a797fd1/620db4ca930b8e4c589482b5",
                            HttpMethod.GET, httpEntity, String.class);
                    code = response.getStatusCode();
                    System.out.println(code);

                } catch (HttpClientErrorException e)  {
                    System.out.println("--Setting new token--");
                    String token = tokenManagement.tokenization();
                    headers.setBearerAuth(token);
                    response = restTemplate.exchange("https://product-service-dev.discovermarket.com/v2/riskdetailinfos/619c9d2e4b0253465a797fd1/620db4ca930b8e4c589482b5",
                            HttpMethod.GET, httpEntity, String.class);
                    code = response.getStatusCode();
                    System.out.println(code);
                } catch (HttpServerErrorException e){
                    System.out.println("Not working");
                    break;
                }
                JSONObject riskDetails = new JSONObject(response.getBody());
                JSONArray activitiesList = riskDetails.getJSONObject("data").getJSONObject("customerCategory").getJSONArray("objectTypes").getJSONObject(0).getJSONArray("riskDetailDataGroups").getJSONObject(0).getJSONArray("dataDetailAttributes").getJSONObject(0).getJSONArray("options");
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
                if (turnover <= 0) {
                    fulfillment.put("outputContexts", turnoverContext);
                    fulfillment.put("fulfillmentText", "Annual turnover must be a positive non-zero number, please re-enter");
                }
                break;

            case "Email":

                JSONObject objectTypes = objectTypesGenerator.generateObjectTypes(userInfo.getJSONObject(user), BizActivities);

                System.out.println(objectTypes);
                break;
        }
        return fulfillment;
    }
}
