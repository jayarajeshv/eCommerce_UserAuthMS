package com.ecommerce.userauthservice.exceptions;

import lombok.Data;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
