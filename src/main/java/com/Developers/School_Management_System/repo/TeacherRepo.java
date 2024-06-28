package com.Developers.School_Management_System.repo;

import com.Developers.School_Management_System.modal.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepo extends JpaRepository<Teacher,Long> {
}
