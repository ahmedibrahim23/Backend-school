package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.modal.Subject;
import com.Developers.School_Management_System.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/all")
public class SubjectController {
    public final SubjectService subjectService;
    public SubjectController(SubjectService subjectService){
        this.subjectService=subjectService;
    }
    @GetMapping
    public List<Subject> getAllSubject(){
        return subjectService.getAllSubject();
    }
    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable Long id){
        return subjectService.getSubjectById(id);
    }
    @PostMapping
    public void saveSubject(@RequestBody Subject subject){
         subjectService.saveSubject(subject);
    }
    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id){
        subjectService.deleteSubject(id);
    }
    public void updateSubject(@PathVariable Long id, @RequestBody Subject subject){
        subject.setId(id);
        subjectService.saveSubject(subject);
    }

}
