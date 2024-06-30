package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.Exception.StudentNotFoundException;
import com.Developers.School_Management_System.modal.Student;
import com.Developers.School_Management_System.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class StudentController {
    private final StudentRepo studentRepo;
    @Autowired
    public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }
    @PostMapping("/student")
    Student newStudent(@RequestBody Student newStudent){
        return studentRepo.save(newStudent);
    }
    @GetMapping("/students")
    public List<Student> getAllStudent(){
        return studentRepo.findAll();
    }

    @GetMapping("/student/{id}")
    Student getStudentById(@PathVariable Long id){
        return studentRepo.findById(id).orElseThrow(()->new StudentNotFoundException(id));
    }

    @PutMapping("/student/{id}")
    Student updateStudent(@RequestBody Student newStudent, @PathVariable Long id){
        return studentRepo.findById(id).map(student->{
            student.setFullName(newStudent.getFullName());
            student.setAge(newStudent.getAge());
            student.setGender(newStudent.getGender());
            student.setPhone(newStudent.getPhone());
            student.setClassId(newStudent.getClassId());
            student.setPassword(newStudent.getPassword());
            student.setDateOfBirth(newStudent.getDateOfBirth());
            return studentRepo.save(student);
        }).orElseThrow(()->new StudentNotFoundException(id));
    }

    @DeleteMapping("/student/{id}")
    String deleteById(@PathVariable Long id){
        if (!studentRepo.existsById(id)){
            throw new StudentNotFoundException(id);
        }
        studentRepo.deleteById(id);
        return "The student have id"+id+"have been deleted";
    }
}
