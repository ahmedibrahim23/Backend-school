package com.Developers.School_Management_System.controller;


import com.Developers.School_Management_System.Exception.ResourceNotFoundException;
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
@RequestMapping("/api/v1/")
public class ExamController {
    @Autowired
    private ExamRepository examRepository;
    //get all exams
    @GetMapping("exams")
    public List<Examination> getAllExam(){
        return this.examRepository.findAll();
    }
    //get exam by id
    @GetMapping("/exams/{id}")
    public ResponseEntity<Examination> getExamById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
            Examination examination=examRepository.findById(id)
                    .orElseThrow(()-> new ResourceNotFoundException("exam not found fot id"+id));
            return ResponseEntity.ok().body(examination);

        }
    //save exam
    public Examination createExam( @RequestBody Examination examination){
        return this.examRepository.save(examination);

    }

    //update exam
    @PutMapping("exams/{id}")
    public ResponseEntity<Examination> updateSubject(@PathVariable(value = "id") Long id,
                                                 @Validated @RequestBody Examination examinationDetails) throws ResourceNotFoundException {
        Examination examination=examRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("subject not found for this id:" + id));
        examination.setMarks(examinationDetails.getMarks());
        return ResponseEntity.ok(this.examRepository.save(examination));


    }
    //delete exam
    public Map< String, Boolean> deleteExam(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Examination examination= examRepository.findById(id).orElseThrow ( () -> new ResourceNotFoundException("subject not found for this id"));
        this.examRepository.delete(examination);
        Map<String ,Boolean> response=new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }




}
