package com.example.quan_ly_sinh_vien_codegym.service.impl;

import com.example.quan_ly_sinh_vien_codegym.dto.ModuleAttendance;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;
import com.example.quan_ly_sinh_vien_codegym.repository.IStudentRepository;
import com.example.quan_ly_sinh_vien_codegym.repository.impl.StudentRepository;
import com.example.quan_ly_sinh_vien_codegym.service.IStudentService;
import com.example.quan_ly_sinh_vien_codegym.dto.ModuleScore;

import java.util.List;

public class StudentService implements IStudentService {
    private static final IStudentRepository iStudentRepository= new StudentRepository();
    @Override
    public List<Student> findAll() {
        return iStudentRepository.findAll();
    }

    @Override
    public Student displayStudent(String username) {
        return iStudentRepository.displayStudent(username);
    }

    @Override
    public boolean updateStudent(Student student) {
        return iStudentRepository.updateStudent(student);
    }

    @Override
    public ModuleScore displayScore(String userName) {
        return iStudentRepository.displayScore(userName);
    }


    @Override
    public ModuleAttendance displayAttendance(String userName) {
        return iStudentRepository.displayAttendance(userName);
    }



}
