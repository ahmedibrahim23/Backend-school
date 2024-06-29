package com.Developers.School_Management_System.repo;

import com.Developers.School_Management_System.modal.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
