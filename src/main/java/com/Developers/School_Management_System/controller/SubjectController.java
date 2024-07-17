package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.exception.ResourceNotFoundException;
import com.Developers.School_Management_System.modal.Subject;
import com.Developers.School_Management_System.repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;
    //get subjects
    @GetMapping("subjects")
    public List<Subject> getAllSubject(){
        return this.subjectRepository.findAll();
    }
    // get subject by id
    @GetMapping("/subjects/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable(value = "id") Long id)
        throws ResourceNotFoundException{
        Subject subject=subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("subject not found for this id:" + id));
        return ResponseEntity.ok().body(subject);

    }

    //save subjects
    @PostMapping("subjects")
    public Subject createSubject(@RequestBody Subject subject){
        return this.subjectRepository.save(subject);
    }

    //update subject
    @PutMapping("subject/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable(value = "id") Long id,
                                                 @Validated @RequestBody Subject subjectDetail) throws ResourceNotFoundException {
        Subject subject=subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("subject not found for this id:" + id));
            subject.setSubjectName(subjectDetail.getSubjectName());
            return ResponseEntity.ok(this.subjectRepository.save(subject));


    }
    //delete subject
    @DeleteMapping("/subjects/{id}")
    public Map<String,Boolean> deleteSubject(@PathVariable(value = "id") Long id){
        Subject subject= subjectRepository.findById(id).orElseThrow( () -> new ResourceAccessException("subject not found for this id"));
         this.subjectRepository.delete(subject);
         Map<String ,Boolean> response=new HashMap<>();
         response.put("delete", Boolean.TRUE);
         return response;
    }


}
