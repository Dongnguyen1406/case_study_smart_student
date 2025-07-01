package com.example.quan_ly_sinh_vien_codegym.Service;

import com.example.quan_ly_sinh_vien_codegym.Entity.Student;

import java.util.List;

public interface IStudentService extends Service<Student>{
    public List<Student> displayStudent(String username);
}
