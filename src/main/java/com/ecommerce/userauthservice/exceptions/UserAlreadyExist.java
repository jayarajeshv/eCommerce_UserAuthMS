package com.ecommerce.userauthservice.exceptions;

import lombok.Data;

public class UserAlreadyExist extends Exception {
    public UserAlreadyExist(String message) {
        super(message);
    }
}
