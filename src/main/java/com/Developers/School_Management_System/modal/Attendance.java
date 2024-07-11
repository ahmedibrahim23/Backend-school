package com.Developers.School_Management_System.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
@Table(name = "AttendanceTable")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "FullName", nullable = false)
    private Class StudentTble;

    @Column(name = "Date")
    private Date date;

    @Column(name = "Status")
    private String status;
}
