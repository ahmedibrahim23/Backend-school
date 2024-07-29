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
    @Column(name = "Id")
    private Long id;

    @Column(name = "FullName")
    private String fullName;

    @Column(name = "DateOfBirth")
    private Date dateOfBirth;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Address")
    private String address;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;
    @Column(name = "ParentName")
    private String parentname;
    @Column(name = "ParentNumber")
    private int parentnumber;
    @ManyToOne
    @JoinColumn(name = "Class_id")
    private StdClass stdClass;
}
