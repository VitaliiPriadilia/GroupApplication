package com.employee.Group.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ErrorObject {
    private List<String> errorMessages;
    private Date timestamp;
    private int status;
}
