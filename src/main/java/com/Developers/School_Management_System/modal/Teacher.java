package com.Developers.School_Management_System.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "TeacherTble")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TId")
    private Long id;

    @Column(name = "FullName")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "Class_id")
    private Class className;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Email")
    private String email;

    @Column(name = "Hiredate")
    private Date hireDate;

    @ManyToOne
    @JoinColumn(name = "Subject_id")
    private Subject subject;



}
