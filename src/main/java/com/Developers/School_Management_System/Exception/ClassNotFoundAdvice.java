package com.Developers.School_Management_System.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class ClassNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ClassNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> Exceptionhandler(ClassNotFoundException exception){
        Map<String,String> erormap= new HashMap<>();
        erormap.put("erormessage",exception.getMessage());
        return erormap;
    }
}
