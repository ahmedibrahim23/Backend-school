package com.Developers.School_Management_System.exception;

public class ExamNotFoundException extends RuntimeException{
    public ExamNotFoundException(Long id){super("Class not found with id: " + id);}
}
