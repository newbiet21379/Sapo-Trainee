package com.sapo.edu.category.validate.exceptionAdvice;

import com.sapo.edu.category.validate.exception.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class CategoryNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String CategoryNotFoundHandler(CategoryNotFoundException pro) {
        return pro.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ExceptionHandlerCategory(Exception ex) {
        return ex.getMessage();
    }
}
