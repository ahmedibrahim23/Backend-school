package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.Exception.StudentNotFoundException;
import com.Developers.School_Management_System.modal.Attendance;
import com.Developers.School_Management_System.repo.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Attendance")
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceController(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @GetMapping
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Attendance getFeeById(@PathVariable Long id) {
        return attendanceRepository.findById(id).orElse(null);
    }

    @PostMapping
    public void saveAttendance(@RequestBody Attendance attendance) {
        attendanceRepository.save(attendance);
    }

    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        if (!attendanceRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        attendanceRepository.deleteById(id);

    }

    public Attendance updateAttendance(@PathVariable Long id, @RequestBody Attendance attendance) {
        return attendanceRepository.findById(id)
                .map(attendance1 -> {
                    attendance1.getStudentTble(attendance.getStudentTble());
                    attendance1.setDate(attendance.getDate());
                    attendance1.setStatus(attendance.getStatus());


                })
    }
    
}
