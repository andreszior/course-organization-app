package com.amv.courseorganizationapp.exceptions;

import org.springframework.http.HttpStatus;

import java.net.http.HttpClient;

public abstract class RequestValidationException extends Exception{

    protected HttpStatus httpStatus;
    protected String httpMessage;

    abstract HttpStatus setHttpStatus();
    abstract String setHttpMessage();

    public RequestValidationException(String exceptionMessage) {
        super(exceptionMessage);
        this.httpStatus = setHttpStatus();
        this.httpMessage = setHttpMessage();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public String getHttpMessage() {
        return httpMessage;
    }

}
