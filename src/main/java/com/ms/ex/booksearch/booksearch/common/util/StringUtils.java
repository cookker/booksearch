package com.ms.ex.booksearch.booksearch.common.util;

/**
 * @see org.springframework.util.StringUtils
 *
 * 기존 StringUtils의 빈값체크가 부실해서 기능 추가함.
 */
public final class StringUtils {

    private StringUtils(){
    }

    public static boolean isEmpty(String value){
        return value == null || "".equals(value) || "".equals(value.trim());
    }

}
