package com.example.quan_ly_sinh_vien_codegym.service;

import com.example.quan_ly_sinh_vien_codegym.entity.Assessment;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;

import java.util.List;

public interface IAssessmentService {
    List<Student> findStudentsByClassAndModule(int classId, int moduleId);
    void saveAssessments(List<Assessment> assessments);
}