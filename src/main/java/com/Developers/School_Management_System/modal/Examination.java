package com.Developers.School_Management_System.modal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "exam_tble")
@Data
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "stdmarks")
    private String marks;

}
