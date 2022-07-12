package com.example.chatbot;

public class GeneralHeaders {
    public String connection = "keep-alive";
    public String contentLength = "0";
    public String secChUa = ".Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"103\", \"Chromium\";v=\"103";
    public String accept = "application/json, text/plain, */*";
    public String contentType = "application/json";
    public String secChUaMobile = "?0";
    public String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36";
    public String secChUaPlatform = "\"Windows\"";
    public String origin = "https://dcmp-dev.discovermarket.com";
    public String secFetchSite = "same-site";
    public String secFetchMode  = "cors";
    public String secFetchDest = "empty";
    public String referer = "https://dcmp-dev.discovermarket.com/";
    public String acceptLanguage = "en-US,en;q=0.9,vi;q=0.8";
    public String host = "dev.apis.discovermarket.com";
    public String acceptEncoding = "gzip, deflate, br";
    public GeneralHeaders(String val){
        this.contentLength = val;
    }
    public GeneralHeaders(){

    }
}

