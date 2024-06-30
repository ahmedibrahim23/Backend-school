package com.Developers.School_Management_System.service;

import com.Developers.School_Management_System.exception.ExamNotFoundException;
import com.Developers.School_Management_System.modal.Examination;
import com.Developers.School_Management_System.repo.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExaminationService {
    public final ExamRepository examRepository;
    @Autowired
    public ExaminationService(ExamRepository examRepository){
        this.examRepository=examRepository;
    }
    public List<Examination> getAllExam(){
       return examRepository.findAll();
    }
    public Examination getExamById(Long id){return examRepository.findById(id).orElseThrow(null);
    }
    public void saveExam(Examination examination){examRepository.save(examination);}
    public void deleteExam(Long id){examRepository.deleteById(id);}
    public Examination  updateExam(Long id,Examination newExam){
        return examRepository.findById(id).map(exam ->{
            exam.setMarks(newExam.getMarks());
           return examRepository.save(exam);
        }).orElseThrow(() -> new ExamNotFoundException(id));
    }
}
