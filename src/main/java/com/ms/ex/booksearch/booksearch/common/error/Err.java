package com.ms.ex.booksearch.booksearch.common.error;

public interface Err {
    String code();
    String message();
    String message(Object... args);
}
