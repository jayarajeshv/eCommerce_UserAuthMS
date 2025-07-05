package com.ecommerce.userauthservice.exceptions;

public class IncompleteUserDetailsException extends Exception {
    public IncompleteUserDetailsException(String message) {
        super(message);
    }
}
