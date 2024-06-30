package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.modal.Examination;
import com.Developers.School_Management_System.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ExamController {
    public final ExaminationService examinationService;
    @Autowired
    public ExamController(ExaminationService examinationService){
        this.examinationService=examinationService;
    }
    @PostMapping("/exams")
    public void createExam(@RequestBody Examination examination){
        examinationService.saveExam(examination);
    }
    @GetMapping("/exams")
    public List<Examination> getAllExam(){
        return examinationService.getAllExam();
    }
    @GetMapping("/exams/{id}")
    public Examination getExamById(@PathVariable Long id){
        return examinationService.getExamById(id);
    }
    @PutMapping("/exams/{id}")
    public Examination updateExam(@PathVariable Long id, @RequestBody Examination examination){
        return examinationService.updateExam(id, examination);
    }
    @DeleteMapping("/exams/{id}")
    public ResponseEntity<Void> deleteExam(@PathVariable Long id) {
        examinationService.deleteExam(id);
        return ResponseEntity.noContent().build();
    }
}
