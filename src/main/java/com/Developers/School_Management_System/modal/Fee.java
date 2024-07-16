package com.Developers.School_Management_System.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Data
@Table(name = "FeeTable")
public class Fee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "Amount")
    private double amount;

    @Column(name = "Status")
    private Boolean status;

    @Column(name = "Date")
    private Date date;

  @ManyToOne
  @JoinColumn(name = "student_id", nullable = false)
  private Class StudentTble;
}
