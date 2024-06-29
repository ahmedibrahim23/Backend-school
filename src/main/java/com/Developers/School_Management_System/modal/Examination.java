package com.Developers.School_Management_System.modal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ExaminationTble")
@Data
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "studentID", nullable = false)
    private Student student;
    @ManyToOne
    @JoinColumn(name = "subjectid",nullable = false)
    private Subject subject;
    private String marks;


}
