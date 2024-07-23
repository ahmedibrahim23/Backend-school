package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.exception.ResourceNotFoundException;
import com.Developers.School_Management_System.modal.StdClass;
import com.Developers.School_Management_System.modal.Teacher;
import com.Developers.School_Management_System.repo.ClassRepo;
import com.Developers.School_Management_System.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/class")
public class ClassController {
    @Autowired
    private ClassRepo stdClassRepository;
    @Autowired
    private TeacherRepo teacherRepository;

    @GetMapping
    public List<StdClass> getAllClasses() {
        return this.stdClassRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StdClass> getClassById(@PathVariable(value = "id") Long classId)
            throws ResourceNotFoundException {
        StdClass stdClass = stdClassRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found for this id :: " + classId));
        return ResponseEntity.ok().body(stdClass);
    }

    @PostMapping("/new")
    public ResponseEntity<StdClass> createClass(@RequestBody StdClass stdClass) throws ResourceNotFoundException {
        // Check if the teacher ID is provided in the request
        if (stdClass.getTeacher() == null || stdClass.getTeacher().getId() == null) {
            throw new IllegalArgumentException("Teacher ID must be provided.");
        }

        // Fetch the teacher from the repository
        Teacher teacher = teacherRepository.findById(stdClass.getTeacher().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found for this id :: " + stdClass.getTeacher().getId()));

        // Set the teacher to the class
        stdClass.setTeacher(teacher);

        // Save the class with the assigned teacher
        StdClass createdClass = stdClassRepository.save(stdClass);

        return ResponseEntity.ok(createdClass);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<StdClass> updateClass(@PathVariable(value = "id") Long classId,
                                                @Validated @RequestBody StdClass classDetails) throws ResourceNotFoundException {
        StdClass stdClass = stdClassRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found for this id :: " + classId));

        stdClass.setName(classDetails.getName());
        stdClass.setTeacher(classDetails.getTeacher());
        stdClass.setStudents(classDetails.getStudents());
        stdClass.setSubjects(classDetails.getSubjects());

        return ResponseEntity.ok(this.stdClassRepository.save(stdClass));
    }



    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteClass(@PathVariable(value = "id") Long classId) throws ResourceNotFoundException {
        StdClass stdClass = stdClassRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found for this id :: " + classId));

        this.stdClassRepository.delete(stdClass);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
