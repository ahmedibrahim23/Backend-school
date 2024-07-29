package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.exception.ResourceNotFoundException;
import com.Developers.School_Management_System.modal.Attendance;
import com.Developers.School_Management_System.modal.StdClass;
import com.Developers.School_Management_System.modal.Student;
import com.Developers.School_Management_System.repo.AttendanceRepository;
import com.Developers.School_Management_System.repo.ClassRepo;
import com.Developers.School_Management_System.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepo classRepository;



    @PostMapping("/mark")
    public Attendance markAttendance(@RequestParam Long studentId, @RequestParam Long classId, @RequestParam Boolean present) {
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        Optional<StdClass> stdClassOpt = classRepository.findById(classId);

        if (studentOpt.isPresent() && stdClassOpt.isPresent()) {
            Attendance attendance = new Attendance();
            attendance.setStudent(studentOpt.get());
            attendance.setStdClass(stdClassOpt.get());
            attendance.setAttendanceDate(LocalDateTime.now());
            attendance.setPresent(present);
            return attendanceRepository.save(attendance);
        } else {
            // Handle the case where either the student or the class is not found
            throw new RuntimeException("Student or Class not found");
        }
    }

    @GetMapping("/student/{studentId}")
    public List<Attendance> getAttendanceByStudent(@PathVariable Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    @GetMapping("/classes/{classId}/students")
    public List<Student> getStudentsByClassId(@PathVariable Long classId) {
        Optional<StdClass> stdClass = classRepository.findById(classId);
        if (stdClass.isPresent()) {
            return stdClass.get().getStudents(); // Assuming StdClass has a getStudents() method
        } else {
            throw new RuntimeException("Class not found");
        }
    }

    @GetMapping("/class/{classId}")
    public List<Attendance> getAttendanceByClass(@PathVariable Long classId) {
        return attendanceRepository.findByStdClassId(classId);
    }

    @GetMapping("/student/{studentId}/date")
    public List<Attendance> getAttendanceByStudentAndDate(
            @PathVariable Long studentId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        return attendanceRepository.findByStudentIdAndAttendanceDateBetween(studentId, start, end);
    }

    @GetMapping("/class/{classId}/date")
    public List<Attendance> getAttendanceByClassAndDate(
            @PathVariable Long classId,
            @RequestParam String startDate,
            @RequestParam String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);
        return attendanceRepository.findByStdClassIdAndAttendanceDateBetween(classId, start, end);
    }
}
