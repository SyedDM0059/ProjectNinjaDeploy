package com.example.chatbot;

import com.google.api.gax.rpc.ApiException;
import com.google.cloud.dialogflow.v2.*;

import java.io.IOException;

public class UnusedCode {

    //LOGIC FOR MAKING THE FULL QUOTE STRING WITH ALL THE DETAILS

//    httpEntity = new HttpEntity<>("", reCalcHeaders);
//                try {
//                    reCalcHeaders.setBearerAuth(cusPropFullTok);
//                    resp = restTemplate.exchange("https://dev.apis.discovermarket.com/product/v2/products/620db4ca930b8e4c589482b5",
//                            HttpMethod.GET, httpEntity, String.class);
//                } catch (HttpClientErrorException e)  {
//                    System.out.println("--Setting new token for base bundle api--");
//                    cusPropFullTok = tokenManagement.CusPropFullTokenization();
//                    reCalcHeaders.setBearerAuth(cusPropFullTok);
//                    try {
//                        resp = restTemplate.exchange("https://dev.apis.discovermarket.com/product/v2/products/620db4ca930b8e4c589482b5",
//                                HttpMethod.GET, httpEntity, String.class);
//                    } catch (HttpServerErrorException E){
//                        System.out.println("DCM SERVER ERROR");
//                        fulfillment.put("fulfillmentText", "We are experiencing technical difficulties, please try again later");
//                        break;
//                    }
//                    code = resp.getStatusCode();
//                    System.out.println(code);
//                } catch (HttpServerErrorException e){
//                    System.out.println("DCM SERVER ERROR");
//                    fulfillment.put("fulfillmentText", "We are experiencing technical difficulties, please try again later");
//                    break;
//                }
//
//                JSONArray baseBundle = new JSONObject(resp.getBody()).getJSONArray("data");
//
//                StringBuilder test = new StringBuilder();
//                JSONObject test2 = new JSONObject();
//
//                for ( int i = 0; i < baseBundle.length(); i++) {
//                    JSONObject temp = baseBundle.getJSONObject(i);
//                    if (temp.getString("productTypeCode").equals("BU") || temp.getString("productTypeCode").equals("PL")) {
//                        break;
//                    }
//                    test2.put(temp.getString("proposalProductGroupCode"), new JSONArray());
//                    test2.getJSONArray(temp.getString("proposalProductGroupCode")).put(temp.getString("productName"));
//                }
//
//                for (int i = 0; i < 4; i++) {
//                   JSONArray temp = userInfo.getJSONObject(user).getJSONObject("proposal").getJSONArray("quotations").getJSONObject(i).getJSONArray("quotationDetails")
//                            .getJSONObject(0).getJSONArray("productSelectedList");
//                    System.out.println(i);
//                    for ( int j = 0; j < temp.length(); j++) {
//                        JSONObject temp2 = temp.getJSONObject(j);
//                        if (temp2.getString("productTypeCode").equals("BU") || temp2.getString("productTypeCode").equals("PL")) {
//                            break;
//                        }
//                        test2.getJSONArray(temp2.getString("proposalProductGroupCode")).put(temp2.getString("productDescription"));
//                    }
//                }
//
//                String prefix = "PPG00000";
//                for (int i = 1; i < baseBundle.length()-1; i++) {
//                    test.append(test2.getJSONArray(prefix + String.format("%02d", i)).getString(0));
//
//                }
//
//                System.out.println("{\"text\":{\"text\":[\"" + test + "\",\"" + test + "\",\"" + test + "\",\"" + test + "\"]}}");
//                fulfillment.put("fulfillmentText", fulfillmentMessages.text.text[0]);
//                fulfillment.put("fulfillmentMessages", "[{\"text\":{\"text\":[\"" + test + "\"]}}]");
//                fulfillment.getJSONArray("fulfillmentMessages").put(new JSONObject("{\"text\":{\"text\":[\"" + test + "\"]}}"));
//                fulfillment.getJSONArray("fulfillmentMessages").put("{\"text\":{\"text\":[" + test + "]}}");
//                fulfillment.getJSONArray("fulfillmentMessages").put("{\"text\":{\"text\":[" + test + "]}}");
//                fulfillment.getJSONArray("fulfillmentMessages").put("{\"text\":{\"text\":[" + test + "]}}");






