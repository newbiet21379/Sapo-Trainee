package com.sapo.edu.category.validate.exception;

public class CategoryNotFoundException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public CategoryNotFoundException(int category_id) {
        super("Could not find Category " + category_id);
    }
}
