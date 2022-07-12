package com.example.chatbot;
import org.json.JSONArray;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
public class TokenManagement {
    RestTemplate restTemplate = new RestTemplate();
    public String tokenization() {

        String value = "keep-alive";
        String value2 = "Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"97\", \"Chromium\";v=\"97";
        String value3 = "application/json, text/plain, */*";
        String value4 = "application/json";
        String value5 = "?0";
        String value6 = "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36";
        String value7 = "sec-ch-ua-platform: \"Windows\"";
        String value8 = "Origin: https://dcmp-dev.discovermarket.com";
        String value9 = "Sec-Fetch-Site: same-site";
        String value10 = "Sec-Fetch-Mode: cors";
        String value11 = "Sec-Fetch-Dest: empty";
        String value12 = "Referer: https://dcmp-dev.discovermarket.com/";
        String value13 = "Accept-Language: en-US,en;q=0.9,vi;q=0.8";

        HttpHeaders headers = new HttpHeaders();

        headers.add("Connection",value);
        headers.add("sec-ch-ua", value2);
        headers.add("Accept", value3);
        headers.add("Content-Type", value4);
        headers.add("sec-ch-ua-mobile", value5);
        headers.add("User-Agent", value6);
        headers.add("sec-ch-ua-platform", value7);
        headers.add("Origin", value8);
        headers.add("Sec-Fetch-Site", value9);
        headers.add("Sec-Fetch-Mode", value10);
        headers.add("Sec-Fetch-Dest", value11);
        headers.add("Referer", value12);
        headers.add("Accept-Language", value13);
        HttpEntity<String> httpEntityFirst = new HttpEntity<>("{\"username\":\"tobedeleted@discovermarket.com\",\"password\":\"Bud542381\",\"tenantCode\":\"TE0000001\"}", headers);
        ResponseEntity<String> tokenCheck = restTemplate.exchange("https://dev.apis.discovermarket.com/authentication/v2/auth/login",
                HttpMethod.POST, httpEntityFirst, String.class);
        JSONObject tokenize = new JSONObject(tokenCheck.getBody());
        return tokenize.getJSONObject("data").getString("token");
    }
    public String CusPropFullTokenization() {
        String tok = "eyJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJodHRwczovL2RjbXAtZGV2LmRpc2NvdmVybWFya2V0LmNvbSIsInN1YiI6IjAwMDAwMDAwYWIxMDgzMjMwYTY1MzlkNCIsImNvZGUiOiJzdHJpbmciLCJwZXJtaXNzaW9ucyI6WyJkZXBlbmRhbnRzLmNyZWF0ZS5hbnkiLCJkZXBlbmRhbnRzLnJlYWQuYW55IiwiZGVwZW5kYW50cy51cGRhdGUuYW55IiwiZGVwZW5kYW50cy5kZWxldGUuYW55IiwicHJvcG9zYWxzLmNyZWF0ZS50ZW5hbnQiLCJ0ZW5hbnRzLnJlYWQub3duIiwicHJvcG9zYWxzLnVwZGF0ZS50ZW5hbnQiLCJwcm9wb3NhbHMucmVhZC50ZW5hbnQiLCJwcm9wb3NhbHMuZGVsZXRlLnRlbmFudCIsImN1c3RvbWVycy5jcmVhdGUudGVuYW50IiwiY3VzdG9tZXJzLnJlYWQudGVuYW50IiwiY3VzdG9tZXJzLnVwZGF0ZS50ZW5hbnQiLCJjdXN0b21lcnMuZGVsZXRlLnRlbmFudCIsImN1c3RvbWVycy5yZWFkLm93biIsInNjYW4uY3JlYXRlLnRlbmFudCIsInNjYW4ucmVhZC50ZW5hbnQiLCJ1c2Vycy5yZWFkLm93biIsInVzZXJzLnVwZGF0ZS5vd24iLCJ1c2Vycy5jcmVhdGUuYW55IiwiY3VzdG9tZXJzLnJlYWQudGVuYW50IiwiY3VzdG9tZXJzLmNyZWF0ZS50ZW5hbnQiLCJjdXN0b21lcnMudXBkYXRlLnRlbmFudCIsImN1c3RvbWVycy5kZWxldGUudGVuYW50IiwicHJvcG9zYWxzLmNyZWF0ZS50ZW5hbnQiLCJwcm9wb3NhbHMudXBkYXRlLnRlbmFudCIsInByb3Bvc2Fscy5yZWFkLnRlbmFudCIsInByb3Bvc2Fscy5kZWxldGUudGVuYW50IiwidXNlcnMucmVhZC5vd24iLCJ1c2Vycy51cGRhdGUub3duIiwidGVuYW50cy5yZWFkLm93biIsInRlbmFudHMudXBkYXRlLm93biIsInVzZXJzLmNyZWF0ZS5hbnkiLCJjbGFpbXMucmVhZC50ZW5hbnQiLCJwb2xpY2llcy5yZWFkLnRlbmFudCIsImN1c3RvbWVycy5yZWFkLnRlbmFudCIsImN1c3RvbWVycy5jcmVhdGUudGVuYW50IiwiY3VzdG9tZXJzLnVwZGF0ZS50ZW5hbnQiLCJjdXN0b21lcnMuZGVsZXRlLnRlbmFudCIsInByb3Bvc2Fscy5jcmVhdGUudGVuYW50IiwicHJvcG9zYWxzLnVwZGF0ZS50ZW5hbnQiLCJwcm9wb3NhbHMucmVhZC50ZW5hbnQiLCJwcm9wb3NhbHMuZGVsZXRlLnRlbmFudCIsInVzZXJzLnJlYWQub3duIiwidXNlcnMudXBkYXRlLm93biIsInRlbmFudHMucmVhZC5vd24iLCJ0ZW5hbnRzLnVwZGF0ZS5vd24iLCJ1c2Vycy5jcmVhdGUuYW55IiwiY2xhaW1zLnJlYWQudGVuYW50IiwicG9saWNpZXMucmVhZC50ZW5hbnQiLCJkZXBlbmRhbnRzLmNyZWF0ZS5hbnkiLCJkZXBlbmRhbnRzLnJlYWQuYW55IiwiZGVwZW5kYW50cy51cGRhdGUuYW55IiwiZGVwZW5kYW50cy5kZWxldGUuYW55IiwicHJvcG9zYWxzLmNyZWF0ZS50ZW5hbnQiLCJ0ZW5hbnRzLnJlYWQub3duIiwicHJvcG9zYWxzLnVwZGF0ZS50ZW5hbnQiLCJwcm9wb3NhbHMucmVhZC50ZW5hbnQiLCJwcm9wb3NhbHMuZGVsZXRlLnRlbmFudCIsImN1c3RvbWVycy5jcmVhdGUudGVuYW50IiwiY3VzdG9tZXJzLnJlYWQudGVuYW50IiwiY3VzdG9tZXJzLnVwZGF0ZS50ZW5hbnQiLCJjdXN0b21lcnMuZGVsZXRlLnRlbmFudCIsImN1c3RvbWVycy5yZWFkLm93biIsInNjYW4uY3JlYXRlLnRlbmFudCIsInNjYW4ucmVhZC50ZW5hbnQiLCJ1c2Vycy5yZWFkLm93biIsInVzZXJzLnVwZGF0ZS5vd24iLCJ1c2Vycy5jcmVhdGUuYW55Il0sInJvbGVzIjpbIlJPMDAwMDAyNiIsIlJPMDAwMDAxOCIsIlJPMDAwMDAxOCIsIlJPMDAwMDAyNiJdLCJ0ZW5hbnRJZCI6IjYxOWM5ZDJlNGIwMjUzNDY1YTc5N2ZkMSIsImlzcyI6Imh0dHBzOi8vZGNtcC1kZXYuZGlzY292ZXJtYXJrZXQuY29tIiwidGVuYW50Q29kZSI6IlRFMDAwMDAwNSIsImV4cCI6MTY1NzI3NTU2MiwiaWF0IjoxNjU3MjcxMDYyLCJlbWFpbCI6IlRFMDAwMDAwNUBkaXNjb3Zlcm1hcmtldC5jb20ifQ.XSXkBmk-Cl_8GrhvABjUSUHuZhrfKh2nGVmAW5wEKi6I29aZH3JU1A46tS7Ib4TC7NHZSnG5zJoByXRUgkUn_A";
        return tok;
    }

}
