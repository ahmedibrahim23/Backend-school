package com.Developers.School_Management_System.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "StudentTble")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @ManyToOne
    @JoinColumn(name = "class_id", nullable = false)
    private StdClass stdClass;

    @OneToMany(mappedBy = "student")
    private List<Attendance> attendance;

    @OneToMany(mappedBy = "student")
    private List<Examination> exams;

    @OneToMany(mappedBy = "student")
    private List<Fee> fees;
}
