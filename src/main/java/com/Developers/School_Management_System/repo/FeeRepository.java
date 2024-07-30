package com.Developers.School_Management_System.repo;

import com.Developers.School_Management_System.modal.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface FeeRepository extends JpaRepository<Fee,Long> {
}
