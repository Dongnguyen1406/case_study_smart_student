package com.example.quan_ly_sinh_vien_codegym.repository;

import com.example.quan_ly_sinh_vien_codegym.entity.Attendance;
import com.example.quan_ly_sinh_vien_codegym.entity.AttendanceStatus;

import java.util.List;

public interface IAttendanceRepository {
    List<AttendanceStatus> findAllStatuses();
    void saveAttendance(List<Attendance> attendances);
}