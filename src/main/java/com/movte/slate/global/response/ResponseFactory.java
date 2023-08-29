package com.movte.slate.global.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movte.slate.global.exception.HttpBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class ResponseFactory {

    public static <T> ResponseEntity<SuccessResponseFormat<T>> success(T data) {
        SuccessResponseFormat<T> successResponse = new SuccessResponseFormat<>("요청에 성공하였습니다.", data);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    public static void fail(HttpServletResponse response, HttpBusinessException e) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(e.getStatusCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        FailResponseFormat failResponse = new FailResponseFormat(e.getMessage(), e.getCode());
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(failResponse));
    }

    public static ResponseEntity<FailResponseFormat> fail(HttpBusinessException e) {
        FailResponseFormat failResponse = new FailResponseFormat(e.getMessage(), e.getCode());
        return new ResponseEntity<>(failResponse, HttpStatus.valueOf(e.getStatusCode()));
    }
}
