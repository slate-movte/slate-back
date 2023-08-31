package com.movte.slate.domain.user.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.movte.slate.global.exception.ServerErrorException;
import com.movte.slate.global.exception.ServerErrorExceptionCode;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public static String getObject(String json, String key) {
        JsonElement jsonElement = com.google.gson.JsonParser.parseString(json);
        return jsonElement.getAsJsonObject().get(key).toString();
    }

    public static <T> List<T> parse(String array, Class<T> klass) {
        if (StringUtils.isNotBlank(array)) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                List<T> list = objectMapper.readerForListOf(klass).readValue(array);
                return list;
            } catch (JsonProcessingException e) {
                throw new ServerErrorException(ServerErrorExceptionCode.JSON_PARSE_ERROR);
            }
        }
        return new ArrayList<>();
    }
}
