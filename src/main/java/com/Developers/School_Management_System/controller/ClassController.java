package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.exception.ResourceNotFoundException;
import com.Developers.School_Management_System.modal.StdClass;
import com.Developers.School_Management_System.modal.Teacher;
import com.Developers.School_Management_System.repo.ClassRepo;
import com.Developers.School_Management_System.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/class")
public class ClassController {
    @Autowired
    private ClassRepo stdClassRepository;

    @Autowired
    private TeacherRepo teacherRepository;

    @GetMapping
    public List<Map<String, Object>> getAllClasses() {
        return stdClassRepository.findAll().stream().map(stdClass -> {
            Map<String, Object> response = new HashMap<>();
            response.put("id", stdClass.getId());
            response.put("name", stdClass.getName());
            response.put("teacherId", stdClass.getTeacher().getId());
            response.put("teacherName", stdClass.getTeacher().getFullName());
            return response;
        }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getClassById(@PathVariable(value = "id") Long classId)
            throws ResourceNotFoundException {
        StdClass stdClass = stdClassRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found for this id :: " + classId));

        Map<String, Object> response = new HashMap<>();
        response.put("id", stdClass.getId());
        response.put("name", stdClass.getName());
        response.put("teacherId", stdClass.getTeacher().getId());
        response.put("teacherName", stdClass.getTeacher().getFullName());

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/new")
    public ResponseEntity<?> createClass(@Validated @RequestBody Map<String, Object> request, BindingResult result) throws ResourceNotFoundException {
        if (request.get("teacherId") == null) {
            result.rejectValue("teacherId", "error.class", "Teacher ID is required.");
        }
        if (request.get("name") == null || ((String) request.get("name")).isEmpty()) {
            result.rejectValue("name", "error.class", "Class name is required.");
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

        StdClass stdClass = new StdClass();
        stdClass.setName((String) request.get("name"));
        stdClass.setTeacher(teacher);

        StdClass createdClass = stdClassRepository.save(stdClass);

        Map<String, Object> response = new HashMap<>();
        response.put("id", createdClass.getId());
        response.put("name", createdClass.getName());
        response.put("teacherId", createdClass.getTeacher().getId());
        response.put("teacherName", createdClass.getTeacher().getFullName());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Map<String, Object>> updateClass(@PathVariable(value = "id") Long classId,
                                                           @Validated @RequestBody StdClass classDetails) throws ResourceNotFoundException {
        StdClass stdClass = stdClassRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found for this id :: " + classId));

        stdClass.setName(classDetails.getName());
        stdClass.setTeacher(classDetails.getTeacher());

        StdClass updatedClass = stdClassRepository.save(stdClass);

        Map<String, Object> response = new HashMap<>();
        response.put("id", updatedClass.getId());
        response.put("name", updatedClass.getName());
        response.put("teacherId", updatedClass.getTeacher().getId());
        response.put("teacherName", updatedClass.getTeacher().getFullName());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteClass(@PathVariable(value = "id") Long classId) throws ResourceNotFoundException {
        StdClass stdClass = stdClassRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found for this id :: " + classId));

        stdClassRepository.delete(stdClass);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
