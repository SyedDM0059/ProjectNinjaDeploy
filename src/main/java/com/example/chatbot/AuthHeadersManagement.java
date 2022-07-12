package com.example.chatbot;

import org.springframework.http.HttpHeaders;

public class AuthHeadersManagement extends GeneralHeaders{

    public static HttpHeaders AuthHeaders(String length) {
        GeneralHeaders cusHeaders = new GeneralHeaders(length);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Connection",cusHeaders.value);
        headers.add("Content-Length",cusHeaders.value1);
        headers.add("sec-ch-ua", cusHeaders.value2);
        headers.add("Accept", cusHeaders.value3);
        headers.add("Content-Type", cusHeaders.value4);
        headers.add("sec-ch-ua-mobile", cusHeaders.value5);
        headers.add("User-Agent", cusHeaders.value6);
        headers.add("sec-ch-ua-platform", cusHeaders.value7);
        headers.add("Origin", cusHeaders.value8);
        headers.add("Sec-Fetch-Site", cusHeaders.value9);
        headers.add("Sec-Fetch-Mode", cusHeaders.value10);
        headers.add("Sec-Fetch-Dest", cusHeaders.value11);
        headers.add("Referer", cusHeaders.value12);
        headers.add("Accept-Language", cusHeaders.value13);
        headers.add("Host", cusHeaders.value14);
        headers.add("Accept-Encoding", cusHeaders.value15);
        headers.add("Accept-Language", cusHeaders.value16);
        return headers;
    }
    public static HttpHeaders AuthHeadersNoLength() {
        GeneralHeaders cusHeaders = new GeneralHeaders();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Connection",cusHeaders.value);
//        headers.add("Content-Length",cusHeaders.value1);
        headers.add("sec-ch-ua", cusHeaders.value2);
        headers.add("Accept", cusHeaders.value3);
        headers.add("Content-Type", cusHeaders.value4);
        headers.add("sec-ch-ua-mobile", cusHeaders.value5);
        headers.add("User-Agent", cusHeaders.value6);
        headers.add("sec-ch-ua-platform", cusHeaders.value7);
        headers.add("Origin", cusHeaders.value8);
        headers.add("Sec-Fetch-Site", cusHeaders.value9);
        headers.add("Sec-Fetch-Mode", cusHeaders.value10);
        headers.add("Sec-Fetch-Dest", cusHeaders.value11);
        headers.add("Referer", cusHeaders.value12);
        headers.add("Accept-Language", cusHeaders.value13);
        headers.add("Host", cusHeaders.value14);
        headers.add("Accept-Encoding", cusHeaders.value15);
        headers.add("Accept-Language", cusHeaders.value16);
        return headers;
    }

}

