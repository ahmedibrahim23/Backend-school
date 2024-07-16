package com.Developers.School_Management_System.exception;

public class ClassNotFoundException  extends  RuntimeException{


    public ClassNotFoundException(Long id) {
        super("Class not found with id: " + id);
    }
    
}
