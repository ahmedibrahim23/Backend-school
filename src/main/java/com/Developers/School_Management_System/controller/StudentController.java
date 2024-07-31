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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/students")
@CrossOrigin("http://localhost:5173/")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepo classRepo;

    @GetMapping
    public List<Map<String , Object>> getAllStudents() {
        return this.studentRepository.findAll().stream().map(student -> {
            Map<String, Object> response = new HashMap<>();
            response.put("id", student.getId());
            response.put("fullname", student.getFullName());
            response.put("dateOfBirth", student.getDateOfBirth());
            response.put("gender", student.getGender());
            response.put("address", student.getAddress());
            response.put("phone", student.getPhone());
            response.put("email", student.getEmail());
            response.put("password", student.getPassword());
            response.put("parentname", student.getParentname());
            response.put("parentnumber", student.getParentnumber());
            response.put("class_id", student.getStdClass().getId());
            response.put("className", student.getStdClass().getName());
            return response;
        }).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Long studentId)
            throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
        return ResponseEntity.ok().body(student);
    }
    @PostMapping("/new")
    public ResponseEntity<Student > createStudent(@RequestBody Student student) throws ResourceNotFoundException {
        if (student.getStdClass() == null || student.getStdClass().getId()==null){
            throw new IllegalArgumentException("class id must provided.");
        }
        StdClass stdClass= classRepo.findById(student.getStdClass().getId())
                .orElseThrow(()-> new ResourceNotFoundException("class not found for this id:"+student.getStdClass().getId()));
        student.setStdClass(stdClass);
        Student createdStudent= studentRepository.save(student);
        return ResponseEntity.ok(createdStudent);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Long studentId,
                                                 @Validated @RequestBody Student studentDetails) throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id :: " + studentId));
        student.setFullName(studentDetails.getFullName());
        student.setDateOfBirth(studentDetails.getDateOfBirth());
        student.setEmail(studentDetails.getEmail());
        student.setAddress(studentDetails.getAddress());
        student.setGender(studentDetails.getGender());
        student.setParentname(studentDetails.getParentname());
        student.setParentnumber(studentDetails.getParentnumber());
        student.setPhone(studentDetails.getPhone());
        student.setPassword(studentDetails.getPassword());
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

    @GetMapping("/count")
    public ResponseEntity<Long> getStudentCount() {
        long count = studentRepository.count();
        return ResponseEntity.ok(count);
    }
}

