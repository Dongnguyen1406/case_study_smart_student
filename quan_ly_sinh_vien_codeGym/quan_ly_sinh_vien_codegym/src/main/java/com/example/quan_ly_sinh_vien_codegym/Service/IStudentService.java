package com.example.quan_ly_sinh_vien_codegym.Service;

import com.example.quan_ly_sinh_vien_codegym.Entity.Student;
import com.example.quan_ly_sinh_vien_codegym.dto.ModuleScore;

import java.util.List;

public interface IStudentService extends Service<Student>{
     Student displayStudent(String username);
     boolean updateStudent( Student student);
     ModuleScore displayScore(String userName);
}
