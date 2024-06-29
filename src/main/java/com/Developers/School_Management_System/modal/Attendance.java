package com.Developers.School_Management_System.modal;

import jakarta.persistence.*;

import java.util.Date;

public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "StudentId")
    private Student student;

    @Column(name = "Date")
    private Date date;

    @Column(name = "Status")
    private String status;
}
