package com.example.quan_ly_sinh_vien_codegym.Service.impl;

import com.example.quan_ly_sinh_vien_codegym.Entity.Student;
import com.example.quan_ly_sinh_vien_codegym.Repository.IStudentRepository;
import com.example.quan_ly_sinh_vien_codegym.Repository.impl.StudentRepository;
import com.example.quan_ly_sinh_vien_codegym.Service.IStudentService;
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
   public ModuleScore displayScore(String userName){
        return iStudentRepository.displayScore(userName);
    }


}
