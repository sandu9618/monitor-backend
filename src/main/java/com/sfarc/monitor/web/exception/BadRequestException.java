package com.sfarc.monitor.web.exception;

/**
 * @author = madhuwantha
 * created on 4/11/2021
 */
public class BadRequestException extends RuntimeException{
    public BadRequestException(Class clazz, String... paramsMap) {
        super(
                ErrorMessage.generateMessage(
                        clazz.getSimpleName(),
                        ErrorMessage.toMap(String.class, String.class, (Object[]) paramsMap),
                        " Wos not valid for parameters"
                )
        );
    }
}
