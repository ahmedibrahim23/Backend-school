package com.Developers.School_Management_System.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
    private static  final Long serialVersionUID=1l;
    public ResourceNotFoundException(String message){
        super(message);
    }

}
