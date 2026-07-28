package com.linuka.employeehub.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public String handleEmployeeNotFoundException(
            EmployeeNotFoundException ex,
            Model model) {

        model.addAttribute("errorMessage", ex.getMessage());

        return "error";
    }
}
