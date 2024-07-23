package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.exception.ResourceNotFoundException;
import com.Developers.School_Management_System.modal.Attendance;
import com.Developers.School_Management_System.repo.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {
    @Autowired
    private AttendanceRepository attendanceRepository;

    // Get all attendance records
    @GetMapping
    public List<Attendance> getAllAttendances() {
        return this.attendanceRepository.findAll();
    }

    // Get attendance record by ID
    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendanceById(@PathVariable(value = "id") Long attendanceId)
            throws ResourceNotFoundException {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found for this id :: " + attendanceId));
        return ResponseEntity.ok().body(attendance);
    }

    // Create a new attendance record
    @PostMapping("/new")
    public Attendance createAttendance(@RequestBody Attendance attendance) {
        return this.attendanceRepository.save(attendance);
    }

    // Update an existing attendance record
    @PutMapping("/edit/{id}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable(value = "id") Long attendanceId,
                                                       @Validated @RequestBody Attendance attendanceDetails) throws ResourceNotFoundException {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found for this id :: " + attendanceId));

        // Update attendance fields
        attendance.setStudent(attendanceDetails.getStudent());
        attendance.setDate(attendanceDetails.getDate());
        attendance.setStatus(attendanceDetails.getStatus());

        return ResponseEntity.ok(this.attendanceRepository.save(attendance));
    }

    // Delete an attendance record
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteAttendance(@PathVariable(value = "id") Long attendanceId) throws ResourceNotFoundException {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new ResourceNotFoundException("Attendance not found for this id :: " + attendanceId));

        this.attendanceRepository.delete(attendance);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }
}
