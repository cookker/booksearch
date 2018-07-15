package com.ms.ex.booksearch.booksearch.common.error;

import java.text.MessageFormat;

public enum SystemErr implements Err {
    ERROR_UNKNOWN("S999", "네트워크 상태가 원활하지 않습니다. 잠시 후 다시 시도해주세요."),
    FILE_SERVER_ERROR("FileServerError",""),
    RETRY_REQUEST("9999", "잠시 후 다시 시도해주세요"),
            ;

    private final String code;
    private final String message;

    SystemErr(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String message(Object... args) {
        return MessageFormat.format(message, args);
    }
}
