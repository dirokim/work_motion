package com.workmotion.app.errors;

import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice
public class ErrorManager {


    @ExceptionHandler(Exception.class)
    public String handler1() {

        return "/error/error404";
    }


}
