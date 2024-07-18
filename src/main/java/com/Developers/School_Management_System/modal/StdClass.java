
package com.Developers.School_Management_System.modal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name= "ClassTble")
public class StdClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Class_id")
    private Long id;

    @Column(name = "Name")
    private String name;

    private String subject;
}