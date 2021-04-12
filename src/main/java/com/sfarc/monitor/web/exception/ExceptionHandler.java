package com.sfarc.monitor.web.exception;

import com.sfarc.monitor.web.error.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author = madhuwantha
 * created on 4/11/2021
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class ExceptionHandler {
    /**
     * Handles BadRequestException. Created to encapsulate errors with more detail
     *
     * @param ex the BadRequestException
     * @return the ApiError object
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequest(BadRequestException ex){
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    /**
     * Handles FailedProcessingSensorDataException. Created to encapsulate errors with more detail
     *
     * @param ex the FailedProcessingSensorDataException
     * @return the ApiError object
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(FailedProcessingSensorDataException.class)
    protected ResponseEntity<Object> handleFailedProcessingSensorDataException(FailedProcessingSensorDataException ex){
        ApiError apiError = new ApiError(INTERNAL_SERVER_ERROR);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
