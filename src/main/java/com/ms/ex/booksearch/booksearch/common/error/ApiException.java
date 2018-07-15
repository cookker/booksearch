package com.ms.ex.booksearch.booksearch.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private Err err = ProcessErr.DEFAULT_API_EXCEPTION;
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public ApiException(HttpStatus httpStatus, String message, Err err) {
        super(message);
        this.err = err;
        this.httpStatus = httpStatus;
    }

    public ApiException(HttpStatus httpStatus, Err err) {
        this(httpStatus, err.message(), err);
    }

    public ApiException(HttpStatus httpStatus, String message) {
        this(httpStatus, message, ProcessErr.DEFAULT_API_EXCEPTION);
    }

    public ApiException(Err err) {
        super(err.message());
        this.err = err;
    }

    public ApiException(String message) {
        this(message, ProcessErr.DEFAULT_API_EXCEPTION);
    }

    public ApiException(String message, Err err) {
        super(message);
        this.err = err;
    }

    public ApiException(String message, Throwable cause, Err err) {
        super(message, cause);
        this.err = err;
    }

    public ApiException(Throwable cause, Err err) {
        super(cause);
        this.err = err;
    }
}
