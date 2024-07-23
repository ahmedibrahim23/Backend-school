package com.Developers.School_Management_System.modal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "exam_tble")
@Data
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(name = "marks")
    private String marks;
}
