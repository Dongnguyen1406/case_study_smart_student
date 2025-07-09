package com.example.quan_ly_sinh_vien_codegym.service;

import com.example.quan_ly_sinh_vien_codegym.entity.Assessment;
import com.example.quan_ly_sinh_vien_codegym.entity.Student;

import java.util.List;
import java.util.Map;

public interface IAssessmentService {
    List<Student> findStudentsByClassAndModule(int classId, int moduleId);
    void saveAssessments(List<Assessment> assessments);
    Map<String, Assessment> findAssessmentsByClassAndModule(int classId, int moduleId);
    void deleteAssessmentsByClassAndModule(int classId, int moduleId);
    void deleteStudentAssessmentByModuleId(String studentId, int moduleId);
}