package com.lms.cms.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CourseNotFoundException.class)
    public String handleCourseNotFoundException(CourseNotFoundException ex) {
        return ex.getMessage();
    }
}
