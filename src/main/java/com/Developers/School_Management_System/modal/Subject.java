package com.Developers.School_Management_System.modal;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subject_tble")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "subjectName")
    private String subjectName;
}
