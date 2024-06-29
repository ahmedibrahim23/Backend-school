package com.Developers.School_Management_System.service;

import com.Developers.School_Management_System.modal.Subject;
import com.Developers.School_Management_System.repo.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    public final SubjectRepository subjectRepository;
    @Autowired
    public SubjectService(SubjectRepository subjectRepository){
        this.subjectRepository=subjectRepository;
    }
    public List<Subject> getAllSubject(){
        return subjectRepository.findAll();
    }
    public Subject getSubjectById(Long id){return subjectRepository.findById(id).orElse(null);}
    public void saveSubject(Subject subject){subjectRepository.save(subject);}
    public void deleteSubject(Long id){subjectRepository.deleteById(id);}
}
