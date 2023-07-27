package com.employee.Group.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Date;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorObject> roleNotFoundException(RoleNotFoundException exception){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setErrorMessages(Collections.singletonList(exception.getMessage()));
        errorObject.setTimestamp(new Date());
        errorObject.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalRegisterDataException.class)
    public ResponseEntity<ErrorObject> illegalRegisterDataException(IllegalRegisterDataException exception){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setErrorMessages(exception.getErrorMessages());
        errorObject.setTimestamp(new Date());
        errorObject.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorObject> badCredentialsException(BadCredentialsException exception){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setErrorMessages(Collections.singletonList(exception.getMessage()));
        errorObject.setTimestamp(new Date());
        errorObject.setStatus(HttpStatus.UNAUTHORIZED.value());

        return new ResponseEntity<>(errorObject, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalGroupDataException.class)
    public ResponseEntity<ErrorObject> illegalGroupDataException(IllegalGroupDataException exception){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setErrorMessages(exception.getErrorMessages());
        errorObject.setTimestamp(new Date());
        errorObject.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GroupNotFoundException.class)
    public ResponseEntity<ErrorObject> groupNotFoundException(GroupNotFoundException exception){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setErrorMessages(Collections.singletonList(exception.getMessage()));
        errorObject.setTimestamp(new Date());
        errorObject.setStatus(HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

}
