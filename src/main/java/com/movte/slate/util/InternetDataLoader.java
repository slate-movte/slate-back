package com.movte.slate.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class InternetDataLoader {
    public String getResponseUsingGetHttpMethod(String url) throws IOException {
        URL urlObj;
        HttpURLConnection conn;
        urlObj = new URL(url);
        conn = (HttpURLConnection) urlObj.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content_Type", "application/x-ww-form-urlencoded;charset=utf-8");
//        if (conn.getResponseCode() != 200) {
//            throw new IOException();
//        }
        return getBody(conn);
    }

    private String getBody(HttpURLConnection conn) throws IOException {
        String responseData = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            while ((responseData = br.readLine()) != null) {
                sb.append(responseData);
            }
            responseData = sb.toString();
        }
        return responseData;
    }
}
