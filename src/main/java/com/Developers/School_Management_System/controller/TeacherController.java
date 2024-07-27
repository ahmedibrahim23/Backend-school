package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.exception.ResourceNotFoundException;
import com.Developers.School_Management_System.modal.Teacher;
import com.Developers.School_Management_System.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    @Autowired
    private TeacherRepo teacherRepository;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return this.teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable(value = "id") Long teacherId)
            throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + teacherId));
        return ResponseEntity.ok().body(teacher);
    }

    @PostMapping("/new")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return this.teacherRepository.save(teacher);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable(value = "id") Long teacherId,
                                                 @Validated @RequestBody Teacher teacherDetails) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + teacherId));

        teacher.setFullName(teacherDetails.getFullName());
        teacher.setDateOfBirth(teacherDetails.getDateOfBirth());
        teacher.setGender(teacherDetails.getGender());
        teacher.setAddress(teacherDetails.getAddress());
        teacher.setPhone(teacherDetails.getPhone());
        teacher.setEmail(teacherDetails.getEmail());
        teacher.setAge(teacherDetails.getAge());
        teacher.setPassword(teacherDetails.getPassword());
        teacher.setHireDate(teacherDetails.getHireDate());

        return ResponseEntity.ok(this.teacherRepository.save(teacher));
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteTeacher(@PathVariable(value = "id") Long teacherId) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + teacherId));

        this.teacherRepository.delete(teacher);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}

