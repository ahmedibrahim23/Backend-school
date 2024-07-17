package com.Developers.School_Management_System.exception;

public class SubjectNotFoundException extends RuntimeException{
    public SubjectNotFoundException(Long id){
        super("subject not found with id: " + id);
    }
}
