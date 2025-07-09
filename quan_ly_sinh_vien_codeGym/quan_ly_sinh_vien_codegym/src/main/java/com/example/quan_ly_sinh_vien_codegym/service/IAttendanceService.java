package com.example.quan_ly_sinh_vien_codegym.service;

import com.example.quan_ly_sinh_vien_codegym.entity.Attendance;
import com.example.quan_ly_sinh_vien_codegym.entity.AttendanceStatus;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface IAttendanceService {
    List<Student> findStudentsByClassId(int classId);
    List<AttendanceStatus> findAllStatuses();
    void saveAttendance(List<Attendance> attendances);
    Map<String, Integer> findAttendanceByClassAndDate(int classId, LocalDate date);
    void deleteAttendanceByClassAndDate(int classId, LocalDate date);
    void deleteStudentAttendanceByDate(String studentId, LocalDate date);
}