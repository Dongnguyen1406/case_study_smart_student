package com.example.quan_ly_sinh_vien_codegym.service;


import com.example.quan_ly_sinh_vien_codegym.dto.AssessmentDto;
import com.example.quan_ly_sinh_vien_codegym.dto.AttendanceDateDto;
import com.example.quan_ly_sinh_vien_codegym.dto.ModuleAttendance;
import com.example.quan_ly_sinh_vien_codegym.dto.StudentDto;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;
import com.example.quan_ly_sinh_vien_codegym.dto.ModuleScore;

import java.util.List;

public interface IStudentService extends IService<Student> {
     Student displayStudent(String username);

     boolean updateStudent(Student student);

     ModuleScore displayScore(String userName);

     ModuleAttendance displayAttendance(String userName);

     List<Student> findByClassId(int classId); // Thêm phương thức findByClassId
     List<AssessmentDto> displayAssessments(String userName);
     List<AttendanceDateDto> displayAttendanceDate(String idStudent);
     List<StudentDto> findAllDto();
    
}