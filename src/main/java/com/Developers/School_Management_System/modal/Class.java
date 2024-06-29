
package com.Developers.School_Management_System.modal;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name= "ClassTble")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ClassId;
    private  String ClassName;


    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;


}