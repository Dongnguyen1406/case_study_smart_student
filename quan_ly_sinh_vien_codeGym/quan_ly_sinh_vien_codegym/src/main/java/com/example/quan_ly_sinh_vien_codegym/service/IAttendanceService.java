package com.example.quan_ly_sinh_vien_codegym.service;

import com.example.quan_ly_sinh_vien_codegym.entity.Attendance;
import com.example.quan_ly_sinh_vien_codegym.entity.AttendanceStatus;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;

import java.util.List;

public interface IAttendanceService {
    List<Student> findStudentsByClassId(int classId);
    List<AttendanceStatus> findAllStatuses();
    void saveAttendance(List<Attendance> attendances);
}