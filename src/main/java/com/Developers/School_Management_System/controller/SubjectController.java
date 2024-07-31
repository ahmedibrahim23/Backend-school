package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.exception.ResourceNotFoundException;
import com.Developers.School_Management_System.modal.Subject;
import com.Developers.School_Management_System.modal.Teacher;
import com.Developers.School_Management_System.repo.SubjectRepository;
import com.Developers.School_Management_System.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin("http://localhost:5173/")
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepo teacherRepository;

    @GetMapping
    public List<Map<String, Object>> getAllSubjects() {
        return subjectRepository.findAll().stream().map(subject -> {
            Map<String, Object> response = new HashMap<>();
            response.put("id", subject.getId());
            response.put("subjectName", subject.getName());
            response.put("teacherId", subject.getTeacher().getId());
            response.put("teacherName", subject.getTeacher().getFullName());
            return response;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getSubjectById(@PathVariable(value = "id") Long subjectId)
            throws ResourceNotFoundException {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found for this id :: " + subjectId));

        Map<String, Object> response = new HashMap<>();
        response.put("id", subject.getId());
        response.put("name", subject.getName());
        response.put("teacherId", subject.getTeacher().getId());
        response.put("teacherName", subject.getTeacher().getFullName());

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/new")
    public ResponseEntity<?> createSubject(@Validated @RequestBody Map<String, Object> request, BindingResult result) throws ResourceNotFoundException {
        if (request.get("teacherId") == null) {
            result.rejectValue("teacherId", "error.class", "Teacher ID is required.");
        }
        if (request.get("name") == null || ((String) request.get("name")).isEmpty()) {
            result.rejectValue("name", "error.class", "Subject name is required.");
        }

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Long teacherId;
        try {
            teacherId = Long.valueOf(request.get("teacherId").toString());
        } catch (NumberFormatException e) {
            result.rejectValue("teacherId", "error.class", "Invalid teacher ID format.");
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Teacher teacher;
        try {
            teacher = teacherRepository.findById(teacherId)
                    .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + teacherId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found for this id :: " + teacherId);
        }

        Subject subject = new Subject();
        subject.setName((String) request.get("name"));
        subject.setTeacher(teacher);

        Subject createdSubject = subjectRepository.save(subject);

        Map<String, Object> response = new HashMap<>();
        response.put("id", createdSubject.getId());
        response.put("name", createdSubject.getName());
        response.put("teacherId", createdSubject.getTeacher().getId());
        response.put("teacherName", createdSubject.getTeacher().getFullName());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> updateSubject(@PathVariable(value = "id") Long subjectId,
                                                             @Validated @RequestBody Subject subjectDetails,
                                                             BindingResult result) throws ResourceNotFoundException {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found for this id :: " + subjectId));

        subject.setName(subjectDetails.getName());
        subject.setTeacher(subjectDetails.getTeacher());

        Subject updatedSubject = subjectRepository.save(subject);

        Map<String, Object> response = new HashMap<>();
        response.put("id", updatedSubject.getId());
        response.put("name", updatedSubject.getName());
        response.put("teacherId", updatedSubject.getTeacher().getId());
        response.put("teacherName", updatedSubject.getTeacher().getFullName());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteSubject(@PathVariable(value = "id") Long subjectId) throws ResourceNotFoundException {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found for this id :: " + subjectId));

        subjectRepository.delete(subject);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
