package com.Developers.School_Management_System.repo;

import com.Developers.School_Management_System.modal.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepo extends JpaRepository<SchoolClass,Long> {
}
