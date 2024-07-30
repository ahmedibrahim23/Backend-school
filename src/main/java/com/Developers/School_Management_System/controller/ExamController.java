package com.Developers.School_Management_System.controller;


import com.Developers.School_Management_System.Exception.ResourceNotFoundException;
import com.Developers.School_Management_System.modal.Exam;
import com.Developers.School_Management_System.modal.Student;
import com.Developers.School_Management_System.modal.Subject;
import com.Developers.School_Management_System.repo.ExamRepository;
import com.Developers.School_Management_System.repo.StudentRepo;
import com.Developers.School_Management_System.repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/exams")
public class ExamController {

    @Autowired
    private StudentRepo studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ExamRepository examRepository;

    @GetMapping
    public List<Map<String , Object>> getAllSExams() {
        return this.examRepository.findAll().stream().map(exam -> {
            Map<String, Object> response=new HashMap<>();
            response.put("id",exam.getId());
            response.put("student_id",exam.getStudent().getFullName());
            response.put("subject_id",exam.getSubject().getSubjectName());
            response.put("stdMarks",exam.getMarks());
            return response;
        }).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable(value = "id") Long examId)
            throws ResourceNotFoundException {
        Exam exam= examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("exam not found for this id :: " + examId));
        return ResponseEntity.ok().body(exam);
    }
    @PostMapping("/new")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) throws ResourceNotFoundException {
        if (exam.getStudent() == null || exam.getStudent().getId() == null) {
            throw new IllegalArgumentException("Student must be provided.");
        }

        if (exam.getSubject() == null || exam.getSubject().getId() == null) {
            throw new IllegalArgumentException("Subject must be provided.");
        }

        Student student = studentRepository.findById(exam.getStudent().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found for this id: " + exam.getStudent().getId()));

        Subject subject = subjectRepository.findById(exam.getSubject().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found for this id: " + exam.getSubject().getId()));

        exam.setStudent(student);
        exam.setSubject(subject);

        Exam createExam = examRepository.save(exam);
        return ResponseEntity.ok(createExam);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Exam> updateExam(@PathVariable(value = "id") Long examId,
                                           @Validated @RequestBody Exam examDetails) throws ResourceNotFoundException {
        Exam exam=examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("exam not found for this id :: " + examId));
        exam.setSubject(examDetails.getSubject());
        exam.setStudent(examDetails.getStudent());
        exam.setMarks(examDetails.getMarks());
        return ResponseEntity.ok(this.examRepository.save(exam));
    }
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteExam(@PathVariable(value = "id") Long examId) throws ResourceNotFoundException {
        Exam exam= examRepository.findById(examId)
                .orElseThrow(() -> new ResourceNotFoundException("exam not found for this id :: " + examId));
        this.examRepository.delete(exam);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }




}
