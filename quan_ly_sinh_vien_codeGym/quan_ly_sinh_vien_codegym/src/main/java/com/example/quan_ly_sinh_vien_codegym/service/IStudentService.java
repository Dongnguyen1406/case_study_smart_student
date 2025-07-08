package com.example.quan_ly_sinh_vien_codegym.service;

import com.example.quan_ly_sinh_vien_codegym.dto.ModuleAttendance;
import com.example.quan_ly_sinh_vien_codegym.dto.StudentDto;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;
import com.example.quan_ly_sinh_vien_codegym.dto.ModuleScore;

public interface IStudentService extends IService<StudentDto> {
     Student displayStudent(String username);
     boolean updateStudent( Student student);
     ModuleScore displayScore(String userName);
     ModuleAttendance displayAttendance(String userName);
}
