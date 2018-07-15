package com.ms.ex.booksearch.booksearch.common;

import com.ms.ex.booksearch.booksearch.common.error.ApiException;
import com.ms.ex.booksearch.booksearch.common.error.SystemErr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = {RestController.class})
@ResponseBody
@Slf4j
@Order(1)
public class ExceptionHandler {
    @Autowired
    MessageSource messageSource;

    @org.springframework.web.bind.annotation.ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private CommonResponse handleRuntimeException(RuntimeException e) {
        log.error("{}", e.getMessage(), e);
        return CommonResponse.fail(SystemErr.ERROR_UNKNOWN);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({ApiException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private CommonResponse handleOrderApiException(ApiException e) {
        log.error("{}", e.getMessage(), e);
        return CommonResponse.fail(e.getErr(), e);
    }
}
