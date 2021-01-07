package com.sapo.edu.product.validate.exception;

public class ProductNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = -7114438265284339999L;

    public ProductNotFoundException(int id) {
        super("Could not find Product " + id);
    }
}
