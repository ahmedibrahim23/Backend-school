package com.Developers.School_Management_System.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Data
@Table(name = "AttendanceTable")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    private StdClass stdClass;

    @Column(name = "attendance_date", nullable = false)
    private LocalDateTime attendanceDate;

    @Column(name = "is_present", nullable = false)
    private Boolean present;
}