    //	@Override
//	public void run(String... args) throws Exception {
//		//Question qn1 = new Question("1", "why?", "23/6/2022");
////		Question qn2 = new Question("2", "why sia?", "23/6/2022");
////		Question qn3 = new Question("3", "why must I?", "23/6/2022");
////		Question qn4 = new Question("4", "why why?", "23/6/2022");
////
////		questionRepository.save(qn1);
////		questionRepository.save(qn2);
////		questionRepository.save(qn3);
////		questionRepository.save(qn4);
//
////		System.out.println("***********");
////		System.out.println(childPlans.childPlans.getJSONArray("data").getJSONObject(0).getString("productDescription"));
////		List<Question> questions = questionRepository.findAll();
////		for(Question q : questions) {
////			System.out.println(q.toString());
////		}
//	}
//    quoteString = new StringBuilder();
//
//                quoteString.append("Thank you, here are your quotes:\n\n");
//
//                quoteString.append("Standard").append(" --- ").append(String.format("$%.2f", proposal.getJSONArray("quotations").getJSONObject(0).getFloat("totalPremium"))).append("\n");
//                quoteString.append("Silver").append(" --- ").append(String.format("$%.2f", proposal.getJSONArray("quotations").getJSONObject(1).getFloat("totalPremium"))).append("\n");
//                quoteString.append("Gold").append(" --- ").append(String.format("$%.2f", proposal.getJSONArray("quotations").getJSONObject(2).getFloat("totalPremium"))).append("\n");
//                quoteString.append("Platinum").append(" --- ").append(String.format("$%.2f", proposal.getJSONArray("quotations").getJSONObject(3).getFloat("totalPremium"))).append("\n");
//
//                quoteString.append("\nWould you like to receive the full details of the quote in an email sent to ").append(userInfo.getJSONObject(user).getString("email")).append("?");

//        JSONObject BAtemp;
//        JSONObject exptemp;
//        JSONArray context = new JSONArray();
//        JSONObject temp = new JSONObject();
//        JSONObject fulfillment = new JSONObject();
//
//        String sessionID = json.getString("session"); // To hold the unique sessionID from dialogflow
//        String intent = json.getJSONObject("queryResult").getJSONObject("intent").getString("displayName"); // To extract the intent of the user input from the dialogflow request
//
//        if (intent.equals("BA")) {
//
//            BAtemp = json.getJSONObject("queryResult").getJSONObject("parameters");
//            totalBA += Integer.parseInt(BAtemp.getString("percentage").substring(0, BAtemp.getString("percentage").lastIndexOf("%")));
////            BA.put(BAtemp.getString("BA"), BAtemp.getString("percentage"));
//            BAstring += BAtemp.getString("BA") + ": " + BAtemp.getString("percentage") + "\n";
//            if (totalBA < 100){
//                temp.put("name", sessionID+"/contexts/quote");
//                temp.put("lifespanCount", 2);
//                context.put(temp);
//                fulfillment.put("outputContexts", context);
//                fulfillment.put("fulfillmentText", "What are your other business activities? (total business activity must add up to 100%)\n\n"+BAstring);
//            } else if ( totalBA > 100) {
//                temp.put("name", sessionID+"/contexts/quote");
//                temp.put("lifespanCount", 2);
//                context.put(temp);
//                fulfillment.put("outputContexts", context);
//                fulfillment.put("fulfillmentText", "Total business activity cannot be more than 100%, please state your business activities again");
//                totalBA = 0;
//                BAstring = new String();
////                BA = new JSONObject();
//            } else {
//                temp.put("name", sessionID+"/contexts/turnover");
//                temp.put("lifespanCount", 2);
//                context.put(temp);
//                fulfillment.put("outputContexts", context);
//                totalBA = 0;
//                BAstring = new String();
//                fulfillment.put("fulfillmentText", "What is your company's annual turnover?");
//            }
//        } else if (intent.equals("Exposure")) {
//
//            exptemp = json.getJSONObject("queryResult").getJSONObject("parameters");
//            totalExp += Integer.parseInt(exptemp.getString("percentage").substring(0, exptemp.getString("percentage").lastIndexOf("%")));
//            expstring += exptemp.getString("Exposure") + ": " + exptemp.getString("percentage") + "\n";
//            if (totalExp < 100){
//                temp.put("name", sessionID+"/contexts/exposure");
//                temp.put("lifespanCount", 2);
//                context.put(temp);
//                fulfillment.put("outputContexts", context);
//                fulfillment.put("fulfillmentText", "What is your other market exposure? (total exposure must add up to 100%)\n\n"+expstring);
//            } else if ( totalExp > 100) {
//                temp.put("name", sessionID+"/contexts/exposure");
//                temp.put("lifespanCount", 2);
//                context.put(temp);
//                fulfillment.put("outputContexts", context);
//                fulfillment.put("fulfillmentText", "Total exposure cannot be more than 100%, please state your exposure again");
//                totalExp = 0;
//                expstring = new String();
//            } else {
//                temp.put("name", sessionID+"/contexts/name");
//                temp.put("lifespanCount", 99);
//                context.put(temp);
//                fulfillment.put("outputContexts", context);
//                totalExp = 0;
//                fulfillment.put("fulfillmentText", "Great, thanks! Now we just need to know a bit more about you. What is your name?");
//                expstring = new String();
//            }
//        }
//        System.out.println(fulfillment.toString());
//        return fulfillment.toString();


