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
    private Long id;

    private String FullName;

    private Date DateOfBirth;

    private String Gender;

    private String Address;

    private  Long  Phone;

    @Column(unique = true)
    private String Email;

    private String password;

    private Date HireDate;


    @OneToMany(mappedBy = "teacher")
    private List<SchoolClass> schoolClasses;


}
