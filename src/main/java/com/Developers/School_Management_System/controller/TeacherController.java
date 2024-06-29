package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.Exception.ClassNotFoundException;
import com.Developers.School_Management_System.modal.Teacher;
import com.Developers.School_Management_System.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    private TeacherRepo teacherRepo;


    @PostMapping("/Teacher")
    public Teacher newTeacher(@RequestBody Teacher newTeacher) {
        return teacherRepo.save(newTeacher);
    }

    @GetMapping("/Teachers")
    public List<Teacher> getAllTeacher() {
        return teacherRepo.findAll();
    }

    @GetMapping("/Teachers/{id}")
    public Teacher getTeacherById(@PathVariable Long id) {
        return teacherRepo.findById(id)
                .orElseThrow(() -> new ClassNotFoundException(id));
    }

    @PutMapping("/Teachers/{id}")
    public Teacher updateTeacher(@RequestBody Teacher newTeacher, @PathVariable long id) {
        return teacherRepo.findById(id)
                .map(teacher -> {
                    teacher.setFullName(newTeacher.getFullName());
                    teacher.setDateOfBirth(newTeacher.getDateOfBirth());
                    teacher.setGender(newTeacher.getGender());
                    teacher.setAddress(newTeacher.getAddress());
                    teacher.setPhone(newTeacher.getPhone());
                    teacher.setEmail(newTeacher.getEmail());
                    teacher.setPassword(newTeacher.getPassword());
                    teacher.setHireDate(newTeacher.getHireDate());
                    return teacherRepo.save(teacher);
                }).orElseThrow(() -> new ClassNotFoundException(id));
    }


    @DeleteMapping("/Teachers/{id}")
    String DeleteTeacher(@PathVariable long id){
        if (!teacherRepo.existsById(id)){
            throw new ClassNotFoundException(id);
        }
        teacherRepo.deleteById(id);
        return "The Teacher with Id"+id + "has been deleted succesfuly!";
    }




}
