package com.codecool.web.model.exceptions;

public class ProductAlreadyExistsException extends ShopException {
    
    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
