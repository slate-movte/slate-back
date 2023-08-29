package com.movte.slate.global.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movte.slate.global.exception.HttpBusinessException;
import com.movte.slate.global.exception.dto.ExceptionMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class ResponseFactory {

    public static <T> ResponseEntity<ResponseFormat<T>> success(T data) {
        return new ResponseEntity<>(ResponseFormat.<T>builder()
                .message("성공했습니다.")
                .data(data).build(), HttpStatus.OK);
    }

    public static void fail(HttpServletResponse response, HttpBusinessException e) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(e.getStatusCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ExceptionMessage exceptionMessage = new ExceptionMessage(e.getMessage());
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(exceptionMessage));
    }

    public static ResponseEntity<ResponseFormat<String>> fail(HttpBusinessException e) {
        return new ResponseEntity<>(ResponseFormat.<String>builder()
                .message(e.getMessage())
                .data("실패했으므로 데이터는 존재하지 않습니다.")
                .build(), HttpStatus.valueOf(e.getStatusCode()));
    }
}
