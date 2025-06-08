package com.ecommerce.userauthservice.exceptions;

import lombok.Data;

public class PasswordLengthRestrictionsNotMet extends Exception {
    public PasswordLengthRestrictionsNotMet(String message) {
        super(message);
    }
}
