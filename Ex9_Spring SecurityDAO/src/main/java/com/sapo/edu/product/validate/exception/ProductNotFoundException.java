package com.sapo.edu.product.validate.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(int id) {
        super("Could not find Product " + id);
    }
}
