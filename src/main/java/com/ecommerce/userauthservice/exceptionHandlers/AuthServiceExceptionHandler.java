package com.ecommerce.userauthservice.exceptionHandlers;

import com.ecommerce.userauthservice.dtos.UserSignUpExceptionDto;
import com.ecommerce.userauthservice.exceptions.PasswordLengthRestrictionsNotMet;
import com.ecommerce.userauthservice.exceptions.UserAlreadyExist;
import com.ecommerce.userauthservice.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthServiceExceptionHandler {
    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<UserSignUpExceptionDto> handleUserAlreadyExist(UserAlreadyExist exception) {
        UserSignUpExceptionDto dto = new UserSignUpExceptionDto();
        dto.setMessage(exception.getMessage());
        dto.setResolution("Please try Login instead of Signup");
        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserSignUpExceptionDto> handleUserNotFoundException(UserNotFoundException exception) {
        UserSignUpExceptionDto dto = new UserSignUpExceptionDto();
        dto.setMessage(exception.getMessage());
        dto.setResolution("Please try to Signup before Login");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordLengthRestrictionsNotMet.class)
    public ResponseEntity<UserSignUpExceptionDto> handlePasswordLengthRestrictionsNotMet(PasswordLengthRestrictionsNotMet exception) {
        UserSignUpExceptionDto dto = new UserSignUpExceptionDto();
        dto.setMessage(exception.getMessage());
        dto.setResolution("Password should be least 6 characters");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

}
