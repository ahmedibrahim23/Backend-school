package com.Developers.School_Management_System.exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(Long id){
        super("student not found with id: " + id);
    }
}
