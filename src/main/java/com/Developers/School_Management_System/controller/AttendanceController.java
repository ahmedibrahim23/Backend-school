package com.Developers.School_Management_System.controller;

import com.Developers.School_Management_System.modal.Attendance;
import com.Developers.School_Management_System.service.AttendanceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/Attendance")
public class AttendanceController {
    private  final AttendanceServices attendanceServices;
@Autowired
    public AttendanceController(AttendanceServices attendanceServices) {
        this.attendanceServices = attendanceServices;
    }
    @GetMapping
    public String getAllAttendance(Model model) {
        List<Attendance> attendances=attendanceServices.getAllAttendance();
        model.addAttribute("attendances", attendances);
        return "attendances";
    }
    @GetMapping("/{id}")
    public String getAttendanceById(@PathVariable Long id, Model model) {
        Attendance attendance=attendanceServices.getAttendanceById(id);
        model.addAttribute("attendance", attendance);
        return "attendance-details";
    }

}
