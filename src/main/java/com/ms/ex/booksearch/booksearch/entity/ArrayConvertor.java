package com.ms.ex.booksearch.booksearch.entity;

import com.ms.ex.booksearch.booksearch.common.util.JsonUtils;

import javax.persistence.AttributeConverter;

public class ArrayConvertor implements AttributeConverter<String [], String> {

    @Override
    public String convertToDatabaseColumn(String [] attribute) {
        return JsonUtils.toJson(attribute);
    }

    @Override
    public String [] convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return new String []{};
        }
        return JsonUtils.fromJson(dbData, String [].class);
    }
}