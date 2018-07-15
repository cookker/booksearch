package com.ms.ex.booksearch.booksearch.common;

import com.ms.ex.booksearch.booksearch.common.error.ApiException;
import com.ms.ex.booksearch.booksearch.common.error.Err;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
public class CommonResponse<T> {
    private final long timestamp = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    private final String code;
    private final String message;
    private final T data;

    private static final String SUCCESS_CODE = "0000";
    private static final String SUCCESS_MESSAGE = "SUCCESS";

    private static final String FAIL_CODE = "9999";
    private static final String FAIL_MESSAGE = "FAIL";

    private CommonResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private CommonResponse(Err err) {
        this.code = err.code();
        this.message = err.message();
        this.data = null;
    }

    public static <T> CommonResponse<T> success(String code, String message, T data) {
        return new CommonResponse<>(code, message, data);
    }

    public static <T> CommonResponse<T> success(T data) {
        return new CommonResponse<>(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static CommonResponse success(String successCode, String successMessage) {
        return new CommonResponse<>(successCode, successMessage, null);
    }

    public static CommonResponse success(String successMessage) {
        return new CommonResponse<>(SUCCESS_CODE, successMessage, null);
    }

    public static CommonResponse success() {
        return new CommonResponse<>(SUCCESS_CODE, SUCCESS_MESSAGE, null);
    }

    public static CommonResponse fail(Err err, Exception e) {
        return new CommonResponse<>(err.code(), e.getMessage(), null);
    }

    public static CommonResponse fail(Err err) {
        return new CommonResponse(err);
    }

    public static CommonResponse fail(ApiException ex) {
        return new CommonResponse<>(ex.getErr().code(), ex.getMessage(), null);
    }

    public static CommonResponse fail(String statusCode, String statusMessage) {
        return new CommonResponse<>(statusCode, statusMessage, null);
    }

    public static CommonResponse fail(String message) {
        return new CommonResponse<>(FAIL_CODE, message, null);
    }

    public static CommonResponse fail() {
        return new CommonResponse<>(FAIL_CODE, FAIL_MESSAGE, null);
    }
}
