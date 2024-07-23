package com.Developers.School_Management_System.controller;


import com.Developers.School_Management_System.exception.ResourceNotFoundException;
import com.Developers.School_Management_System.modal.StdClass;
import com.Developers.School_Management_System.modal.Student;
import com.Developers.School_Management_System.repo.ClassRepo;
import com.Developers.School_Management_System.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepo classRepository;

    @GetMapping
    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
        return ResponseEntity.ok().body(student);
    }

    @PostMapping("/new")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) throws ResourceNotFoundException {
        // Check if the class ID is provided in the request
        if (student.getStdClass() == null || student.getStdClass().getId() == null) {
            throw new IllegalArgumentException("Class ID must be provided.");
        }

        // Fetch the class from the repository
        StdClass stdClass = classRepository.findById(student.getStdClass().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Class not found for this id :: " + student.getStdClass().getId()));

        // Set the class to the student
        student.setStdClass(stdClass);

        // Save the student with the assigned class
        Student createdStudent = studentRepository.save(student);

        return ResponseEntity.ok(createdStudent);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long studentId,
                                                 @Validated @RequestBody Student studentDetails) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

        student.setName(studentDetails.getName());
        student.setAge(studentDetails.getAge());
        student.setStdClass(studentDetails.getStdClass());

        return ResponseEntity.ok(this.studentRepository.save(student));
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long studentId) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));

        this.studentRepository.delete(student);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}

