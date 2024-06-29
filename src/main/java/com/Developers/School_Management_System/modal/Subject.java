package com.Developers.School_Management_System.modal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "subjectTble")
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subjectName;
    @ManyToOne
    @JoinColumn(name = "ClassId",nullable = false)
    private SchoolClass schoolClassTble;
}
