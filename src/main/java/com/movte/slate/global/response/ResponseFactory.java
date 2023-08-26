package com.movte.slate.global.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movte.slate.global.exception.HttpBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ResponseFactory {
    private final ObjectMapper objectMapper;

    public <T> ResponseEntity<ResponseFormat<T>> success(T data) {
        return new ResponseEntity<>(ResponseFormat.<T>builder()
                .message("성공했습니다.")
                .data(data).build(), HttpStatus.OK);
    }

    public void fail(HttpServletResponse response, HttpBusinessException e) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(e.getStatusCode());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Map<String, String> body = new HashMap<>();
        body.put("message", e.getMessage());
        response.getWriter().write(objectMapper.writeValueAsString(body));
    }

    public ResponseEntity<ResponseFormat<String>> fail(HttpBusinessException e) {
        return new ResponseEntity<>(ResponseFormat.<String>builder()
                .message(e.getMessage())
                .data("실패했으므로 데이터는 존재하지 않습니다.")
                .build(), HttpStatus.valueOf(e.getStatusCode()));
    }
}
