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
    @Column(name = "Id")
    private Long id;

    @Column(name = "Amount")
    private double amount;

    @Column(name = "Status")
    private String status;

    @Column(name = "Date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "StudentId")
    private Student student;
}
