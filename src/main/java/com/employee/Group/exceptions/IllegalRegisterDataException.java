package com.employee.Group.exceptions;

import java.util.List;

public class IllegalRegisterDataException extends RuntimeException{
    private final List<String> errorMessages;

    public IllegalRegisterDataException(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    @Override
    public String getMessage() {
        return String.join("\n", errorMessages);
    }
}
