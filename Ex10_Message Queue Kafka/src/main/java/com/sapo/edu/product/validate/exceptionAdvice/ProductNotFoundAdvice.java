package com.sapo.edu.product.validate.exceptionAdvice;

import com.sapo.edu.product.validate.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ProductNotFoundHandler(ProductNotFoundException pro){
        return  pro.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String ExceptionHandlerProduct(Exception ex){
        return ex.getMessage();
    }
}
