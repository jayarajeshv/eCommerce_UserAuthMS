package com.ecommerce.userauthservice.exceptionHandlers;

import com.ecommerce.userauthservice.dtos.UserAuthExceptionDto;
import com.ecommerce.userauthservice.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthServiceExceptionHandler {
    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<UserAuthExceptionDto> handleUserAlreadyExist(UserAlreadyExist exception) {
        UserAuthExceptionDto dto = new UserAuthExceptionDto();
        dto.setMessage(exception.getMessage());
        dto.setResolution("Please try Login instead of Signup");
        return new ResponseEntity<>(dto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserAuthExceptionDto> handleUserNotFoundException(UserNotFoundException exception) {
        UserAuthExceptionDto dto = new UserAuthExceptionDto();
        dto.setMessage(exception.getMessage());
        dto.setResolution("Please try to Signup before Login");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordLengthRestrictionsNotMet.class)
    public ResponseEntity<UserAuthExceptionDto> handlePasswordLengthRestrictionsNotMet(PasswordLengthRestrictionsNotMet exception) {
        UserAuthExceptionDto dto = new UserAuthExceptionDto();
        dto.setMessage(exception.getMessage());
        dto.setResolution("Password should be least 6 characters");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<UserAuthExceptionDto> handleIncorrectPasswordException(IncorrectPasswordException exception) {
        UserAuthExceptionDto dto = new UserAuthExceptionDto();
        dto.setMessage(exception.getMessage());
        dto.setResolution("Please try with correct password");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<UserAuthExceptionDto> handleInvalidTokenException(InvalidTokenException exception) {
        UserAuthExceptionDto dto = new UserAuthExceptionDto();
        dto.setMessage(exception.getMessage());
        dto.setResolution("Please try with correct token");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
