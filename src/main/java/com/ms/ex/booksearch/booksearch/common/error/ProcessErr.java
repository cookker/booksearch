package com.ms.ex.booksearch.booksearch.common.error;

import com.ms.ex.booksearch.booksearch.common.error.Err;

import java.text.MessageFormat;

public enum ProcessErr implements Err {
    DEFAULT_API_EXCEPTION("9999", "")
    ;

    private final String code;
    private final String message;

    ProcessErr(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message.replaceAll("\\{.*\\}", "");
    }

    @Override
    public String message(Object... args) {
        return MessageFormat.format(message, args).replaceAll("\\{.*\\}", "");
    }
}
