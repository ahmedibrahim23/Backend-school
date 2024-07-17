package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.Exception.ClassNotFoundException;
import com.Developers.School_Management_System.modal.Class;
import com.Developers.School_Management_System.repo.ClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClassController {

    @Autowired
    private ClassRepo classRepo;

    @PostMapping("/class")
    public Class newClass(@RequestBody Class newClass) {
        return classRepo.save(newClass);
    }

    @GetMapping("/classes")
    public List<Class> getAllClasses() {
        return classRepo.findAll();
    }

    @GetMapping("/classes/{classId}")
    public Class getClassById(@PathVariable Long classId) {
        return classRepo.findById(classId)
                .orElseThrow(() -> new ClassNotFoundException(classId));
    }



    @PutMapping("/classes/{classId}")
    public Class updateClass(@RequestBody Class newClass, @PathVariable Long ClassId) {
        return classRepo.findById(ClassId)
                .map(classes-> {
                  classes .setName(newClass.getName());
                    return classRepo.save(classes);
                }).orElseThrow(() -> new ClassNotFoundException(ClassId));
    }

    @DeleteMapping("/classes/{classId}")
    String DeleteClass(@PathVariable long ClassId){
        if (!classRepo.existsById(ClassId)){
            throw new ClassNotFoundException(ClassId);
        }
        classRepo.deleteById(ClassId);
        return "The Class with ClassId"+ClassId + "has been deleted successfully!";
    }


}
