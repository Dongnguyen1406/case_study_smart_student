package com.example.quan_ly_sinh_vien_codegym.service.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Attendance;
import com.example.quan_ly_sinh_vien_codegym.entity.AttendanceStatus;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;
import com.example.quan_ly_sinh_vien_codegym.repository.IAttendanceRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.IStudentRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.impl.AttendanceRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.impl.StudentRepository;
import com.example.quan_ly_sinh_vien_codegym.service.IAttendanceService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class AttendanceService implements IAttendanceService {
    private final IAttendanceRepository attendanceRepository = new AttendanceRepository();
    private final IStudentRepository studentRepository = new StudentRepository();

    @Override
    public List<Student> findStudentsByClassId(int classId) {
        return studentRepository.findByClassId(classId);
    }

    @Override
    public List<AttendanceStatus> findAllStatuses() {
        return attendanceRepository.findAllStatuses();
    }

    @Override
    public void saveAttendance(List<Attendance> attendances) {
        attendanceRepository.saveAttendance(attendances);
    }
    
    @Override
    public Map<String, Integer> findAttendanceByClassAndDate(int classId, LocalDate date) {
        return attendanceRepository.findAttendanceByClassAndDate(classId, date);
    }
    
    @Override
    public void deleteAttendanceByClassAndDate(int classId, LocalDate date) {
        attendanceRepository.deleteAttendanceByClassAndDate(classId, date);
    }
    
    @Override
    public void deleteStudentAttendanceByDate(String studentId, LocalDate date) {
        attendanceRepository.deleteStudentAttendanceByDate(studentId, date);
    }
}