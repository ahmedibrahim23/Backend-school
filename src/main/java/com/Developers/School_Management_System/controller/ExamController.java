package com.Developers.School_Management_System.controller;


import com.Developers.School_Management_System.exception.ExamNotFoundException;
import com.Developers.School_Management_System.modal.Examination;
import com.Developers.School_Management_System.repo.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {
    public final ExamRepository examRepository;
    @Autowired
    public ExamController(ExamRepository examRepository){
        this.examRepository=examRepository;
    }
    @GetMapping()
    public List<Examination> getAllExam(){
        return examRepository.findAll();
    }
    @GetMapping("/{id}")
    public Examination getExamById(Long id){
        return examRepository.findById(id).orElseThrow(() -> new ExamNotFoundException(id));
    }
    @PostMapping
    public Examination saveExam(@RequestBody Examination examination){
        return examRepository.save(examination);
    }
    @DeleteMapping("/{id}")
    public String deleteExam(@PathVariable Long id){
        if(!examRepository.existsById(id)){
            throw new ExamNotFoundException(id);
        }
        examRepository.deleteById(id);
        return "The exam  with Id"+ id + "has been deleted succesfuly!";
    }
    @PutMapping("/{id}")
    public Examination updateExam(@PathVariable  Long id,@RequestBody Examination examination){
         return examRepository.findById(id).map(exam ->{
            exam.setMarks(examination.getMarks());
            exam.setStudent(examination.getStudent());
              return examRepository.save(exam);
        }).orElse(null);
    }


}
