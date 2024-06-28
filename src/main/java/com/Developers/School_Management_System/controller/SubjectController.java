package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.modal.Subject;
import com.Developers.School_Management_System.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
public class SubjectController {
    public final SubjectService subjectService;
    public SubjectController(SubjectService subjectService){
        this.subjectService=subjectService;
    }
    public ResponseEntity<List<Subject>> getAllSubject(){
        List<Subject> subjects = subjectService.getAllSubject();
        return ResponseEntity.ok(subjects);
    }
}
