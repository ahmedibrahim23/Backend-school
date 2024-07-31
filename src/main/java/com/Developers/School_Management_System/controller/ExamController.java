package com.Developers.School_Management_System.controller;


import com.Developers.School_Management_System.exception.ResourceNotFoundException;
import com.Developers.School_Management_System.modal.Examination;
import com.Developers.School_Management_System.repo.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exams")
@CrossOrigin("http://localhost:5173/")
public class ExamController {
    @Autowired
    private ExamRepository examRepository;

    // Get all exams
    @GetMapping
    public List<Examination> getAllExams() {
        return this.examRepository.findAll();
    }

    // Get exam by ID
    @GetMapping("/{id}")
    public ResponseEntity<Examination> getExamById(@PathVariable(value = "id") Long examId)
            throws ResourceNotFoundException {
        Examination exam = examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found for this id :: " + examId));
        return ResponseEntity.ok().body(exam);
    }

    // Create a new exam
    @PostMapping("/new")
    public Examination createExam(@RequestBody Examination exam) {
        return this.examRepository.save(exam);
    }

    // Update an existing exam
    @PutMapping("/edit/{id}")
    public ResponseEntity<Examination> updateExam(@PathVariable(value = "id") Long examId,
                                           @Validated @RequestBody Examination examDetails) throws ResourceNotFoundException {
        Examination exam = examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found for this id :: " + examId));

        // Update exam fields
        exam.setSubject(examDetails.getSubject());
        exam.setStudent(examDetails.getStudent());
        exam.setMarks(examDetails.getMarks());

        return ResponseEntity.ok(this.examRepository.save(exam));
    }

    // Delete an exam
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteExam(@PathVariable(value = "id") Long examId) throws ResourceNotFoundException {
        Examination exam = examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("Exam not found for this id :: " + examId));

        this.examRepository.delete(exam);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
