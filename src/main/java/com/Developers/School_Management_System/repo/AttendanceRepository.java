package com.Developers.School_Management_System.repo;

import com.Developers.School_Management_System.modal.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByStudentId(Long studentId);
    List<Attendance> findByStdClassId(Long classId);
    List<Attendance> findByStudentIdAndAttendanceDateBetween(Long studentId, LocalDateTime startDate, LocalDateTime endDate);
    List<Attendance> findByStdClassIdAndAttendanceDateBetween(Long classId, LocalDateTime startDate, LocalDateTime endDate);
}
