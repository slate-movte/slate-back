package com.movte.slate.domain.user.application.service;

import com.movte.slate.global.exception.ServerErrorException;
import com.movte.slate.global.exception.ServerErrorExceptionCode;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class NetworkCommunicatorImpl implements NetworkCommunicator {

    public String getResourceFromKakao(String url, String queryString) {
        URL urlObj;
        HttpURLConnection conn;
        try {
            urlObj = new URL(url);
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content_Type", "application/x-ww-form-urlencoded;charset=utf-8");
            conn.setDoOutput(true);
            try (BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(conn.getOutputStream())))) {
                bw.write(queryString);
                bw.flush();
            }
            if (conn.getResponseCode() != 200) {
                throw new ServerErrorException(ServerErrorExceptionCode.CANNOT_GET_ID_TOKEN);
            }
            return getBody(conn);
        } catch (IOException e) {
            throw new ServerErrorException(ServerErrorExceptionCode.NETWORK_ERROR);
        }
    }

    @Override
    public String getResponseUsingGet(String url, String queryString) {
        URL urlObj;
        HttpURLConnection conn;
        try {
            urlObj = new URL(url);
            conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content_Type", "application/x-ww-form-urlencoded;charset=utf-8");
//            conn.setDoOutput(true);
//            try (BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(conn.getOutputStream())))) {
//                bw.write(queryString);
//                bw.flush();
//            }
            if (conn.getResponseCode() != 200) {
                throw new ServerErrorException(ServerErrorExceptionCode.CANNOT_GET_ID_TOKEN);
            }
            return getBody(conn);
        } catch (IOException e) {
            throw new ServerErrorException(ServerErrorExceptionCode.NETWORK_ERROR);
        }
    }

    private String getBody(HttpURLConnection conn) {
        String responseData = "";
        // HTTP 요청 후 응답 받은 데이터를 버퍼에 쌓는다
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            while ((responseData = br.readLine()) != null) {
                sb.append(responseData);
            }
            responseData = sb.toString();
        } catch (IOException e) {
            throw new ServerErrorException(ServerErrorExceptionCode.NETWORK_ERROR);
        }
        return responseData;
    }
}