    //        Body messageBody = new Body.Builder(fulfillment).build();
//        Message message = new Message.Builder().body(messageBody).build();
//        return new MessagingResponse.Builder().message(message).build().toXml();

    //        PhoneNumber userNumber = new PhoneNumber("whatsapp:"+jsonPayload.getString("From"));
//        PhoneNumber DCMNumber = new PhoneNumber("whatsapp:+17406392618");


    //    @PostMapping("/twilio")
//    @ResponseBody
//    public String twilioWebhook(@RequestParam MultiValueMap<String,String> payload) throws IOException {
//
//        String text = payload.get("Body").get(0);
//
//        // Using user number as the session id
//        QueryResult df = dialogflowService.detectIntentTexts("focused-house-354313", text, payload.get("From").toString(), "en-US");
//
//        return df.getFulfillmentText();
//    }

//    public class dialogflowService {
//        // DialogFlow API Detect Intent sample with text inputs.
//        public static QueryResult detectIntentTexts(
//                String projectId, String texts, String sessionId, String languageCode)
//                throws IOException, ApiException {
////        JSONObject queryResults = new JSONObject();
//            // Instantiates a client
//            QueryResult queryResult;
//            try (SessionsClient sessionsClient = SessionsClient.create()) {
//                // Set the session name using the sessionId (UUID) and projectID (my-project-id)
//                SessionName session = SessionName.of(projectId, sessionId);
////            System.out.println("Session Path: " + session.toString());
//
//                // Set the text (hello) and language code (en-US) for the query
//                TextInput.Builder textInput =
//                        TextInput.newBuilder().setText(texts).setLanguageCode(languageCode);
//
//                // Build the query with the TextInput
//                QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
//
//                // Performs the detect intent request
//                DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
//
//                // Display the query result
//                queryResult = response.getQueryResult();
//
//            }
//            return queryResult;
//        }
//    }
    //package com.example.chatbot;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class dialogflowServiceImpl implements dialogflowInterface {
//    @Autowired
//    private dialogflowService dialogflowService = new dialogflowService();
//    @Override
//    public void detectIntentTexts(final String projectId, final String texts, final String sessionId, final String languageCode) {
//
//    }
//
//}

}



