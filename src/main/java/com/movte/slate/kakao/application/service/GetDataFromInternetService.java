package com.movte.slate.kakao.application.service;

import com.movte.slate.global.exception.ServerErrorException;
import com.movte.slate.global.exception.ServerErrorExceptionCode;
import com.movte.slate.kakao.application.usecase.GetDataFromInternetUseCase;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Service
public class GetDataFromInternetService implements GetDataFromInternetUseCase {
    @Override
    public String get(String url) {
        try {
            URL urlObj;
            HttpURLConnection conn;
            urlObj = new URL(url);
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content_Type", "application/x-ww-form-urlencoded;charset=utf-8");
            if (conn.getResponseCode() != 200) {
                throw new IOException();
            }
            return getBody(conn);
        } catch (IOException e) {
            throw new ServerErrorException(ServerErrorExceptionCode.NETWORK_ERROR);
        }
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
