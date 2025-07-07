package com.example.quan_ly_sinh_vien_codegym.service.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Teacher;
import com.example.quan_ly_sinh_vien_codegym.repository.ITeacherRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.impl.TeacherRepository;
import com.example.quan_ly_sinh_vien_codegym.service.ITeacherService;

import java.util.List;

public class TeacherService implements ITeacherService {
    private static ITeacherRepository teacherRepository = new TeacherRepository(); 
    
    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher select(int id) {
        return null;
    }

    @Override
    public boolean update(Teacher teacher) {
        return teacherRepository.update(teacher);
    }

    @Override
    public boolean delete(String id) {
        return teacherRepository.delete(id);
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public void add(Teacher teacher) {

    }
}

