package com.example.quan_ly_sinh_vien_codegym.service.impl;

import com.example.quan_ly_sinh_vien_codegym.dto.ClassResponseDto;
import com.example.quan_ly_sinh_vien_codegym.repository.IClassRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.IStudentRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.impl.ClassRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.impl.StudentRepository;
import com.example.quan_ly_sinh_vien_codegym.service.IClassService;

import java.util.List;

public class ClassService implements IClassService {
    private static final IClassRepository classRepository= new ClassRepository();


    @Override
    public List<ClassResponseDto> findAll() {
        return classRepository.findAll();
    }

    @Override
    public ClassResponseDto select(int id) {
        return null;
    }

    @Override
    public boolean update(ClassResponseDto classResponseDto) {
        return classRepository.update(classResponseDto);
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return classRepository.deleteById(id);
    }

    @Override
    public void add(ClassResponseDto classResponseDto) {
        classRepository.add(classResponseDto);
    }
}
