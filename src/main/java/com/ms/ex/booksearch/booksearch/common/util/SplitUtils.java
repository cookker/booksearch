package com.ms.ex.booksearch.booksearch.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public final class SplitUtils {
    private SplitUtils(){}

    public static String [] split(String val){
        return split(val, ",");
    }

    public static String [] split(String val, String delimiter){
        if(StringUtils.isEmpty(val)){
            return new String[]{""};
        }

        String[] splitedValues = val.split(delimiter);
        List<String> strings = Arrays.stream(splitedValues)
                .filter(v -> !StringUtils.isEmpty(v.trim()))
                .collect(Collectors.toList());
        log.debug("{}값을 쪼갠결과:{}", val, strings);
        return strings.toArray(new String[strings.size()]);
    }
}
