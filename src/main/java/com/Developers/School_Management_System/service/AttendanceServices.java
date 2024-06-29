package com.Developers.School_Management_System.service;
import com.Developers.School_Management_System.modal.Attendance;
import com.Developers.School_Management_System.repo.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServices {
    private final AttendanceRepository attendanceRepository;
@Autowired
    public AttendanceServices(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }
    public List<Attendance> getAllAttendance(){  return attendanceRepository.findAll();}
    public Attendance getAttendanceById(Long id){return attendanceRepository.findById(id).orElse(null);}
    public void saveAttendance(Attendance attendance){attendanceRepository.save(attendance);}
    public void deleteAttendance(Long id){attendanceRepository.deleteById(id);}
}
