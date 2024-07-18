package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.exception.ResourceNotFoundException;
import com.Developers.School_Management_System.modal.StdClass;
import com.Developers.School_Management_System.repo.ClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/class")
public class ClassController {
    @Autowired
    private ClassRepo classRepository;

    @GetMapping
    public List<StdClass> getAllStudent(){
        return this.classRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StdClass> getClassById(@PathVariable(value = "id") Long classId)
            throws ResourceNotFoundException {
        StdClass stdClass= classRepository.findById(classId)
                .orElseThrow(()->new ResourceNotFoundException("Student for this id"+ classId));
        return ResponseEntity.ok().body(stdClass);
    }

    @PostMapping("/new")
    public StdClass saveClass(@RequestBody StdClass stdClass){
        return this.classRepository.save(stdClass);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<StdClass> updateClass(@PathVariable(value = "id") Long classId,
                                                @Validated @RequestBody StdClass ClassDetail) throws ResourceNotFoundException{
        StdClass stdClass= classRepository.findById(classId)
                .orElseThrow(()->new ResourceNotFoundException("Student for this id"+ classId));
        stdClass.setName(ClassDetail.getName());
        stdClass.setSubject(ClassDetail.getSubject());

        return ResponseEntity.ok(this.classRepository.save(stdClass));
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long classId) throws ResourceNotFoundException {
        StdClass stdClass= classRepository.findById(classId)
                .orElseThrow(()->new ResourceNotFoundException("Student for this id"+ classId));
        this.classRepository.delete(stdClass);

        Map<String, Boolean> response =new HashMap<>();
        response.put("delete", Boolean.TRUE);

        return response;
    }
}
