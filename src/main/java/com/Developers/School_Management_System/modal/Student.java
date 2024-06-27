package com.Developers.School_Management_System.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "studentTble")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 50)
    private String fullName;
    private Date dateOfBirth;
    private String sex;
    private String address;
    private int phone;
    @Column(unique = true)
    private String email;
    private Date enrolmentDate;
    private String password;
}
