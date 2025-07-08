package com.example.quan_ly_sinh_vien_codegym.repository;

import com.example.quan_ly_sinh_vien_codegym.dto.ClassResponseDto;

import java.util.List;

public interface IClassRepository extends IRepository<ClassResponseDto> {
    List<ClassResponseDto> findByTeacherId(String teacherId); // Thêm phương thức
}