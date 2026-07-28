package com.linuka.employeehub.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(EmployeeNotFoundException.class)
    public String handleEmployeeNotFound(
            EmployeeNotFoundException exception,
            Model model) {

        logger.error(exception.getMessage());

        model.addAttribute(
                "errorMessage",
                exception.getMessage()
        );

        return "error/404";
    }


    @ExceptionHandler(Exception.class)
    public String handleGeneralException(
            Exception exception,
            Model model) {

        logger.error(exception.getMessage());

        model.addAttribute(
                "errorMessage",
                exception.getMessage()
        );

        return "error/500";
    }

}