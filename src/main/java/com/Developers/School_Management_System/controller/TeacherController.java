package com.Developers.School_Management_System.controller;



import com.Developers.School_Management_System.exception.ResourceNotFoundException;
import com.Developers.School_Management_System.modal.Teacher;
import com.Developers.School_Management_System.repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
    @Autowired
    private TeacherRepo teacherRepository;
    @PostMapping("/new")
    public Teacher saveTeacher(@RequestBody Teacher teacher){
        return this.teacherRepository.save(teacher);
    }

    @GetMapping
    public List<Teacher> getAllTeacher(){
        return this.teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable(value = "id") Long teachertId)
            throws ResourceNotFoundException {
        Teacher teacher= teacherRepository.findById(teachertId)
                .orElseThrow(()->new ResourceNotFoundException("Teacher fo this id"+teachertId));
        return ResponseEntity.ok().body(teacher);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable(value = "id") Long studentId,
                                                 @Validated @RequestBody Teacher teacherDetail) throws ResourceNotFoundException {
        Teacher teacher = teacherRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student for this id" + studentId));
        teacher.setFullName(teacherDetail.getFullName());
        teacher.setDateOfBirth(teacherDetail.getDateOfBirth());
        teacher.setGender(teacherDetail.getGender());
        teacher.setAddress(teacherDetail.getAddress());
        teacher.setPhone(teacherDetail.getPhone());
        teacher.setAge(teacherDetail.getAge());
        teacher.setEmail(teacherDetail.getEmail());
        teacher.setPassword(teacherDetail.getPassword());
        return ResponseEntity.ok(this.teacherRepository.save(teacher));
    }



    @DeleteMapping("delete/{id}")
    public Map<String, Boolean> deleteTeacher(@PathVariable(value = "id") Long teacherId) throws ResourceNotFoundException {
        Teacher teacher= teacherRepository.findById(teacherId)
                .orElseThrow(()->new ResourceNotFoundException("Teacher for this id"+teacherId));
        this.teacherRepository.delete(teacher);

        Map<String, Boolean> response =new HashMap<>();
        response.put("delete", Boolean.TRUE);

        return response;
    }
}

