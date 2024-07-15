package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.exception.ExamNotFoundException;
import com.Developers.School_Management_System.modal.Subject;
import com.Developers.School_Management_System.repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/all")
public class SubjectController {
    public final SubjectRepository subjectRepository;
    @Autowired
    public SubjectController(SubjectRepository subjectRepository){
        this.subjectRepository=subjectRepository;
    }
    @GetMapping
    public List<Subject> getAllSubject(){
        return subjectRepository.findAll();
    }
     @GetMapping("/{id}")
    public Subject getSubjectById( @PathVariable long id){
        return subjectRepository.findById(id).orElse(null);
    }
    @PostMapping
    public void saveSubject(@RequestBody  Subject subject){
        subjectRepository.save(subject);
    }
    @DeleteMapping("/{id}")
    public void deleteSubject( @PathVariable long id){
        if(!subjectRepository.existsById(id)){
            throw new ExamNotFoundException(id);
        }
        subjectRepository.deleteById(id);

    }
    public Subject  updateSubject(@PathVariable Long id, @RequestBody Subject subject){
          return subjectRepository.findById(id)
                .map(sub ->{
                    sub.setSubjectName(subject.getSubjectName());
                    sub.setClassTble(subject.getClassTble());
                    return subjectRepository.save(sub);
                }).orElseThrow(() ->  new  ExamNotFoundException(id)) ;

    }

}
