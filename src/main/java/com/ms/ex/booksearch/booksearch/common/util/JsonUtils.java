package com.ms.ex.booksearch.booksearch.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public final class JsonUtils {

    private static final ObjectMapper objectMapper;

    static{
        objectMapper = new ObjectMapper();

        // int나 double 타입에서 값이 null일 경우 0으로 바꿔준다.
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        // enum을 String으로 변환한다. enum.toString()호출
        objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        // 모르는 properties가 있을 때 오류를 반환하지 않는다.
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //직렬화 어노테이션이 없어도 직렬화 시킨다.
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        //필드명이 더블쿼테이션이 아니더라도(싱글이더라도) 인정함.
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        // ENUM 값이 존재하지 않으면 null로 설정한다. Enum 항목이 추가되어도 무시하고 넘어가게 할 때 필요하다.
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
    }

    private JsonUtils(){
    }

    /**
     * 스트링 JSON데이터를 객체로 반환합니다
     * @param json
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clazz){
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 객체 데이터를 스트링으로 변환홥니다.
     *
     * @param object
     * @return
     */
    public static String toJson(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}