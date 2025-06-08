package com.ecommerce.userauthservice.exceptions;

import lombok.Data;

public class IncorrectPasswordException extends Exception {
    public IncorrectPasswordException(String message) {
        super(message);
    }
}
