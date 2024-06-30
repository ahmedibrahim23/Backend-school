package com.Developers.School_Management_System.Exception;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(Long id){
        super("student not found with id: " + id);
    }
}
