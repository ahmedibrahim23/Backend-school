package com.Developers.School_Management_System.modal;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class Attendance {
    private int attendanceID;
    @ManyToOne
    @JoinColumn(name = "StudentID", nullable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(name = "fullName", nullable = false)
    private Student studentFullName;
    private Date date;
    private String Status;

}
