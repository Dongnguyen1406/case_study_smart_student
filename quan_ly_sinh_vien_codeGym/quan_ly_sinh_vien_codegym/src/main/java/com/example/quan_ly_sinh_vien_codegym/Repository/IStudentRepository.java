package com.example.quan_ly_sinh_vien_codegym.Repository;

import com.example.quan_ly_sinh_vien_codegym.Entity.Student;

import java.util.List;

public interface IStudentRepository extends IRepository<Student>{
    public List<Student> displayStudent(String username);
}
