package com.Developers.School_Management_System.Exception;

public class ClassNotFoundException  extends  RuntimeException{


    public ClassNotFoundException(Long id) {
        super("Class not found with id: " + id);
    }
    
}
