package com.example.chatbot.Management;

import com.example.chatbot.GeneralHeaders;
import org.springframework.http.HttpHeaders;

public class AuthHeadersManagement extends GeneralHeaders {

    public HttpHeaders AuthHeaders(String length) {
        GeneralHeaders cusHeaders = new GeneralHeaders(length);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Connection",cusHeaders.connection);
        headers.add("Content-Length",cusHeaders.contentLength);
        headers.add("sec-ch-ua", cusHeaders.secChUa);
        headers.add("Accept", cusHeaders.accept);
        headers.add("Content-Type", cusHeaders.contentType);
        headers.add("sec-ch-ua-mobile", cusHeaders.secChUaMobile);
        headers.add("User-Agent", cusHeaders.userAgent);
        headers.add("sec-ch-ua-platform", cusHeaders.secChUaPlatform);
        headers.add("Origin", cusHeaders.origin);
        headers.add("Sec-Fetch-Site", cusHeaders.secFetchSite);
        headers.add("Sec-Fetch-Mode", cusHeaders.secFetchMode);
        headers.add("Sec-Fetch-Dest", cusHeaders.secFetchDest);
        headers.add("Referer", cusHeaders.referer);
        headers.add("Accept-Language", cusHeaders.acceptLanguage);
        headers.add("Host", cusHeaders.host);
        headers.add("Accept-Encoding", cusHeaders.acceptEncoding);
        headers.add("Accept-Language", cusHeaders.acceptLanguage);
        return headers;
    }
    public HttpHeaders AuthHeadersNoLength() {
        GeneralHeaders cusHeaders = new GeneralHeaders();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Connection",cusHeaders.connection);
//        headers.add("Content-Length",cusHeaders.contentLength);
        headers.add("sec-ch-ua", cusHeaders.secChUa);
        headers.add("Accept", cusHeaders.accept);
        headers.add("Content-Type", cusHeaders.contentType);
        headers.add("sec-ch-ua-mobile", cusHeaders.secChUaMobile);
        headers.add("User-Agent", cusHeaders.userAgent);
        headers.add("sec-ch-ua-platform", cusHeaders.secChUaPlatform);
        headers.add("Origin", cusHeaders.origin);
        headers.add("Sec-Fetch-Site", cusHeaders.secFetchSite);
        headers.add("Sec-Fetch-Mode", cusHeaders.secFetchMode);
        headers.add("Sec-Fetch-Dest", cusHeaders.secFetchDest);
        headers.add("Referer", cusHeaders.referer);
        headers.add("Accept-Language", cusHeaders.acceptLanguage);
        headers.add("Host", cusHeaders.host);
        headers.add("Accept-Encoding", cusHeaders.acceptEncoding);
        headers.add("Accept-Language", cusHeaders.acceptLanguage);
        return headers;
    }
    public HttpHeaders ReCalHeaders() {
        GeneralHeaders cusHeaders = new GeneralHeaders();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Connection",cusHeaders.connection);
//        headers.add("Content-Length",cusHeaders.contentLength);
        headers.add("sec-ch-ua", cusHeaders.secChUa);
        headers.add("Accept", cusHeaders.accept);
//        headers.add("Content-Type", cusHeaders.contentType);
        headers.add("sec-ch-ua-mobile", cusHeaders.secChUaMobile);
        headers.add("User-Agent", cusHeaders.userAgent);
        headers.add("sec-ch-ua-platform", cusHeaders.secChUaPlatform);
        headers.add("Origin", cusHeaders.origin);
        headers.add("Sec-Fetch-Site", cusHeaders.secFetchSite);
        headers.add("Sec-Fetch-Mode", cusHeaders.secFetchMode);
        headers.add("Sec-Fetch-Dest", cusHeaders.secFetchDest);
        headers.add("Referer", cusHeaders.referer);
        headers.add("Host", cusHeaders.host);
        headers.add("Accept-Encoding", cusHeaders.acceptEncoding);
        headers.add("Accept-Language", "en-US,en;q=0.9,vi;q=0.8");
        return headers;
    }
}

