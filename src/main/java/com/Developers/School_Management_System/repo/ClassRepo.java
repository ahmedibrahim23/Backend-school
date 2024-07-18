package com.Developers.School_Management_System.repo;

import com.Developers.School_Management_System.modal.StdClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepo extends JpaRepository<StdClass,Long> {
}
