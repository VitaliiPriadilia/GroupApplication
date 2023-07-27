package com.employee.Group.exceptions;

import java.util.List;

public class IllegalGroupDataException extends RuntimeException{
    private final List<String> errorMessages;

    public IllegalGroupDataException(List<String> errorMessages) {
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
