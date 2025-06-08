package com.ecommerce.userauthservice.exceptions;

public class PasswordLengthRestrictionsNotMet extends Exception {
    public PasswordLengthRestrictionsNotMet(String message) {
        super(message);
    }
}
