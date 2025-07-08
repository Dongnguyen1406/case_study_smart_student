package com.example.quan_ly_sinh_vien_codegym.service;

import com.example.quan_ly_sinh_vien_codegym.dto.ClassResponseDto;

import java.util.List;

public interface IClassService extends IService<ClassResponseDto> {
    List<ClassResponseDto> findByTeacherId(String teacherId); // Thêm phương thức
}