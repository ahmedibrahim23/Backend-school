package com.Developers.School_Management_System.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
@Table(name = "FeeTaable")
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 80)
    private Double Amount;
    private String Status;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "id",nullable = false)
    private Student student;
}
