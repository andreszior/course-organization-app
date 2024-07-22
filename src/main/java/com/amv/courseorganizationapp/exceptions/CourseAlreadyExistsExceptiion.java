package com.amv.courseorganizationapp.exceptions;

import org.springframework.http.HttpStatus;

public class CourseAlreadyExistsExceptiion extends RequestValidationException{
    public CourseAlreadyExistsExceptiion(String exceptionMessage) {
        super(exceptionMessage);
    }

    @Override
    HttpStatus setHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    String setHttpMessage() {
        return "el curso ya existe";
    }
}
