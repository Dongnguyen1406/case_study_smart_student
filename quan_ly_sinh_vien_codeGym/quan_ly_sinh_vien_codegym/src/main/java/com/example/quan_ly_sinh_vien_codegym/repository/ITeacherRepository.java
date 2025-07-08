package com.example.quan_ly_sinh_vien_codegym.repository;

import com.example.quan_ly_sinh_vien_codegym.entity.Teacher;

public interface ITeacherRepository extends IRepository<Teacher> {
    Teacher findById(String teacherId);
}