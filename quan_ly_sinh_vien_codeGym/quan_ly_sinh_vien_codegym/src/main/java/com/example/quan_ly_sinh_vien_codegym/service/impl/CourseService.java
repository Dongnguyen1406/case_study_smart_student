package com.example.quan_ly_sinh_vien_codegym.service.impl;

import com.example.quan_ly_sinh_vien_codegym.entity.Course;
import com.example.quan_ly_sinh_vien_codegym.repository.ICourseRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.IStudentRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.impl.CourseRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.impl.StudentRepository;
import com.example.quan_ly_sinh_vien_codegym.service.ICourseService;

import java.util.List;

public class CourseService implements ICourseService {
    private static final ICourseRepository courseRepository= new CourseRepository();
    
    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course select(int id) {
        return null;
    }

    @Override
    public boolean update(Course course) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public void add(Course course) {

    }
}
