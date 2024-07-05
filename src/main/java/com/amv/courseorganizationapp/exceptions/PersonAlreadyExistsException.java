package com.amv.courseorganizationapp.exceptions;

import org.springframework.http.HttpStatus;

public class PersonAlreadyExistsException extends RequestValidationException {


    public PersonAlreadyExistsException(String exceptionMessage) {
        super(exceptionMessage);
    }

    @Override
    HttpStatus setHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    String setHttpMessage() {
        return "El usuario ya existe";
    }
}
