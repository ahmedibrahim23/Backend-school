package com.Developers.School_Management_System.modal;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Data
@Table(name = "Fee_tble")
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

}
